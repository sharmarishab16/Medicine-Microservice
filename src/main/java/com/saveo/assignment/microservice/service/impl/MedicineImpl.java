package com.saveo.assignment.microservice.service.impl;

import com.saveo.assignment.microservice.dto.*;
import com.saveo.assignment.microservice.repository.MedicineRepository;
import com.saveo.assignment.microservice.repository.OrderRepository;
import com.saveo.assignment.microservice.service.MedicineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicineImpl implements MedicineService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final MedicineRepository medicineRepository;

    private final OrderRepository orderRepository;

    public MedicineImpl(MedicineRepository medicineRepository, OrderRepository orderRepository) {
        this.medicineRepository = medicineRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean addMedicines(List<List<String>> medicines) {
        List<Medicine> medicineList = new ArrayList<>();
        Optional.ofNullable(medicines).orElse(new ArrayList<>()).forEach( med -> {
            Medicine medicine = new Medicine();
            try {
                medicine.setName(med.get(0));
                medicine.setBatchNo(med.get(1));
                medicine.setExpiryDate(med.get(2));
                medicine.setBalanceQuantity(Integer.parseInt(med.get(3)));
                medicine.setPackaging(med.get(4));
                medicine.setUniqueCode(med.get(5));
                medicine.setSchemes(med.get(6));
                medicine.setMrp(med.get(7));
                medicine.setManufacturer(med.get(8));
                medicine.setHsnCode(med.get(9));
                medicineList.add(medicine);
            }
            catch (Exception e){
                logger.error("Error while parsing medicine :: " + med.toString());
            }
        });
        try {
            medicineRepository.saveAll(medicineList);
            return true;
        }
        catch (Exception e){
            logger.error("Error while saving all records :: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<String> searchMedicines(String queryString) {
        List<Medicine> medicineList = new ArrayList<>();
        List<String> medicineNames = new ArrayList<>();
        try {
            medicineList = medicineRepository.findByNameContaining(queryString);
            medicineNames = Optional.ofNullable(medicineList).orElse(new ArrayList<>()).stream().map(medicine -> medicine.getName()).collect(Collectors.toList());
        }
        catch (Exception e){
            logger.error("Error while retrieving medicine list for query string :: " + queryString);
        }
        return medicineNames;
    }

    @Override
    public Medicine getMedicineDetails(String uniqueCode) {
        try {
           List<Medicine> medicineList = medicineRepository.findByUniqueCode(uniqueCode);

           return Optional.ofNullable(medicineList.get(0)).orElse(null);
        }
        catch (Exception e){
            logger.error("Error while fetching med details");
        }
        return null;
    }

    @Override
    public Response placeOrder(OrderRequest orderRequest ) {
       orderRequest.getOrders().stream().forEach(orders -> {
           List<Medicine> medicineList = medicineRepository.findByUniqueCode(orders.getUniqueCode());
           Medicine medicine = medicineList.get(0);
           medicine.setBalanceQuantity(medicine.getBalanceQuantity()-orders.getQuantity());
           medicineRepository.save(medicine);
       });

      Response response = new Response();
      UUID uuid = UUID.randomUUID();
      response.setOrderId(uuid);
      OrderDetails orderDetails = new OrderDetails();
      orderDetails.setOrderId(uuid);
      orderDetails.setOrders(orderRequest.getOrders());
      orderRepository.save(orderDetails);
      return response;
    }
}

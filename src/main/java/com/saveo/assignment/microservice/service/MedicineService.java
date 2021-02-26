package com.saveo.assignment.microservice.service;

import com.saveo.assignment.microservice.dto.Medicine;
import com.saveo.assignment.microservice.dto.OrderRequest;
import com.saveo.assignment.microservice.dto.Response;

import java.util.List;

public interface MedicineService {
    boolean addMedicines(List<List<String>> medicines);
    List<String> searchMedicines(String queryString);
    Medicine getMedicineDetails(String uniqueCode);
    Response placeOrder(OrderRequest orderRequest);
}

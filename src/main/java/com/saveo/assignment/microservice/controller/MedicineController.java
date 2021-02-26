package com.saveo.assignment.microservice.controller;

import com.opencsv.CSVReader;
import com.saveo.assignment.microservice.dto.Medicine;
import com.saveo.assignment.microservice.dto.OrderRequest;
import com.saveo.assignment.microservice.dto.Response;
import com.saveo.assignment.microservice.service.MedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.util.*;

@RestController
public class MedicineController {

    private final MedicineService medicineService;

    //Contructor autowiring
    MedicineController(MedicineService medicineService){
        this.medicineService = medicineService;
    }

    @RequestMapping(value = "/uploadCSV", method = RequestMethod.GET)
    public ResponseEntity<Object> uploadCsv(@RequestParam(required = true, value = "fileLocation") String location){
        List<List<String>> csvRecords = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(location));
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                csvRecords.add(Arrays.asList(values));
            }
            medicineService.addMedicines(csvRecords);
            return new ResponseEntity<>(new Response("Data uploaded successfully"), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(new Response("Failed to process request."),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/searchMedicine",method = RequestMethod.GET)
    public ResponseEntity<Object> searchMedicine(@RequestParam(value = "query", required = true) String queryString){
        List<String> medicineList = new ArrayList<>();
        medicineList=  medicineService.searchMedicines(queryString);
        if(CollectionUtils.isEmpty(medicineList)){
            return new ResponseEntity<>(new Response("No medicines found"), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(medicineList,HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/getMedicineDetails")
    public ResponseEntity<Object> getMedicineDetails(@RequestParam(required = true,value = "uniqueCode") String uniqueCode){
        Medicine medicine = medicineService.getMedicineDetails(uniqueCode);
        if(Objects.isNull(medicine)){
            return new ResponseEntity<>(new Response("No medicine found"), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(medicine,HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/placeorder",method = RequestMethod.POST)
    public ResponseEntity<Object> placeOrder(@RequestBody OrderRequest orderRequest){
        Response response = medicineService.placeOrder(orderRequest);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}

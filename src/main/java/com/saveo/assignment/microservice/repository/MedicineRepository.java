package com.saveo.assignment.microservice.repository;

import com.saveo.assignment.microservice.dto.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends MongoRepository<Medicine,String> {
     List<Medicine> findByNameContaining(String name);
    <S extends Medicine> List<S> saveAll(Iterable<S> iterable);
    List<Medicine> findByUniqueCode(String uniqueCode);
}

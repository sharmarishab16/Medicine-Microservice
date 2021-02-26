package com.saveo.assignment.microservice.repository;

import com.saveo.assignment.microservice.dto.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends MongoRepository<OrderDetails,UUID> {

}

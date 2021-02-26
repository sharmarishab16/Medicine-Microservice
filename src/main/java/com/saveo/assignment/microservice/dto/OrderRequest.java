package com.saveo.assignment.microservice.dto;

import java.util.List;

public class OrderRequest {
    private List<Orders> orders;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "orders=" + orders +
                '}';
    }
}

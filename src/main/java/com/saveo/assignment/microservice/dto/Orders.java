package com.saveo.assignment.microservice.dto;

public class Orders {
    private String uniqueCode;
    private Integer quantity;
    private String name;

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "uniqueCode='" + uniqueCode + '\'' +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                '}';
    }
}

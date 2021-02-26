package com.saveo.assignment.microservice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medicines")
public class Medicine {

    @Id
    private String id;
    private String uniqueCode;
    private String name;
    private String batchNo;
    private String expiryDate;
    private Integer balanceQuantity;
    private String packaging;
    private String schemes;
    private String mrp;
    private String manufacturer;
    private String hsnCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Integer getBalanceQuantity() {
        return balanceQuantity;
    }

    public void setBalanceQuantity(Integer balanceQuantity) {
        this.balanceQuantity = balanceQuantity;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getSchemes() {
        return schemes;
    }

    public void setSchemes(String schemes) {
        this.schemes = schemes;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", uniqueCode='" + uniqueCode + '\'' +
                ", name='" + name + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", balanceQuantity='" + balanceQuantity + '\'' +
                ", packaging='" + packaging + '\'' +
                ", schemes='" + schemes + '\'' +
                ", mrp='" + mrp + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", hsnCode='" + hsnCode + '\'' +
                '}';
    }
}

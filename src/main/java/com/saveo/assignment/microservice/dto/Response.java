package com.saveo.assignment.microservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {
    private String responseMsg;
    private UUID orderId ;

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public Response(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public Response() {
    }
}

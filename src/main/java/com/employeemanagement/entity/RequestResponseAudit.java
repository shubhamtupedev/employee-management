package com.employeemanagement.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "REQUEST_RESPONSE_AUDIT")
public class RequestResponseAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "END_POINT")
    private String endPoint;

    @Column(name = "REQUEST_PAYLOAD")
    private byte[] requestPayload;

    @Column(name = "RESPONSE_PAYLOAD")
    private byte[] responsePayload;

    @Column(name = "ERROR")
    private String error;

    @Column(name = "LOCAL_DATE_TIME")
    private LocalDateTime localDateTime;

    public RequestResponseAudit() {
    }

    public RequestResponseAudit(Long id, String endPoint, byte[] requestPayload, byte[] responsePayload, String error, LocalDateTime localDateTime) {
        this.id = id;
        this.endPoint = endPoint;
        this.requestPayload = requestPayload;
        this.responsePayload = responsePayload;
        this.error = error;
        this.localDateTime = localDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public byte[] getRequestPayload() {
        return requestPayload;
    }

    public void setRequestPayload(byte[] requestPayload) {
        this.requestPayload = requestPayload;
    }

    public byte[] getResponsePayload() {
        return responsePayload;
    }

    public void setResponsePayload(byte[] responsePayload) {
        this.responsePayload = responsePayload;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}

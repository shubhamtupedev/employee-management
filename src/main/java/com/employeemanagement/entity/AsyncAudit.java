package com.employeemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "ASYNC_AUDIT")
public class AsyncAudit extends BaseEntity {

    @Column(name = "PROCESS_NAME")
    private String processName;

    @Column(name = "PROCESS_START_TIME")
    private LocalDateTime processStartTime;

    @Column(name = "PROCESS_END_TIME")
    private LocalDateTime processEndTime;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "ERROR_MSG")
    private String errorMsg;

    @Column(name = "QUEUE_NAME")
    private String queueName;

    public AsyncAudit() {
    }

    public AsyncAudit(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy, LocalDateTime processEndTime, String processName, LocalDateTime processStartTime, String status, String errorMsg, String queueName) {
        super(id, createdDate, createdBy, modifiedDate, modifiedBy);
        this.processEndTime = processEndTime;
        this.processName = processName;
        this.processStartTime = processStartTime;
        this.status = status;
        this.errorMsg = errorMsg;
        this.queueName = queueName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public LocalDateTime getProcessStartTime() {
        return processStartTime;
    }

    public void setProcessStartTime(LocalDateTime processStartTime) {
        this.processStartTime = processStartTime;
    }

    public LocalDateTime getProcessEndTime() {
        return processEndTime;
    }

    public void setProcessEndTime(LocalDateTime processEndTime) {
        this.processEndTime = processEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}

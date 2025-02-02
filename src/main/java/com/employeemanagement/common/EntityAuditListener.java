package com.employeemanagement.common;

import com.employeemanagement.entity.BaseEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

public class EntityAuditListener {

    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCreatedBy(getCurrentUser());
    }

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setModifiedDate(LocalDateTime.now());
        entity.setModifiedBy(getCurrentUser());
    }

    private String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        } else if (authentication != null) {
            return authentication.getPrincipal().toString();
        }
        return "SystemUser"; // Replace with actual logic
    }
}

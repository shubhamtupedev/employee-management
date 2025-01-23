package com.employeemanagement.common;

import jakarta.validation.GroupSequence;

@GroupSequence({
        NotEmptyGroupValidation.class,
        PatternGroupValidation.class,
        OtherGroupValidation.class
})
public interface ValidationSequence {
}

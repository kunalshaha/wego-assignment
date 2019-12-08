package com.wego.parkingmanagement.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumericStringValidator implements ConstraintValidator<NumericString, String> {

    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        if (str.matches("[0-9]+")) return true;
        return false;
    }
}

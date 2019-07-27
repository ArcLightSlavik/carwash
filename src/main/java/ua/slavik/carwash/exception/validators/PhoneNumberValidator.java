package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private int min;
    private int max;

    @Override
    public void initialize(PhoneNumber phoneNumber) {
        min = phoneNumber.min();
        max = phoneNumber.max();
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber.matches("^\\+?\\d+$")) {
            return phoneNumber.length() >= min && phoneNumber.length() <= max;
        }

        return false;
    }
}


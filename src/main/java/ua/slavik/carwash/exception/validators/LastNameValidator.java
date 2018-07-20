package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LastNameValidator implements ConstraintValidator<LastName,String> {

    @Override
    public void initialize(LastName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String lastName, ConstraintValidatorContext constraintValidatorContext) {
        return lastName.matches("[a-zA-Z0-9]*");
    }
}


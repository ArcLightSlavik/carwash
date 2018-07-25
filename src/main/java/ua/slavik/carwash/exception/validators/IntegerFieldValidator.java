package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntegerFieldValidator implements ConstraintValidator<IntegerField,Integer> {
    @Override
    public void initialize(IntegerField constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer integerField, ConstraintValidatorContext constraintValidatorContext) {
        return integerField != null && integerField >= 0;
    }
}

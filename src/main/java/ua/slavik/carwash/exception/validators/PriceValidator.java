package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<Price,Integer> {
    @Override
    public void initialize(Price constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer price, ConstraintValidatorContext constraintValidatorContext) {
        return price >= 0;
    }
}

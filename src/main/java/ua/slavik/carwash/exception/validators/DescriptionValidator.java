package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DescriptionValidator implements ConstraintValidator<Description,String> {
    @Override
    public void initialize(Description constraintAnnotation) {

    }

    @Override
    public boolean isValid(String description, ConstraintValidatorContext constraintValidatorContext) {
        return description.length() <= 50;
    }
}

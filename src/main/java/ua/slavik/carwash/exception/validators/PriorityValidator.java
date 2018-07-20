package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriorityValidator implements ConstraintValidator<Priority,Integer> {
    @Override
    public void initialize(Priority constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer priority, ConstraintValidatorContext constraintValidatorContext) {
        return priority >= 0;
    }
}

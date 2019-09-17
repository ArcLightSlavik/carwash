package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, Long> {
    private boolean required;
    private int min;
    private int max;

    @Override
    public void initialize(Age age) {
        required = age.required();
        min = age.min();
        max = age.max();
    }

    @Override
    public boolean isValid(Long age, ConstraintValidatorContext constraintValidatorContext) {
        if (age == null) {
            return !required;
        }
        return age > min && age <= max;
    }
}

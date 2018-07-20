package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DurationValidator implements ConstraintValidator<Duration,Integer> {
    @Override
    public void initialize(Duration constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer duration, ConstraintValidatorContext constraintValidatorContext) {
        return duration >= 0;
    }
}

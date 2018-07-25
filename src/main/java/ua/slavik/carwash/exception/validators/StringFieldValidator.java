package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringFieldValidator implements ConstraintValidator<StringField,String> {
    @Override
    public void initialize(StringField constraintAnnotation) {

    }

    @Override
    public boolean isValid(String stringField, ConstraintValidatorContext constraintValidatorContext) {
        return stringField != null && stringField.length() >= 2 && stringField.length() <= 50;
    }
}

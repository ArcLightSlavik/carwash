package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringFieldValidator implements ConstraintValidator<StringField,String> {
    private int min;
    private int max;

    @Override
    public void initialize(StringField stringField) {
        min = stringField.min();
        max = stringField.max();
    }

    @Override
    public boolean isValid(String stringField, ConstraintValidatorContext constraintValidatorContext) {
        if (stringField == null) {
            return false;
        }
        return stringField.trim().length() >= min && stringField.length() <= max;
    }
}

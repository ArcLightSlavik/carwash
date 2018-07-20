package ua.slavik.carwash.exception.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FirstNameValidator implements ConstraintValidator<FirstName,String> {
    @Override
    public void initialize(FirstName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String firstName, ConstraintValidatorContext constraintValidatorContext) {
        return firstName.matches("[a-zA-Z0-9]*");
    }

    //The .matches("[0-9a-zA-Z]") will only return true if the whole string contains just 1 digit or letter.
    // The * in [0-9a-zA-Z]* will allow an empty string, or a string having zero or more letters/digits.
}

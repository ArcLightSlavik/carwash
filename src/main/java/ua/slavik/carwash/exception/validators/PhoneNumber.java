package ua.slavik.carwash.exception.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "PhoneNumber you entered is not valid.";

    int min() default 1;

    int max() default 20;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
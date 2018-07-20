package ua.slavik.carwash.exception.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Priority {
    String message() default "{Priority}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

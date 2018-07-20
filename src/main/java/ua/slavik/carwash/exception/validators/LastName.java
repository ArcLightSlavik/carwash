package ua.slavik.carwash.exception.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LastNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LastName {
    String message() default "{LastName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

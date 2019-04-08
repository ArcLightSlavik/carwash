package ua.slavik.carwash.exception.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {
    String message() default "Age you entered is invalid.";

    boolean required() default true;

    int min() default 0;

    int max() default 150;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

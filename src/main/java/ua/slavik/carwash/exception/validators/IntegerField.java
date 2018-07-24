package ua.slavik.carwash.exception.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IntegerFieldValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerField {
    String message() default "{IntegerField}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

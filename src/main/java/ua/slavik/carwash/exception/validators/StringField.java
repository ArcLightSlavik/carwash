package ua.slavik.carwash.exception.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StringFieldValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringField {
    String message() default "{StringField}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

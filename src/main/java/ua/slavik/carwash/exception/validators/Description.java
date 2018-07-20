package ua.slavik.carwash.exception.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DescriptionValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
    String message() default "{Description}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

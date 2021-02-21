package yte.intern.spring.project.use_cases.common.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EventDateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventDate {
    
    String message() default "End Date must be after the Start Date";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
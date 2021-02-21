package yte.intern.spring.project.use_cases.register_to_the_system.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TcKimlikNoValidator.class)
public @interface TcKimlikNo {

	String message() default "TC Kimlik No doesn't match the type!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

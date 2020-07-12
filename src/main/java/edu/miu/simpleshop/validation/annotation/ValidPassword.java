package edu.miu.simpleshop.validation.annotation;

import edu.miu.simpleshop.validation.PasswordConstraintValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
public @interface ValidPassword {
    String message() default "";
}

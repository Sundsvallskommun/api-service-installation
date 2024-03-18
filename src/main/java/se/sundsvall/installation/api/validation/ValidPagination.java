package se.sundsvall.installation.api.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import se.sundsvall.installation.api.validation.impl.ValidPaginationConstraintValidator;

@Documented
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPaginationConstraintValidator.class)
public @interface ValidPagination {

	String message() default "invalid pagination parameters";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}

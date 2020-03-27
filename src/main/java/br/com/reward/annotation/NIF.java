package br.com.reward.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.reward.validator.NIFValidator;

@Constraint(validatedBy = NIFValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NIF {
	String message() default "Invalid NIF";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
} 
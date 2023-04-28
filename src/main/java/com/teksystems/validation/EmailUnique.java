package com.teksystems.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Constraint(validatedBy = EmailUniqueImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUnique {

    String message() default "Email Address Found. Sign In ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
package org.nisum.challenge.controller.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "El Password no respecta las restricciones: " +
            "Min 1 Upercase, 1 Lowercase, 1 Caracter Especial, 1 Letra, Min 8 Caracteres";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

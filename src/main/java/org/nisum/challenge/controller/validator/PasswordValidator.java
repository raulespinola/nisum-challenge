package org.nisum.challenge.controller.validator;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password , String> {

    @Value("${patron.password}")
    private String patronConfigurable;

    @Override
    public void initialize(Password password) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password != null && password.matches(patronConfigurable)
                && (password.length() >= 8);
    }

}

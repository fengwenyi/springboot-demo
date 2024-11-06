package com.fengwenyi.demo.springboot.validation.service;

import com.fengwenyi.demo.springboot.validation.model.UserModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-04-02
 */
public class ValidationTests {

    @Test
    public void testValidation() {
        UserModel userModel = new UserModel();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserModel>> errors = validator.validate(userModel);
        String errMsg = errors.stream().map(error -> error.getPropertyPath() + ": " +  error.getMessage()).collect(Collectors.joining(","));
        System.out.println(errMsg);
    }

}

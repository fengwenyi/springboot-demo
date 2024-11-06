package com.fengwenyi.demo.springboot.validation.service;

import com.fengwenyi.demo.springboot.validation.DemoValidationApplicationTests;
import com.fengwenyi.demo.springboot.validation.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-11-06
 */
public class ValidatorServiceTests extends DemoValidationApplicationTests {

    @Autowired
    private Validator validator;

    public List<String> validateAddressInfo(UserModel userModel) {
        // 使用 DataBinder 绑定数据和校验结果
        DataBinder dataBinder = new DataBinder(userModel);
        dataBinder.setValidator(validator);
        dataBinder.validate();

        // 获取校验结果
        BindingResult bindingResult = dataBinder.getBindingResult();

        // 如果存在错误，收集并返回错误信息
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
        }

        // 如果没有错误，返回空列表
        return List.of();
    }

    @Test
    public void testGetErrMsg() {
        UserModel userModel = new UserModel();
        // 初始化 addressInfo 对象的字段...

        List<String> errors = validateAddressInfo(userModel);
        if (!errors.isEmpty()) {
            System.out.println("Validation errors: " + errors);
        } else {
            System.out.println("Validation passed!");
        }
    }

}

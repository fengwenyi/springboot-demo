package com.fengwenyi.demo.springboot.validation.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-04-02
 */
@Data
public class UserModel {

    @NotNull(message = "ID不能为空")
    private Long id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 0, message = "年龄不合法")
    private Integer age;

}

package com.fengwenyi.demo.springboot.log.controller;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.demo.springboot.log.dto.ShopAddDto;
import com.fengwenyi.demo.springboot.log.service.ITestService;
import com.fengwenyi.demo.springboot.log.vo.ShopVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-08-19
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final ITestService testService;

    @RequestMapping("/addShop")
    public ResultTemplate<?> addShop(@RequestBody @Validated ShopAddDto dto) {
        ShopVo vo = testService.addShop(dto);
        return ResultTemplate.success(vo);
    }

}

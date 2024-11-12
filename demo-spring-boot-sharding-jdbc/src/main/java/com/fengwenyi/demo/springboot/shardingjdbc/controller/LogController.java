package com.fengwenyi.demo.springboot.shardingjdbc.controller;

import com.fengwenyi.demo.springboot.shardingjdbc.entity.LogEntity;
import com.fengwenyi.demo.springboot.shardingjdbc.mapper.ILogMapper;
import com.fengwenyi.javalib.convert.JsonUtils;
import com.fengwenyi.javalib.generate.IdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-11-12
 */
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {

    private final ILogMapper logMapper;

    @GetMapping("/add")
    public String add() {
        LogEntity entity = new LogEntity();
        entity.setMsg(IdUtils.generateId());
        entity.setCreateDateTime(LocalDateTime.now());
        logMapper.insert(entity);
        return "success";
    }

    @GetMapping("/get")
    public String get() {
        return JsonUtils.string(logMapper.selectList(null));
    }

}

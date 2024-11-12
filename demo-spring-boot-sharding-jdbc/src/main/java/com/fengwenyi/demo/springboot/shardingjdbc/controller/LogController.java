package com.fengwenyi.demo.springboot.shardingjdbc.controller;

import com.fengwenyi.demo.springboot.shardingjdbc.entity.LogEntity;
import com.fengwenyi.demo.springboot.shardingjdbc.mapper.ILogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

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
        entity.setMsg(UUID.randomUUID().toString());
        entity.setCreateDateTime(LocalDateTime.now());
        logMapper.insert(entity);
        return "success";
    }

    @GetMapping("/get")
    public String get() {
        logMapper.selectList(null);
        return "success";
    }

}

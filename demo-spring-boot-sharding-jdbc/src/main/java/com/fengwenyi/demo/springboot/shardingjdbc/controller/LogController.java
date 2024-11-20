package com.fengwenyi.demo.springboot.shardingjdbc.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengwenyi.demo.springboot.shardingjdbc.entity.LogEntity;
import com.fengwenyi.demo.springboot.shardingjdbc.mapper.ILogMapper;
import com.fengwenyi.javalib.convert.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-11-14
 */
@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {

    private final ILogMapper logMapper;

    @RequestMapping("/add")
    public String add(String logDateTime) {
        LogEntity logEntity = new LogEntity();
        logEntity.setLogDateTime(DateTimeUtils.parseLocalDateTime(logDateTime, DateTimeUtils.DATE_TIME));
        logMapper.insert(logEntity);
        return "success";
    }

    @RequestMapping("/getPage")
    public String getPage(String lower, String upper) {
        Page<LogEntity> logEntityPage = logMapper.selectPage(
                new Page<>(1, 10),
                Wrappers.<LogEntity>lambdaQuery()
                        .ge(LogEntity::getLogDateTime, DateTimeUtils.parseLocalDateTime(lower, DateTimeUtils.DATE_TIME))
                        .le(LogEntity::getLogDateTime, DateTimeUtils.parseLocalDateTime(upper, DateTimeUtils.DATE_TIME))
        );
        return "success";
    }
}

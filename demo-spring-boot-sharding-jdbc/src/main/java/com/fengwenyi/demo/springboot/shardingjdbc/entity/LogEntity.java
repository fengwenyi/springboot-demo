package com.fengwenyi.demo.springboot.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-11-14
 */
@Data
@TableName("t_log")
public class LogEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String msg;

    @TableField("log_date_time")
    private LocalDateTime logDateTime;

}

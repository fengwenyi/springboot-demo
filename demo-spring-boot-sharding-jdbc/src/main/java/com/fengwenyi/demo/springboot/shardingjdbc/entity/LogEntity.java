package com.fengwenyi.demo.springboot.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-11-12
 */
@Data
@TableName("t_log")
public class LogEntity {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("msg")
    private String msg;

    @TableField("create_date_time")
    private LocalDateTime createDateTime;

}

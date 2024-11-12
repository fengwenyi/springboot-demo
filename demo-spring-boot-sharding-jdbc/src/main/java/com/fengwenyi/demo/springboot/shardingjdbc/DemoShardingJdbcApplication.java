package com.fengwenyi.demo.springboot.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-11-12
 */
@SpringBootApplication
@MapperScan("com.fengwenyi.demo.springboot.shardingjdbc.mapper")
public class DemoShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoShardingJdbcApplication.class, args);
    }

}

package com.fengwenyi.demo.springboot.log;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-05-08
 */
@RestController
@SpringBootApplication
@EnableLogRecord(tenant = "com.fengwenyi.demo.springboot")
public class LogApplication {

    private static final Log log = LogFactory.getLog(LogApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        log.info("home() is called");
        return "Hello World!";
    }

}

package com.fengwenyi.demo.springboot.shardingjdbc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-11-13
 */
@Data
@ConfigurationProperties(prefix = "hxsy")
@Configuration
public class ErwinDatasourceConfig {

    private Map<String, DataSource> dataSource;

    private Map<String, String> props;

    private Map<String, Sharding> sharding;

    @Data
    public static class DataSource {

        private String type;

        private String driverClassName;

        private String url;

        private String username;

        private String password;

    }

    @Data
    public static class Sharding {

        private String actualDataNodes;
        private String column;
        private String algorithmName;
        private Map<String, Algorithm> algorithm;
    }

    @Data
    public static class Algorithm {
        private String type;
        private Properties props;
    }

}

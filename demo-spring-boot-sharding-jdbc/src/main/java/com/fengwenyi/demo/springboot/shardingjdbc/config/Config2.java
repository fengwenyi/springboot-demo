package com.fengwenyi.demo.springboot.shardingjdbc.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.broadcast.config.BroadcastRuleConfiguration;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.algorithm.core.config.AlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableReferenceRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-11-13
 */
//@Configuration
public class Config2 {

    @Bean
    public DataSource getDataSource() throws SQLException {
        return ShardingSphereDataSourceFactory.createDataSource(
                createDataSourceMap(),
                List.of(createShardingRuleConfiguration()),
                new Properties()
        );
    }

    private ShardingRuleConfiguration createShardingRuleConfiguration() {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTables().add(getLogTableRuleConfiguration());
        Properties props = new Properties();
//        props.setProperty("algorithm-expression", "t_log_$->{create_date_time.substring(0, 7)}");
//        props.setProperty("algorithm-expression", "t_log_${create_date_time.substring(0, 4)}${create_date_time.substring(5, 7)}");
//        props.setProperty("algorithm-expression", "t_log_${create_date_time.year}${create_date_time.month.toString}");
        props.setProperty("algorithm-expression", "t_log_${create_date_time.toString().substring(0, 4)}${create_date_time.toString().substring(5, 7)}");
//        props.setProperty("algorithm-expression", "t_log_${create_date_time.toString().substring(0, 4)}");
//        props.setProperty("algorithm-expression", "t_log_${create_date_time.toString().substring(0, 4)}");
//        props.setProperty("hintAlgorithmClassName", "com.fengwenyi.demo.springboot.shardingjdbc.algorithm.LogTablePreciseShardingAlgorithm");
//        shardingRuleConfiguration.getShardingAlgorithms().put("inline", new AlgorithmConfiguration("INLINE", props));
        shardingRuleConfiguration.getShardingAlgorithms().put("inline", new AlgorithmConfiguration("INLINE", props));
        return shardingRuleConfiguration;
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        // 配置第 1 个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceMap.put("ds_0",dataSource1);
        return dataSourceMap;
    }

    private ShardingTableRuleConfiguration getLogTableRuleConfiguration() {
//        ShardingTableRuleConfiguration result = new ShardingTableRuleConfiguration("t_log", "ds_0.t_log_${202411..202412}");
        ShardingTableRuleConfiguration result = new ShardingTableRuleConfiguration("t_log", "ds_0.t_log_${202411..202412}");
        result.setTableShardingStrategy(new StandardShardingStrategyConfiguration("create_date_time", "inline"));
//        result.setTableShardingStrategy(new StandardShardingStrategyConfiguration("create_date_time", "LOG_SHARDING_ALGORITHM"));
        return result;
    }
}

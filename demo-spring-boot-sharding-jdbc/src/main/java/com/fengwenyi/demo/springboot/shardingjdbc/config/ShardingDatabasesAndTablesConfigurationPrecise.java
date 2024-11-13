//package com.fengwenyi.demo.springboot.shardingjdbc.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.shardingsphere.broadcast.config.BroadcastRuleConfiguration;
//import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
//import org.apache.shardingsphere.infra.algorithm.core.config.AlgorithmConfiguration;
//import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableReferenceRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.strategy.audit.ShardingAuditStrategyConfiguration;
//import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
//import org.apache.shardingsphere.sharding.api.config.strategy.sharding.ShardingStrategyConfiguration;
//import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.*;
//
///**
// * @author <a href="https://fengwenyi.com">Erwin Feng</a>
// * @since 2024-11-13
// */
//@Configuration
//public class ShardingDatabasesAndTablesConfigurationPrecise {
//
//    @Bean
//    public DataSource getDataSource() throws SQLException {
//        return ShardingSphereDataSourceFactory.createDataSource(
//                createDataSourceMap(),
//                Arrays.asList(createShardingRuleConfiguration(), createBroadcastRuleConfiguration()),
//        new Properties());
//    }
//
//    private ShardingRuleConfiguration createShardingRuleConfiguration() {
//        ShardingRuleConfiguration result = new ShardingRuleConfiguration();
//        result.getTables().add(getOrderTableRuleConfiguration());
//        result.getTables().add(getOrderItemTableRuleConfiguration());
////        result.getBindingTableGroups().add(new ShardingTableReferenceRuleConfiguration("foo", "t_order, t_order_item"));
//        result.setDefaultDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("create_date_time", "inline"));
//        result.setDefaultTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "standard_test_tbl"));
//        Properties props = new Properties();
//        props.setProperty("algorithm-expression", "demo_ds_${user_id % 2}");
//        result.getShardingAlgorithms().put("inline", new AlgorithmConfiguration("INLINE", props));
//        result.getShardingAlgorithms().put("standard_test_tbl", new AlgorithmConfiguration("STANDARD_TEST_TBL", new Properties()));
//        result.getKeyGenerators().put("snowflake", new AlgorithmConfiguration("SNOWFLAKE", new Properties()));
//        result.getAuditors().put("sharding_key_required_auditor", new AlgorithmConfiguration("DML_SHARDING_CONDITIONS", new Properties()));
//        return result;
//    }
//
//    private ShardingTableRuleConfiguration getOrderTableRuleConfiguration() {
//        ShardingTableRuleConfiguration result = new ShardingTableRuleConfiguration("t_log", "ds_0.t_log_${202411..202412}");
//        result.setTableShardingStrategy(new StandardShardingStrategyConfiguration("order_id", "standard_test_tbl"));
//        result.setTableShardingStrategy(new ShardingStrategyConfiguration() {
//            @Override
//            public String getShardingAlgorithmName() {
//                return "";
//            }
//
//            @Override
//            public String getType() {
//                return "";
//            }
//        });
//        return result;
//    }
//
//    private ShardingTableRuleConfiguration getOrderItemTableRuleConfiguration() {
//        ShardingTableRuleConfiguration result = new ShardingTableRuleConfiguration("t_order_item", "demo_ds_${0..1}.t_order_item_${[0, 1]}");
//        result.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("order_item_id", "snowflake"));
//        return result;
//    }
//
//    private Map<String, DataSource> createDataSourceMap() {
//
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//        // 配置第 1 个数据源
//        HikariDataSource dataSource1 = new HikariDataSource();
//        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSourceMap.put("ds_0",dataSource1);
//        return dataSourceMap;
//    }
//
//    private BroadcastRuleConfiguration createBroadcastRuleConfiguration() {
//        return new BroadcastRuleConfiguration(Collections.singletonList("t_address"));
//    }
//
//}

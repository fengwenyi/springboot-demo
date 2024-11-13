//package com.fengwenyi.demo.springboot.shardingjdbc.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
//import org.apache.shardingsphere.sharding.algorithm.sharding.inline.InlineShardingAlgorithm;
//import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
//import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
//import org.apache.shardingsphere.sharding.api.sharding.ShardingValue;
//import org.apache.shardingsphere.sharding.route.strategy.ShardingStrategy;
//
//import javax.sql.DataSource;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
//import static org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory.createDataSource;
//
///**
// * @author <a href="https://fengwenyi.com">Erwin Feng</a>
// * @since 2024-11-13
// */
//public class ShardingJdbcConfig {
//
//    public DataSource getDataSource() {
//
//        Map<String, DataSource> dataSourceMap = createDataSourceMap();
//
//        // 创建分片规则
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().add(createTableRuleConfiguration());
//
//        // 创建 Sharding 数据源
//        DataSource shardingDataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new HashMap<String, Object>(), new Properties());
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
//    public static DataSource createShardingDataSource() throws Exception {
//
//        // 创建分片规则配置
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//
//        // 配置表规则
//        TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration();
//        tableRuleConfig.setLogicTable("t_log");
//        tableRuleConfig.setActualDataNodes("ds_0.t_log_${202411..202412}");
//
//        // 设置分片策略
//        tableRuleConfig.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration(
//                "create_date_time", new InlineShardingAlgorithm() {
//            @Override
//            public String doSharding(org.apache.shardingsphere.api.algorithm.sharding.ShardingValue shardingValue) {
//                // 根据 create_date_time 生成分片表
//                String createDateTime = shardingValue.getValue().toString();
//                return "t_log_" + createDateTime.substring(0, 7).replace("-", "");
//            }
//
//            @Override
//            public Properties getProps() {
//                return new Properties();
//            }
//
//            @Override
//            public void setProps(Properties properties) {
//                // 配置参数（如有）
//            }
//        }));
//
//        // 将表规则添加到分片规则配置
//        shardingRuleConfig.getTableRuleConfigs().add(tableRuleConfig);
//
//        // 创建并返回 Sharding 数据源
//        return ShardingSphereDataSourceFactory.createDataSource(createShardingDataSource(), Arrays.asList(createShardingRuleConfiguration(), createBroadcastRuleConfiguration(), new Properties());
//    }
//
//    // 创建表分片规则
//    private static TableRuleConfiguration createTableRuleConfiguration() {
//        TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration();
//        tableRuleConfig.setLogicTable("t_log");
//        tableRuleConfig.setActualDataNodes("ds_0.t_log_${202411..202412}");
//
//        // 设置分片列和分片算法
//        tableRuleConfig.setTableShardingStrategyConfig(new ShardingStrategy("create_date_time", new InlineShardingAlgorithm() {
//            @Override
//            public String doSharding(ShardingValue shardingValue) {
//                // 根据 create_date_time 列生成分片规则
//                String createDateTime = shardingValue.getValue().toString();
//                return "t_log_" + createDateTime.substring(0, 7).replace("-", "");
//            }
//
//            @Override
//            public Properties getProps() {
//                return null;
//            }
//
//            @Override
//            public void setProps(Properties properties) {
//                // 配置相关参数（如果有）
//            }
//        }));
//
//        return tableRuleConfig;
//    }
//
//}

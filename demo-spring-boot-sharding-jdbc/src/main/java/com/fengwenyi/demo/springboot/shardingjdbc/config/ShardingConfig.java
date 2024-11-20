package com.fengwenyi.demo.springboot.shardingjdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.algorithm.core.config.AlgorithmConfiguration;
import org.apache.shardingsphere.infra.config.rule.RuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-11-13
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class ShardingConfig {

    private final ErwinDatasourceConfig erwinDatasourceConfig;

    @Bean
    public DataSource shardingDataSource() {
        try {
            log.info("shardingConfig: {}", erwinDatasourceConfig.toString());
            Properties properties = new Properties();
            Map<String, String> props = erwinDatasourceConfig.getProps();
            if (!CollectionUtils.isEmpty(props)) {
                properties.putAll(props);
            }
            List<ShardingRuleConfiguration> shardingConfigs = shardingList();
            if (CollectionUtils.isEmpty(shardingConfigs)) {
                throw new RuntimeException("shardingConfigs is empty");
            }
            Collection<RuleConfiguration> ruleConfigs = new ArrayList<>(shardingConfigs);
            return ShardingSphereDataSourceFactory.createDataSource(
                    createDataSourceMap(),
                    ruleConfigs,
                    properties
            );
        } catch (SQLException e) {
            log.error("shardingConfig, exception: {}", e);
            throw new RuntimeException(e);
        }
    }

    private List<ShardingRuleConfiguration> shardingList() {
        Map<String, ErwinDatasourceConfig.Sharding> shardingMap = erwinDatasourceConfig.getSharding();
        if (CollectionUtils.isEmpty(shardingMap)) {
            return null;
        }
        List<ShardingRuleConfiguration> shardingList = new ArrayList<>();
        for (Map.Entry<String, ErwinDatasourceConfig.Sharding> shardingEntry : shardingMap.entrySet()) {
            ErwinDatasourceConfig.Sharding sharding = shardingEntry.getValue();
            ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
            ShardingTableRuleConfiguration table = new ShardingTableRuleConfiguration(shardingEntry.getKey(), sharding.getActualDataNodes());
            table.setTableShardingStrategy(new StandardShardingStrategyConfiguration(sharding.getColumn(), sharding.getAlgorithmName()));
            shardingRuleConfiguration.getTables().add(table);
            Map<String, ErwinDatasourceConfig.Algorithm> algorithmMap = sharding.getAlgorithm();
            Map<String, AlgorithmConfiguration> algorithmConfigurationMap = new HashMap<>();
            for (Map.Entry<String, ErwinDatasourceConfig.Algorithm> algorithmEntry : algorithmMap.entrySet()) {
                ErwinDatasourceConfig.Algorithm algorithmEntryValue = algorithmEntry.getValue();
                AlgorithmConfiguration algorithmConfiguration = new AlgorithmConfiguration(algorithmEntryValue.getType(), algorithmEntryValue.getProps());
                algorithmConfigurationMap.put(algorithmEntry.getKey(), algorithmConfiguration);
            }
            shardingRuleConfiguration.getShardingAlgorithms().putAll(algorithmConfigurationMap);
            shardingList.add(shardingRuleConfiguration);
        }
        return shardingList;
    }

    private Map<String, DataSource> createDataSourceMap() {

        Map<String, ErwinDatasourceConfig.DataSource> dataSourceConfigMap = erwinDatasourceConfig.getDataSource();
        if (CollectionUtils.isEmpty(dataSourceConfigMap)) {
            return null;
        }

        Map<String, DataSource> dataSourceMap = new HashMap<>();

        for (Map.Entry<String, ErwinDatasourceConfig.DataSource> entry : dataSourceConfigMap.entrySet()) {
            DruidDataSource dataSource = new DruidDataSource();
//            dataSource.setDriverClassName(entry.getValue().getDriverClassName());
            dataSource.setUrl(entry.getValue().getUrl());
            dataSource.setUsername(entry.getValue().getUsername());
            dataSource.setPassword(entry.getValue().getPassword());
            dataSource.setValidationQuery("SELECT 1");//用来检测连接是否有效
            dataSource.setTestOnBorrow(false);//借用连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
            dataSource.setTestOnReturn(false);//归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
            //连接空闲时检测，如果连接空闲时间大于timeBetweenEvictionRunsMillis指定的毫秒，执行validationQuery指定的SQL来检测连接是否有效
            dataSource.setTestWhileIdle(true);//如果检测失败，则连接将被从池中去除
            dataSource.setTimeBetweenEvictionRunsMillis(60000);//1分钟
            dataSource.setMaxActive(20);
            dataSource.setInitialSize(5);

            dataSourceMap.put(entry.getKey(), dataSource);
        }

        return dataSourceMap;
    }

}

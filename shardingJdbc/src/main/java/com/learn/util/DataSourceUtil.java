package com.learn.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataSourceUtil {

    public static DataSource generateDataSource(){
        Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();

        //配置第一个数据源
        BasicDataSource dataSource1 = new BasicDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/ds0");
        dataSource1.setUsername("root");
        dataSource1.setPassword("123456");

        dataSourceMap.put("ds0", dataSource1);

        //配置第二个数据源
        BasicDataSource dataSource2 = new BasicDataSource();
        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource2.setUrl("jdbc:mysql://localhost:3306/ds1");
        dataSource2.setUsername("root");
        dataSource2.setPassword("123456");

        dataSourceMap.put("ds1", dataSource2);

        //配置order表规则
        TableRuleConfiguration orderTableRuleConfig =
                new TableRuleConfiguration("t_order","ds${0..1}.order_${1..2}");
        orderTableRuleConfig.setKeyGeneratorConfig(getKeyGeneratorConfiguration());

        //配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new
                InlineShardingStrategyConfiguration("status","ds${status % 2}"));

        //配置分表策略
        orderTableRuleConfig.setTableShardingStrategyConfig(new
                InlineShardingStrategyConfiguration("id","order_${id % 2 + 1}"));

        //配置分片规则
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(orderTableRuleConfig);

        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");

        //获取数据源对象
        DataSource dataSource = null;
        try {
            dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap,
                    shardingRuleConfiguration, properties);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dataSource;
    }


    /**
     * 雪花算法生成主键
     * @return
     */
    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration(){
        KeyGeneratorConfiguration key = new KeyGeneratorConfiguration("SNOWFLAKE", "id");
        return key;
    }


}

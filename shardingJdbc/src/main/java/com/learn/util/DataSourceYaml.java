package com.learn.util;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class DataSourceYaml {

    public static DataSource getDataSource() throws IOException, SQLException {

        String url = "C:/devData/IdeaProjects/ShardingSphere/shardingJdbc/target/classes/datasource.yml";

        System.out.println(url.toString());

        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(new File
                (url.toString()));

        return dataSource;
    }
}

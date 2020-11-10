package com.learn.sharding;

import com.learn.util.DataSourceUtil;
import com.learn.util.DataSourceYaml;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShardingJdbcYaml {


    @Test
    public void testSelect() throws IOException, SQLException {
        DataSource dataSource = DataSourceYaml.getDataSource();
        String sql = "select * from t_order where status = ? ";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,3);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            System.out.println(rs.getObject(1) + " " + rs.getObject(2));
        }
        rs.close();
        preparedStatement.close();
        connection.close();
    }
}

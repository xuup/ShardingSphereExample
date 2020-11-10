package com.learn.sharding;

import com.learn.util.DataSourceUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShardingJdbc {

    private static final Logger logger = LoggerFactory.getLogger(ShardingJdbc.class);

    private static final DataSource dataSource = DataSourceUtil.generateDataSource();


    public static void main(String[] args) {

        //获取数据源

    }


    @Test
    public void testInsert() throws SQLException {
        Connection connection = dataSource.getConnection();
        String sql = "insert into t_order (order_name, order_user, status) values ( ?, ?, ? )";
        logger.info(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        for(int i = 0; i < 5; i++){
            statement.setString(1,"order_" + i);
            statement.setString(2,"t_user" + i);
            statement.setInt(3,i);
            statement.executeUpdate();
        }
        logger.info("插入数据完毕");
        statement.close();
        connection.close();
    }


    @Test
    public void TestSelect() throws SQLException {
        String sql = "select * from t_order";
            Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            System.out.println(rs.getObject(1) + " " + rs.getObject(2));
        }
        rs.close();
        preparedStatement.close();
        connection.close();
    }
}

package com.learn.shardingspringboot;

import com.learn.shardingspringboot.mapper.ShardingMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
class ShardingspringbootApplicationTests {

	Logger logger = LoggerFactory.getLogger(ShardingspringbootApplicationTests.class);

	@Resource
	private DataSource dataSource;

	@Resource
	private ShardingMapper mapper;

	@Test
	void contextLoads() {
		logger.info("测试开始...");
		logger.info("获取数据源{}",dataSource);
	}

	@Test
	public void testSelect() throws SQLException {
		String sql = "select * from t_order";
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()){
			logger.info("{}--{}",rs.getObject(1),rs.getObject(2));
		}
		rs.close();
		statement.close();
		connection.close();
	}


	@Test
	public void testInsertMybatis(){
		logger.info("mybatis 插入开始");
		for(int i=5;i<10;i++){
			mapper.insert("order-name_" + i,"user_" + i, i);
		}
		logger.info("mybatis 插入结束");
	}

}

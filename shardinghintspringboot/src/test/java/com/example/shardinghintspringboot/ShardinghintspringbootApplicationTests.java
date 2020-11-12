package com.example.shardinghintspringboot;

import org.apache.shardingsphere.api.hint.HintManager;
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
class ShardinghintspringbootApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(ShardinghintspringbootApplicationTests.class);

	@Resource
	private DataSource dataSource;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSelect() throws SQLException {
		logger.info("datasource{} ", dataSource);
		String sql = "select * from t_order";
		HintManager hintManager = HintManager.getInstance();
		Connection connection = dataSource.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		hintManager.setDatabaseShardingValue(3);
		ResultSet rs = statement.executeQuery();
		while (rs.next()){
			logger.info("{} - {}",rs.getObject(1),rs.getObject(2));
		}
	}

}

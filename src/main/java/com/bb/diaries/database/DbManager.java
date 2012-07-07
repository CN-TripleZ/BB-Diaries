package com.bb.diaries.database;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.log4j.Logger;

public class DbManager {

	private static Logger LOGGER = Logger.getLogger(DbManager.class);

	private static DataSource dataSource;
	private static Properties jdbcProperties = new Properties();;

	static {
		try {
			jdbcProperties.load(DbManager.class.getResourceAsStream("jdbc.properteis"));
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("加载数据库配置失败...");
		}
	}
	
	private DbManager() {
	}

	public static QueryRunner getQueryRunner() {
		if (DbManager.dataSource == null) {
			BasicDataSource dbcpDataSource = new BasicDataSource();
			dbcpDataSource.setUrl(jdbcProperties.getProperty("jdbc.url"));
			dbcpDataSource.setDriverClassName(jdbcProperties.getProperty("jdbc.driver"));
			dbcpDataSource.setUsername(jdbcProperties.getProperty("jdbc.username"));
			dbcpDataSource.setPassword(jdbcProperties.getProperty("jdbc.password"));
			dbcpDataSource.setDefaultAutoCommit(true);
			dbcpDataSource.setMaxActive(Integer.valueOf(jdbcProperties.getProperty("jdbc.MaxActive")));
			dbcpDataSource.setMaxIdle(Integer.valueOf(jdbcProperties.getProperty("jdbc.MaxIdle")));
			dbcpDataSource.setMaxWait(Integer.valueOf(jdbcProperties.getProperty("jdbc.MaxWait")));
			DbManager.dataSource = (DataSource) dbcpDataSource;
		}
		return new QueryRunner(DbManager.dataSource);
	}

}

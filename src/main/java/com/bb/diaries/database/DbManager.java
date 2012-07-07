package com.bb.diaries.database;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;

public class DbManager {
    private static DataSource dataSource;
    private DbManager(){
    }
    
    public static QueryRunner getQueryRunner(){
        if(DbManager.dataSource==null){
            //配置dbcp数据源
            BasicDataSource dbcpDataSource = new BasicDataSource();
            dbcpDataSource.setUrl("jdbc:mysql://localhost:3306/bb_diaries?useUnicode=true&characterEncoding=UTF-8");
            dbcpDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dbcpDataSource.setUsername("root");
            dbcpDataSource.setPassword("root");
            dbcpDataSource.setDefaultAutoCommit(true);
            dbcpDataSource.setMaxActive(100);
            dbcpDataSource.setMaxIdle(30);
            dbcpDataSource.setMaxWait(500);
            DbManager.dataSource = (DataSource)dbcpDataSource;
            System.out.println("Initialize dbcp...");
        }
        return new QueryRunner(DbManager.dataSource);
    }
	
	public static void main(String[] args) {
		QueryRunner qr = DbManager.getQueryRunner();
		try {
			Object[] result =  qr.query("select 1 from dual", new ArrayHandler());
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

package com.bb.diaries.database;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;

import junit.framework.TestCase;

public class DbManagerTest extends TestCase {
	
	public void testConnection() throws SQLException {
		QueryRunner qr = DbManager.getQueryRunner();
		Object[] result = qr.query("select 1 from dual", new ArrayHandler());
		assertEquals("1", result[0] + "");
	}
}

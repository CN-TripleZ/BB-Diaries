package com.bb.diaries.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.Logger;

import com.bb.diaries.database.DbManager;
import com.bb.diaries.model.Photo;

public class PhotoDao {
	
	private static final Logger LOGGER = Logger.getLogger(PhotoDao.class);
	
	private final static PhotoDao DAO = new PhotoDao();

	public static PhotoDao getInstance() {
		return DAO;
	}
	
	public Photo get(int id) {
		String sql = "select * from " +getTableName() + " where id = ?";
		Object params[] = { id };
		QueryRunner run = DbManager.getQueryRunner();
		
		Photo photo = new Photo();
		try {
			photo = run.query(sql,new BeanHandler(Photo.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("查询图片异常.[id=" + id + "]");
		}
		return photo;
	}
	
	public List<Photo> findByUserId(int userId) {
		String sql = "select * from " +getTableName() + " where userId = ?";
		Object params[] = { userId };
		QueryRunner run = DbManager.getQueryRunner();
		ResultSetHandler<List<Photo>> handler = new BeanListHandler<Photo>(Photo.class);
		List<Photo> list = new ArrayList<Photo>();
		try {
			list = run.query(sql, handler, params);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("查询用户图片异常.[userId=" + userId + "]");
		}
		return list;
	}
	
	public boolean save(Photo photo) {
		String sql = "insert into " + getTableName() + " (id,uploadTime,userId,description,path) values (?,?,?,?,?)";
		Object params[] = { photo.getId(), photo.getUploadTime(), photo.getUserId(), photo.getDescription(), photo.getPath()};
		QueryRunner run = DbManager.getQueryRunner();

		boolean flag = false;
		try {
			run.update(sql, params);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			LOGGER.error("保存图片异常.");
		}
		return flag;
	}

	public boolean update(Photo photo) {
		String sql = "update " + getTableName() + " set description=? where id=?";
		Object params[] = { photo.getDescription() };
		QueryRunner run = DbManager.getQueryRunner();

		boolean flag = false;
		try {
			run.update(sql, params);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			LOGGER.error("更新图片异常.");
		}
		return flag;
	}

	public boolean delete(int id) {
		String sql = "delete from " + getTableName() + " where id=?";
		Object params[] = { id };
		QueryRunner run = DbManager.getQueryRunner();
		boolean flag = false;
		try {
			run.update(sql, params);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
			LOGGER.error("删除图片异常.");
		}
		return flag;
	}

	public List<Photo> findAll() {
		String sql = "select * from " + getTableName();
		QueryRunner run = DbManager.getQueryRunner();
		ResultSetHandler<List<Photo>> handler = new BeanListHandler<Photo>(Photo.class);
		List<Photo> list = new ArrayList<Photo>();
		try {
			list = run.query(sql, handler);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error("查询所有有图片异常.");
		}
		return list;
	}

	public String getTableName() {
		return "t_photo";
	}
}

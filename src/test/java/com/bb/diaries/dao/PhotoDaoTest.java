package com.bb.diaries.dao;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.bb.diaries.model.Photo;

public class PhotoDaoTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCURD() {

		Photo photo = new Photo();
		photo.setPath("/jeffrey/20120205/1206");
		photo.setDescription("今天很开心!");
		photo.setUploadTime(new Date());
		photo.setUserId(-1);
		boolean flag = PhotoDao.getInstance().save(photo);
		assertTrue(flag);
		
		List<Photo> pList = PhotoDao.getInstance().findAll();
		assertTrue(pList.size() > 0);
		
		List<Photo> pUserList = PhotoDao.getInstance().findByUserId(-1);
		assertTrue(pUserList.size() > 0);
		
		flag = PhotoDao.getInstance().delete(pList.get(0).getId());
		assertTrue(flag);

	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}

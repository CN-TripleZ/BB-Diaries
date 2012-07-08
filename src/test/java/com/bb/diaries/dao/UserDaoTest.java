package com.bb.diaries.dao;

import java.util.List;

import junit.framework.TestCase;

import com.bb.diaries.model.User;

public class UserDaoTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testCURD() {

		User user = new User();
		user.setName("jeffrey");
		user.setEmail("shine8606@gmail.com");
		user.setPassword("abc1234");
		boolean flag = UserDao.getInstance().save(user);
		assertTrue(flag);
		
		User u = UserDao.getInstance().get("jeffreyzhang");
		assertNull(u);
		
		
		u = UserDao.getInstance().get("jeffrey");
		assertNotNull(u);
		User u1 = UserDao.getInstance().get(u.getId());
		assertNotNull(u1);
		assertEquals(u1.getName(), "jeffrey");
		
		u1.setName("jeffreyzhang");
		flag = UserDao.getInstance().update(u1);
		assertTrue(flag);
		User nUser = UserDao.getInstance().get(u1.getId());
		assertEquals(nUser.getName(), "jeffreyzhang");
		
		List<User> uList = UserDao.getInstance().findAll();
		assertTrue(uList.size() > 0);
		
		flag = UserDao.getInstance().delete(u1.getId());
		assertTrue(flag);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
}

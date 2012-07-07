package com.bb.diaries.dao;

import java.util.List;

import junit.framework.TestCase;

import com.bb.diaries.model.User;

public class UserDaoTest extends TestCase {

	public void testCURD() {
		User user = new User();
		user.setId("100000");
		user.setName("jeffrey");
		user.setEmail("shine8606@gmail.com");
		user.setPassword("abc1234");
		boolean flag = UserDao.getInstance().save(user);
		assertTrue(flag);
		
		User u = UserDao.getInstance().get("1");
		assertNull(u);
		
		
		u = UserDao.getInstance().get("100000");
		assertNotNull(u);
		assertEquals(u.getName(), "jeffrey");
		
		user.setName("jeffreyzhang");
		flag = UserDao.getInstance().update(user);
		assertTrue(flag);
		User nUser = UserDao.getInstance().get("100000");
		assertEquals(nUser.getName(), "jeffreyzhang");
		
		List<User> uList = UserDao.getInstance().findAll();
		assertTrue(uList.size() > 0);
		
		flag = UserDao.getInstance().delete("100000");
		assertTrue(flag);
	}
}

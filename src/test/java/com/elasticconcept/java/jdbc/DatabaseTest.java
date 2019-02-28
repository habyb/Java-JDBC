package com.elasticconcept.java.jdbc;

import java.util.List;

import org.junit.Test;

import com.elasticconcept.java.jdbc.dao.UserDAO;
import com.elasticconcept.java.jdbc.model.BeanUserPhone;
import com.elasticconcept.java.jdbc.model.Phone;
import com.elasticconcept.java.jdbc.model.User;

public class DatabaseTest {

	@Test
	public void initDatabase() {
		
		UserDAO userDAO = new UserDAO();
		User user = new User();
		
		user.setName("Kyle");
		user.setEmail("me@kyle.com");
		
		userDAO.save(user);
	}
	
	@Test
	public void initList() {
		
		UserDAO dao = new UserDAO();
		
		try {
			List<User> list = dao.list();
			
			for (User user : list) {
				System.out.println(user);
				System.out.println("-------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initSearch() {
		
		UserDAO dao = new UserDAO();
		
		try {
			User user = dao.search(5L);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initUpdate() {
		
		try {
			UserDAO dao = new UserDAO();
			
			User objectDB = dao.search(5L);
			
			objectDB.setName("Updated name with update method");
			
			dao.update(objectDB);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initDelete() {
		
		try {
			UserDAO dao = new UserDAO();
			dao.delete(6L);
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void insertPhone() {
		
		Phone phone = new Phone();
		phone.setNumber("(21) 4356 7533");
		phone.setType("casa");
		phone.setPersonuser(9L);
		
		UserDAO dao = new UserDAO();
		dao.savePhone(phone);
	}
	
	@Test
	public void testLoadingUserPhone() {
		
		UserDAO dao = new UserDAO();
		
		List<BeanUserPhone> beanUserPhones = dao.listUserPhone(9L);
		
		for(BeanUserPhone beanUserPhone : beanUserPhones) {
			
			System.out.println(beanUserPhone);
			System.out.println("---");
		}
	}
}

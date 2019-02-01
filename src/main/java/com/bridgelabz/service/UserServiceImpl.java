package com.bridgelabz.service;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.dao.UserDao;
import com.bridgelabz.model.User;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public boolean register(User user, HttpServletRequest request) {
		int id = userDao.register(user);
		if (id > 0) {
			return true;
		}
		return false;
	}

	public User getUserByEmailId(String emailid, HttpServletRequest request)
	{
		User user1=userDao.getUserByEmailId(emailid);

		return user1;
	}




	public User updateUser(String emailid,User user,HttpServletRequest request)
	{
		User user12=userDao.getUserByEmailId(emailid);
		user12.setMobileNumber(user.getMobileNumber());
		user12.setName(user.getName());
		user12.setPassword(user.getPassword());
		userDao.updateUser(emailid, user12);
		return user;
	}


	public User deleteUser(String emailid, User user, HttpServletRequest request) {
		User user12=userDao.getUserByEmailId(emailid);
		userDao.deleteUser(emailid, user12);
		return user12;

	}

}




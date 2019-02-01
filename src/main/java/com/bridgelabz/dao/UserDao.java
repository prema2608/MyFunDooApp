package com.bridgelabz.dao;

import com.bridgelabz.model.User;

public interface UserDao {

	int register(User user);

	User login(User user);
	
	User getUserByEmailId(String emailid);

	User updateUser(String emailid,User user);

	User deleteUser(String emailid,User user);



}

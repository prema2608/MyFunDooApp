package com.bridgelabz.service;

import javax.servlet.http.HttpServletRequest;

import com.bridgelabz.model.User;

public interface UserService {

	boolean register(User user, HttpServletRequest request);
	User getUserByEmailId(String emailid, HttpServletRequest request);
	User updateUser(String emailid, User user, HttpServletRequest request);
	User deleteUser(String emailid, User user, HttpServletRequest request);
}

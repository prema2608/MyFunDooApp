package com.bridgelabz.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;
import com.bridgelabz.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> registerUser(@RequestBody User user, HttpServletRequest request) {
		try {
			if (userService.register(user, request))
				return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}


	@RequestMapping(value = "/getdetails", method = RequestMethod.GET)
	public ResponseEntity<?> getUserByEmailId( @RequestParam("emailid") String emailid, HttpServletRequest request) {

		User user = userService.getUserByEmailId(emailid, request);
		if(user!= null) {
			return new ResponseEntity<User>(user, HttpStatus.FOUND);
		}else
			return new ResponseEntity<String>("User not Found by given name",HttpStatus.NOT_FOUND);
	}



	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Void> updateUser(@RequestParam("emailid") String emailid,@RequestBody User user, HttpServletRequest request)
	{
		try {
			if (userService.updateUser(emailid,user, request)!=null)
				return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	}



	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@RequestParam("emailid") String emailid,@RequestBody User user, HttpServletRequest request)
	{
		try {
			if (userService.deleteUser(emailid,user, request) != null)
				return new ResponseEntity<User>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<User>(HttpStatus.CONFLICT);
	}
































	//	   
	//	    @RequestMapping(value = "/update", method = RequestMethod.POST)
	//	    public ResponseEntity<Void> update(@RequestParam("emailId") String emailId,@RequestBody User user, HttpServletRequest request)
	//	    {
	//	        try {
	//	            if (userService.update(emailId,user, request)!=null)
	//	                return new ResponseEntity<Void>(HttpStatus.OK);
	//	        } catch (Exception e) {
	//	            e.printStackTrace();
	//	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	//	        }
	//	        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	//	    }
	//	   
	//	    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	//	    public ResponseEntity<Void> deleteUser(@RequestParam("emailId") String emailId, HttpServletRequest request) {
	//	        try {
	//	            if (userService.deleteUser(emailId, request))
	//	                return new ResponseEntity<Void>(HttpStatus.OK);
	//	        } catch (Exception e) {
	//	            e.printStackTrace();
	//	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	//	        }
	//	        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	//	    }
}
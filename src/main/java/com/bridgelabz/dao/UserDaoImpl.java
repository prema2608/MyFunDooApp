package com.bridgelabz.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.model.User;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;

	public int register(User user) {
		int userId = 0;
		Session session = sessionFactory.getCurrentSession();
		userId = (Integer) session.save(user);
		return userId;
	}

	public User login(User user) {
		Session session = sessionFactory.getCurrentSession();
		String hqlQuery = "from UserDetails where userName = :userName and password =:password";
		Query query = session.createQuery(hqlQuery);
		query.setParameter("userName", user.getEmailid());
		query.setParameter("password", user.getPassword());
		User existingUser = (User) query.uniqueResult();
		return existingUser;
	}

	public User getUserByEmailId(String emailid)
	{

		Session session = sessionFactory.openSession();
		@SuppressWarnings("unused")
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User");
		@SuppressWarnings("unchecked")
		List<User> empList = query.list();
		for(User emp : empList){
			System.out.println("List of users::"+emp.getId()+","+emp.getName()+","+emp.getEmailid()+","+emp.getMobileNumber());
		}
		query = session.createQuery("from User where emailid= :emailid");
		query.setString("emailid", emailid);
		User emp = (User) query.uniqueResult();
		System.out.println("User detail is="+emp.getName()+","+emp.getEmailid()+","+emp.getMobileNumber());
		session.close();
		return emp;
	}


	public User updateUser(String emailid,User user)
	{
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
		return null;
	}



	public User deleteUser(String emailid,User user) 
	{

		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		//session.delete(user);
		Query query = session.createQuery("DELETE from User u where u.emailid= :emailid");
		query.setString("emailid", emailid);
		query.executeUpdate();
		tx.commit();
		session.close();
		return null;


	}




}
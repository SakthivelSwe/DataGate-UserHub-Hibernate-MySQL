package org.jsp.userapp.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jsp.userapp.dto.User;

public class UserDao {
	
	Session s = new Configuration().configure().buildSessionFactory().openSession();
	Transaction t = s.beginTransaction();
	
	//1.saveUser
	public User saveUser(User user) {
		s.save(user);
		t.commit();
		return user;
	}
	
	//2.Update User
	public User updateUser(User user) {
		User dbUser = s.get(User.class, user.getId());
		if(dbUser != null) {
			dbUser.setName(user.getName());
			dbUser.setEmail(user.getEmail());
			dbUser.setGender(user.getGender());
			dbUser.setAge(user.getAge());
			dbUser.setPhone(user.getPhone());
			dbUser.setPassword(user.getPassword());
			t.commit();
			return dbUser;
		}
		return null;
	}
	
	//3.find User by Id
	public User findUserById(int id) {
		return s.get(User.class, id);
	}
	
	//4.verifyUser Phone and Password
	
	public User verifyUser(long phone,String password) {
		Query<User> q = s.createQuery("Select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	//5.verifyUser Using Email and Password
	public User verifyUser(String email,String password) {
		Query<User> q = s.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	//6.verifyUser Using id and password
	public User verifyUser(int id,String password) {
		Query<User> q = s.createQuery("select u from User u where u.id=?1 and u.password=?2");
		q.setParameter(1, id);
		q.setParameter(2, password);
		try {
			return q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	//7.find By Name
	public List<User> findByName(String name){
		Query<User> q = s.createQuery("select u from User u where u.name=?1");
		q.setParameter(1, name);
		return q.getResultList();
		
	}
	
	//8.find by age
	public List<User> findByAge(int age){
		Query<User> q=s.createQuery("select u from User u where u.age=?1");
		q.setParameter(1, age);
		return q.getResultList();
		
	}
	
	//9.find By Gender
	public List<User> findByGender(String gender){
		Query<User> q = s.createQuery("select u from User u where u.gender=?1");
		q.setParameter(1, gender);
		return q.getResultList();
	}
	
	//10.delete user
	public boolean deleteUser(int id) {
		User user = findUserById(id);
		if(user != null) {
			s.delete(user);
			t.commit();
			return true;
		}
		return false;
	}
	
	//11.find Name by age
	public List<String> findNameByAge(int age){
		Query<String> q = s.createQuery("select u.name from User u where u.age=?1");
		q.setParameter(1, age);
		return q.getResultList();
	}
	
	
	

}

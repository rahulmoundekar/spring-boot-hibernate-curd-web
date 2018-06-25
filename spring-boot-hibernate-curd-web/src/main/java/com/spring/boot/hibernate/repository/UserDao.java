package com.spring.boot.hibernate.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boot.hibernate.model.User;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory _sessionFactory;

	private Session getSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void save(User user) {
		System.out.println("save dao"+user.getEmail()+"\t"+user.getName());
		getSession().save(user);
	}

	public void delete(User user) {
		getSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return getSession().createQuery("from User").list();
	}

	public User getByEmail(String email) {
		return (User) getSession().createQuery("from User where email = :email").setParameter("email", email)
				.uniqueResult();
	}

	public User getById(long id) {
		return (User) getSession().load(User.class, id);
	}

	public void update(User user) {
		getSession().update(user);
	}

}

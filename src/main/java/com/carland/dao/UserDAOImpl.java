package com.carland.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carland.entity.Advert;
import com.carland.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public void save(User theUser) {
		
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(theUser);

	}

	@Override
	public User findByUsername(String username) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query<User> theQuery = session.createQuery("from User where username=:uName", User.class);
		theQuery.setParameter("uName", username);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}
		
		return theUser;
	}

	@Override
	public List<Advert> getUserAdverts(User user) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Advert> theQuery = session.createQuery("from Advert where user=:userId", Advert.class);
		theQuery.setParameter("userId", user);
		
		return theQuery.getResultList();
	}

}

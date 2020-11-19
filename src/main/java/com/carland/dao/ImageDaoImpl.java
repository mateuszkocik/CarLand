package com.carland.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carland.entity.Image;

@Repository
public class ImageDaoImpl implements ImageDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(Image image) {
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(image);
	}

}

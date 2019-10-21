package com.jwt.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.model.ImageItem;
import com.jwt.model.ImageItemOrder;


/**
 * Spring Data JPA repository for the ImageItemOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public class ImageItemOrderRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ImageItemOrder save(ImageItemOrder imageItemOrder) {
		sessionFactory.getCurrentSession().saveOrUpdate(imageItemOrder);
		return imageItemOrder;
	}
}

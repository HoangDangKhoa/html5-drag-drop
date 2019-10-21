package com.jwt.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jwt.model.ImageItem;

import java.util.List;



@Repository
public class ImageItemRepository {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<ImageItem> findAllByOrderByIdAsc() {
		return sessionFactory.getCurrentSession().createQuery("from ImageItem order by id ASC")
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<ImageItem> findAllByOrderByImageItemOrderOrderAsc() {
		return sessionFactory.getCurrentSession().createQuery("from ImageItem item order by item.imageItemOrder.order ASC")
				.list();
	}
	
	public ImageItem save(ImageItem imageItem) {
		sessionFactory.getCurrentSession().saveOrUpdate(imageItem);
		return imageItem;
	}
}

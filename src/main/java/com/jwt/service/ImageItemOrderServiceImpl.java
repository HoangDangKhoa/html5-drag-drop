package com.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.ImageItemOrderRepository;
import com.jwt.dao.ImageItemRepository;
import com.jwt.model.ImageItem;
import com.jwt.model.ImageItemOrder;

import java.util.List;

/**
 * Service Implementation for managing ImageItemOrder.
 */
@Service
@Transactional
public class ImageItemOrderServiceImpl {

	@Autowired
    private ImageItemOrderRepository imageItemOrderRepository;
    
	@Autowired
    private ImageItemRepository imageItemRepository;

    /**
     * Save a imageItemOrder.
     *
     * @param imageItemOrder the entity to save
     * @return the persisted entity
     */
    public ImageItemOrder save(ImageItemOrder imageItemOrder) {
        return imageItemOrderRepository.save(imageItemOrder);
    }
    
    
    public void generateImageItemOrder() {
    	List<ImageItem> imageItems = imageItemRepository.findAllByOrderByIdAsc();
    	for (ImageItem imageItem : imageItems) {
    		if (imageItem.getImageItemOrder() == null) {
    			ImageItemOrder newItemOrder = new ImageItemOrder();
    			newItemOrder.setImageItem(imageItem);
    			newItemOrder.setOrder(imageItem.getId().intValue());
    			imageItemOrderRepository.save(newItemOrder);
    			imageItem.setImageItemOrder(newItemOrder);
    			imageItemRepository.save(imageItem);
    		}
    	}
    }
    
    public void updateItemsOrder(List<ImageItem> imageItems) {
    	for (ImageItem imageItem : imageItems) {
    		imageItemOrderRepository.save(imageItem.getImageItemOrder());
    	}
    }
}

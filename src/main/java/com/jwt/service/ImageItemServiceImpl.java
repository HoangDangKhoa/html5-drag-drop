package com.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwt.dao.ImageItemRepository;
import com.jwt.model.ImageItem;


/**
 * Service Implementation for managing ImageItem.
 */
@Service
@Transactional
public class ImageItemServiceImpl {

	@Autowired
    private ImageItemRepository imageItemRepository;

    /**
     * Save a imageItem.
     *
     * @param imageItem the entity to save
     * @return the persisted entity
     */
    public ImageItem save(ImageItem imageItem) {
        return imageItemRepository.save(imageItem);
    }
    
    /**
     * Get all the imageItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ImageItem> findAllByOrder() {
        return imageItemRepository.findAllByOrderByImageItemOrderOrderAsc();
    }
}

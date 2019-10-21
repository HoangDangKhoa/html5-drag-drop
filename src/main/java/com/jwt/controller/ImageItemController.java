package com.jwt.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.model.ImageItem;
import com.jwt.service.ImageItemOrderServiceImpl;
import com.jwt.service.ImageItemServiceImpl;

@Controller
public class ImageItemController {


	public ImageItemController() {
		System.out.println("ImageItemController()");
	}

	@Autowired
	private ImageItemServiceImpl imageItemService;
	
	@Autowired
	private ImageItemOrderServiceImpl imageItemOrderService;

	@RequestMapping(value = "/")
	public ModelAndView listImageItem(ModelAndView model) throws IOException {
		List<ImageItem> listImageItem = imageItemService.findAllByOrder();
		model.addObject("listImageItem", listImageItem);
		ObjectMapper mapper = new ObjectMapper();
		model.addObject("listImageItemString", mapper.writeValueAsString(listImageItem));
		model.setViewName("ImageItem");
		return model;
	}

	@RequestMapping(value = "/update-item-order", method = RequestMethod.POST)
	@ResponseBody
	public Boolean saveEmployee(@RequestBody String items) throws JsonParseException, JsonMappingException, IOException {
		String decodedJson = java.net.URLDecoder.decode(items, "UTF-8");
		ObjectMapper jacksonObjectMapper = new ObjectMapper();
		List<ImageItem> sortedItems =  jacksonObjectMapper.readValue(decodedJson, new TypeReference<List<ImageItem>>(){});
		imageItemOrderService.updateItemsOrder(sortedItems);
		return true;
	}

}
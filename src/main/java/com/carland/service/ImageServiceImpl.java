package com.carland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carland.dao.ImageDao;
import com.carland.entity.Advert;
import com.carland.entity.Image;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageDao imageDao;

	@Override
	public void save(MultipartFile[] files, Advert advert) {
		for(MultipartFile file : files) {
			String name = file.getOriginalFilename();
			
			Image image = new Image(advert,name);
			
			imageDao.save(image);
		}
		
	}

}

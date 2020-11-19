package com.carland.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.carland.entity.Advert;
import com.carland.entity.Image;

public interface ImageService {
	
	public void save(MultipartFile[] files,  Advert advert);
}

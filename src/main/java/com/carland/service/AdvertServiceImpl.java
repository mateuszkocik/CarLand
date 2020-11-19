package com.carland.service;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.carland.dao.AdvertDao;
import com.carland.entity.Advert;
import com.carland.entity.User;

@Service
public class AdvertServiceImpl implements AdvertService {
	
	@Autowired
	private AdvertDao advertDao;
	
	@Autowired
	private ImageService imageService;
	

	@Override
	public void saveAdvert(Advert advert, HttpServletRequest request, MultipartFile[] files) {
		
		HttpSession session = request.getSession();
		User theUser = (User)session.getAttribute("user");
		
		advert.setUser(theUser);
		setAdvertStateBasedOnUserRole(advert);
		
		advertDao.saveAdvert(advert);
		
		imageService.save(files, advert);

	}
	
	private void setAdvertStateBasedOnUserRole(Advert advert) {
		if(advert.getUser().hasRole("ADMIN")) {
			 makeAdvertActive(advert);
		}else {
			advert.setState("PENDING");
		}
	}


	@Override
	public List<Advert> getActiveAdverts() {
		return advertDao.getActiveAdverts();
	}


	@Override
	public Advert getAdvertById(int id) {
		return advertDao.getAdvertById(id);
	}


	@Override
	public void deleteAdvert(Advert advert) {
		advertDao.deleteAdvert(advert);
	}


	@Override
	public Advert getOnePendingAdvert() {
		return advertDao.getOnePendingAdvert();
	}


	@Override
	public void makeAdvertActive(Advert advert) {
		advert.setState("ACTIVE");
		advert.setExpirationDate(LocalDate.now().plusDays(14));
		advertDao.saveAdvert(advert);
	}

}

package com.carland.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.carland.entity.Advert;
import com.carland.entity.User;
import com.carland.service.AdvertService;
import com.carland.service.StorageService;
import com.carland.service.UserService;

@Controller
public class AdvertController {

	@Autowired
	private AdvertService advertService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EntityManager entityManager;

	
	@GetMapping("/sellmycar")
	public String showSellPage(Model theModel) throws IOException {
		
		if(checkIfUserHas10Adverts()) {
			return "redirect:/";
		}
		
		getMakesAndTypesAndAddToModel(theModel);
		
		Advert advert = new Advert();
		theModel.addAttribute("advert",advert);
		
		return "sellmycar";
	}

	
	
	@PostMapping("/sellmycar")
	public String saveAdvert(
			@Valid @ModelAttribute("advert") Advert advert,
			BindingResult theBindingResult, 
			HttpServletRequest request,
			Model theModel,
			@RequestParam("file") MultipartFile[] files) {
		
		if(theBindingResult.hasErrors()) {
			return "sellmycar";
		}
		
		advertService.saveAdvert(advert, request,files);
		
		storageService.store(files);
		
		return "redirect:/";
	}
	

	@GetMapping("/edit")
	public String showEditAdvert(@RequestParam("id") int id, Model theModel){
		Advert editedAdvert = advertService.getAdvertById(id);
		
		getMakesAndTypesAndAddToModel(theModel);
		getModelsAndAddToModel(theModel, editedAdvert);
		theModel.addAttribute("editedAdvert", editedAdvert);
		theModel.addAttribute("advert",editedAdvert);
		return "editAdvert";
	}

	
	@PostMapping("/edit")
	public String editAdvert(
			@Valid @ModelAttribute("advert") Advert advert,
			@RequestParam("advertId") int id,
			@RequestParam("file") MultipartFile[] files,
			BindingResult theBindingResult, 
			HttpServletRequest request,
			Model theModel){
		
		advertService.deleteAdvert(advertService.getAdvertById(id));
		
		advertService.saveAdvert(advert, request,files);
		
		storageService.store(files);
		
		return "redirect:/profile";
	}
	
	private void getMakesAndTypesAndAddToModel(Model theModel) {
		List<String> types = getNamesFromDB("type");
		List<String> makes = getNamesFromDB("make");
		
		theModel.addAttribute("types",types);
		theModel.addAttribute("makes",makes);
	}
	
	private void getModelsAndAddToModel(Model theModel, Advert editedAdvert) {
		List<String> models = getModels(editedAdvert.getMake());
		
		theModel.addAttribute("models",models);
	}
	
	private Boolean checkIfUserHas10Adverts() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userService.findByUsername(username);
		
		if(user.getAdverts().size() >= 10) {
			return true;
		}
		
		return false;
	}
	
	
	
	@GetMapping("/sellmycar/models")
	public @ResponseBody List<String> findAllModels(
			@RequestParam(value="make", required=true) String make){
		Session currentSession = entityManager.unwrap(Session.class);
		
		String sql = "select name from model where make='" + make + "'";
		
		SQLQuery query = currentSession.createSQLQuery(sql);
	    List<String> models = query.list();
	    
		return models;
	}
	
	public List<String> getNamesFromDB(String table) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		String sql = "select name from ";
		sql += table;
		
		SQLQuery query = currentSession.createSQLQuery(sql);
	    List<String> types = query.list();
	    
	    return types;
	}
	
	public List<String> getModels(String make){
		Session currentSession = entityManager.unwrap(Session.class);
		
		String sql = "select name from model where make='" + make + "'";
		
		SQLQuery query = currentSession.createSQLQuery(sql);
		List<String> models = query.list();
		
		return models;
	}
}

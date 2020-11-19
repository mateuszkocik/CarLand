package com.carland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carland.entity.Advert;
import com.carland.service.AdvertService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	private AdvertService advertService;
	
	@GetMapping
	public String showManage(Model theModel) {
		
		Advert advert = advertService.getOnePendingAdvert();
		
		if(advert == null) {
			return "redirect:/";
		}
		
		theModel.addAttribute("user", advert.getUser());
		theModel.addAttribute("advert",advert);
		
		return "admin";
	}
	
	@GetMapping("/decision")
	public String manageAdvert(
			@RequestParam("id") int id,
			@RequestParam("accepted") Boolean accepted) {
		
		Advert advert = advertService.getAdvertById(id);
		
		if(accepted) {
			advertService.makeAdvertActive(advert);
		}else {
			advertService.deleteAdvert(advert);
		}
		return "redirect:/admin";
	}
	
}

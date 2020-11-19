package com.carland.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.carland.entity.Advert;
import com.carland.entity.User;
import com.carland.user.CrmUser;

public interface UserService extends UserDetailsService{

	public void saveUser(CrmUser theUser);
	public User findByUsername(String username);
	public List<Advert> getUserAdverts(User user);
}

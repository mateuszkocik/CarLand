package com.carland.dao;

import java.util.List;

import com.carland.entity.Advert;
import com.carland.entity.User;

public interface UserDAO {

	public void save(User theUser);
	public User findByUsername(String username);
	public List<Advert> getUserAdverts(User user);
}

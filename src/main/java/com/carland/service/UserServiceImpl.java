package com.carland.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carland.dao.RoleDao;
import com.carland.dao.UserDAO;
import com.carland.entity.Advert;
import com.carland.entity.Role;
import com.carland.entity.User;
import com.carland.user.CrmUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void saveUser(CrmUser crmUser) {
		User user = new User();
		user.setUsername(crmUser.getUsername());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		user.setName(crmUser.getName());
		user.setTelephoneNumber(crmUser.getTelephoneNumber());
		user.setCity(crmUser.getCity());
		user.setStreet(crmUser.getStreet());
		user.setPostalCode(crmUser.getPostalCode());
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));
		
		userDAO.save(user);

	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}
	
	@Override
	@Transactional
	public List<Advert> getUserAdverts(User user) {
		return userDAO.getUserAdverts(user);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	

}

package com.carland.dao;

import com.carland.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}

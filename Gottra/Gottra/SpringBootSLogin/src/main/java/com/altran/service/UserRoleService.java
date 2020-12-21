package com.altran.service;

import java.util.List;

import com.altran.model.Usersroles;

public interface UserRoleService {
	
	List<Usersroles> findAll();
	List<Usersroles> findByUser(String username);
	

}

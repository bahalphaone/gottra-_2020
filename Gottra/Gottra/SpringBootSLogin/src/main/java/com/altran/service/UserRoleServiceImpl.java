package com.altran.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altran.model.Usersroles;
import com.altran.repository.UsersrolesRepository;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	UsersrolesRepository userrolesRepository;
	
	public List<Usersroles> findAll(){
		return userrolesRepository.findAll();
	}
	
	public List<Usersroles> findByUser(String username){
		return userrolesRepository.findByUser(username);
	}

}

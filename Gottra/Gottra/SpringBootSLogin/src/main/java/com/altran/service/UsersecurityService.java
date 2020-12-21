package com.altran.service;


import com.altran.model.Usersecurity;

public interface UsersecurityService {

	public Usersecurity findUsersecurityByEmail(String email);
	public Usersecurity findUsersecurityByUsername(String username);
	public void saveUser(Usersecurity user);
}

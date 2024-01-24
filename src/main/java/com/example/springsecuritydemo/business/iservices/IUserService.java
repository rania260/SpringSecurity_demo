package com.example.springsecuritydemo.business.iservices;

import com.example.springsecuritydemo.dao.entities.User;

public interface IUserService {
	
	public Integer saveUser(User user);
}
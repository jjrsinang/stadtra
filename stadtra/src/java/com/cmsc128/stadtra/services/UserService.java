package com.cmsc128.stadtra.services;

import org.springframework.data.domain.Page;

import com.cmsc128.stadtra.entities.User;
import com.toolkt.utils.model.ApplicationException;

public interface UserService {
	User	create(User user);
	User	findById(Long id);
	User	update(User updated) throws ApplicationException;
	User	delete(Long id) throws ApplicationException;
	
	Page<User>	findAll(User user, int pageStart, int offset, int pageSize);
}

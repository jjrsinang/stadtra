package com.cmsc128.stadtra.services;

import org.springframework.data.domain.Page;

import com.cmsc128.stadtra.entities.Request;
import com.toolkt.utils.model.ApplicationException;

public interface RequestService {
	Request	create(Request request);
	Request	findById(Long id);
	Request	delete(Long id) throws ApplicationException;
	
	Page<Request>	findAll(Request request, int pageStart, int offset, int pageSize);
}

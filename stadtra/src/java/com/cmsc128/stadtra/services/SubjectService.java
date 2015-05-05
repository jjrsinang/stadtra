package com.cmsc128.stadtra.services;

import org.springframework.data.domain.Page;

import com.cmsc128.stadtra.entities.Subject;
import com.toolkt.utils.model.ApplicationException;

public interface SubjectService {
	Subject	create(Subject Subject);
	Subject	findById(Long id);
	Subject	update(Subject updated) throws ApplicationException;
	Subject	delete(Long id) throws ApplicationException;
	
	
}

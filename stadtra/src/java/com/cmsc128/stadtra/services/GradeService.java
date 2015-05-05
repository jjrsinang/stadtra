package com.cmsc128.stadtra.services;

import org.springframework.data.domain.Page;

import com.cmsc128.stadtra.entities.Grade;
import com.toolkt.utils.model.ApplicationException;

public interface GradeService {
	Grade	create(Grade Grade);
	Grade	findById(Long id);
	Grade	update(Grade updated) throws ApplicationException;
	Grade	delete(Long id) throws ApplicationException;
	
	Page<Grade>	findAll(Grade grade, int pageStart, int offset, int pageSize);
}

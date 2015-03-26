package com.cmsc128.stadtra.services;

import org.springframework.data.domain.Page;

import com.cmsc128.stadtra.entities.Teacher;
import com.toolkt.utils.model.ApplicationException;

public interface TeacherService {
	Teacher	create(Teacher teacher);
	Teacher	findById(Long id);
	Teacher	update(Teacher updated) throws ApplicationException;
	Teacher	delete(Long id) throws ApplicationException;
	
	Page<Teacher>	findAll(Teacher teacher, int pageStart, int offset, int pageSize);
}

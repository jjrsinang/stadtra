package com.cmsc128.stadtra.services;

import org.springframework.data.domain.Page;

import com.cmsc128.stadtra.entities.Student;
import com.toolkt.utils.model.ApplicationException;

public interface StudentService {
	Student	create(Student student);
	Student	findById(Long id);
	Student	update(Student updated) throws ApplicationException;
	Student	delete(Long id) throws ApplicationException;
	
	Page<Student>	findAll(Student student, int pageStart, int offset, int pageSize);
}

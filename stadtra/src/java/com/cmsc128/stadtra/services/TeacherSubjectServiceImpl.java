package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cmsc128.stadtra.entities.QTeacherSubject;
import com.cmsc128.stadtra.entities.TeacherSubject;
import com.cmsc128.stadtra.repository.TeacherSubjectRepository;
import com.mysema.query.BooleanBuilder;

@Service("teacherSubjectService")
public class TeacherSubjectServiceImpl implements TeacherSubjectService {
	
	@Resource
	TeacherSubjectRepository repository;

	@Override
	public Page<TeacherSubject> findAll(Long teacherId) {
		
		QTeacherSubject qteacherSubject = QTeacherSubject.teacherSubject;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(qteacherSubject.teacherId.eq(teacherId));
		builder.and(qteacherSubject.isStillEffective.isTrue());
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "id");
		// creates page info that fetches pageSize number of users
		Pageable pageReq = new PageRequest(0, 99, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<TeacherSubject> subjects = repository.findAll(builder.getValue(), pageReq);
		return subjects;
	}

}

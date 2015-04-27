package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsc128.stadtra.entities.Teacher;
import com.cmsc128.stadtra.repository.TeacherRepository;
import com.mysema.query.BooleanBuilder;
import com.toolkt.utils.StringUtils;
import com.toolkt.utils.model.ApplicationException;

import com.cmsc128.stadtra.entities.QTeacher;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

	@Resource
	private TeacherRepository teacherRepository; // repository that connects with database
	
	@Transactional
	@Override
	public Teacher create(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@Transactional
	@Override
	public Teacher findById(Long id) {
		return teacherRepository.findOne(id);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Teacher update(Teacher updated) throws ApplicationException {
		Teacher teacher = teacherRepository.findOne(updated.getId());
		if (teacher == null) {
			throw new ApplicationException("Teacher does not exist.");
		}
		return teacherRepository.save(updated);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Teacher delete(Long id) throws ApplicationException {
		Teacher teacher = teacherRepository.findOne(id);
		if (teacher == null) {
			throw new ApplicationException("Teacher does not exist.");
		}
		teacherRepository.delete(id);
		return null;
	}

	@Override
	public Page<Teacher> findAll(Teacher teacher, int pageStart, int offset,
			int pageSize) {
		if(pageStart == 0) pageStart = 1;
		if(pageSize  == 0) pageSize = 20;
		
		/*
		 * Q classes are query classes - they are objects that contain "columns"
		 *  to be used when generating SQL select statements
		 */
		QTeacher qteacher = QTeacher.teacher;
		
		/*
		 * boolean builders are java conditional statements
		 * that are used for SQL WHERE clauses 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		
		if (teacher.getId() != null && teacher.getId() > 0) {
			builder.and(qteacher.id.eq(teacher.getId())); // translates to "... AND t_user.id = id"
		}
		
		if (StringUtils.hasText(teacher.getEmployeeNo())) {
			builder.and(qteacher.employeeNo.contains(teacher.getEmployeeNo()));
		}
		
		if (StringUtils.hasText(teacher.getfName())) {
			builder.and(qteacher.fName.contains(teacher.getfName()));
		}
		
		if (StringUtils.hasText(teacher.getmName())) {
			builder.and(qteacher.mName.contains(teacher.getmName()));
		}
		
		if (StringUtils.hasText(teacher.getlName())) {
			builder.and(qteacher.lName.contains(teacher.getlName()));
		}
		
		if (teacher.getStudentId() != null && teacher.getStudentId() != 0) {
			builder.and(qteacher.students.any().studentId.eq(teacher.getStudentId()));
		}
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "id");
		// creates page info that fetches pageSize number of teachers
		Pageable pageReq = new PageRequest(pageStart -1, pageSize, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<Teacher> teachers = teacherRepository.findAll(builder.getValue(), pageReq);
		return teachers;
	}

}

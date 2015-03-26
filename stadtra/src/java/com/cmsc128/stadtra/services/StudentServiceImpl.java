package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsc128.stadtra.entities.Student;
import com.cmsc128.stadtra.repository.StudentRepository;
import com.mysema.query.BooleanBuilder;
import com.toolkt.utils.StringUtils;
import com.toolkt.utils.model.ApplicationException;

import com.cmsc128.stadtra.entities.QStudent;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentRepository studentRepository; // repository that connects with database
	
	@Transactional
	@Override
	public Student create(Student student) {
		return studentRepository.save(student);
	}

	@Transactional
	@Override
	public Student findById(Long id) {
		return studentRepository.findOne(id);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Student update(Student updated) throws ApplicationException {
		Student student = studentRepository.findOne(updated.getId());
		if (student == null) {
			throw new ApplicationException("Student does not exist.");
		}
		
		return studentRepository.save(updated);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Student delete(Long id) throws ApplicationException {
		Student student = studentRepository.findOne(id);
		if (student == null) {
			throw new ApplicationException("Student does not exist.");
		}
		studentRepository.delete(id);
		return null;
	}

	@Override
	public Page<Student> findAll(Student student, int pageStart, int offset,
			int pageSize) {
		if(pageStart == 0) pageStart = 1;
		if(pageSize  == 0) pageSize = 20;
		
		/*
		 * Q classes are query classes - they are objects that contain "columns"
		 *  to be used when generating SQL select statements
		 */
		QStudent qstudent = QStudent.student;
		
		/*
		 * boolean builders are java conditional statements
		 * that are used for SQL WHERE clauses 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		
		if (student.getId() != null && student.getId() > 0) {
			builder.and(qstudent.id.eq(student.getId())); // translates to "... AND t_student.id = id"
		}
		
		if (StringUtils.hasText(student.getStudentNo())) {
			builder.and(qstudent.studentNo.eq(student.getStudentNo()));
		}
		
		if (StringUtils.hasText(student.getfName())) {
			builder.and(qstudent.fName.equalsIgnoreCase(student.getfName()));
		}
		
		if (StringUtils.hasText(student.getmName())) {
			builder.and(qstudent.mName.equalsIgnoreCase(student.getmName()));
		}
		
		if (StringUtils.hasText(student.getlName())) {
			builder.and(qstudent.lName.equalsIgnoreCase(student.getlName()));
		}
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "id");
		// creates page info that fetches pageSize number of students
		Pageable pageReq = new PageRequest(pageStart -1, pageSize, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<Student> students = studentRepository.findAll(builder.getValue(), pageReq);
		return students;
	}

}

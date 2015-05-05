package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsc128.stadtra.entities.Grade;
import com.cmsc128.stadtra.entities.QGrade;
import com.cmsc128.stadtra.repository.GradeRepository;
import com.mysema.query.BooleanBuilder;
import com.toolkt.utils.model.ApplicationException;
@Service("gradeService")
public class GradeServiceImpl implements GradeService{
	@Resource
	private GradeRepository gradeRepository; // repository that connects with database
	
	@Transactional
	@Override
	public Grade create(Grade Grade) {
		return gradeRepository.save(Grade);
	}

	@Transactional
	@Override
	public Grade findById(Long id) {
		return gradeRepository.findOne(id);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Grade update(Grade updated) throws ApplicationException {
		Grade Grade = gradeRepository.findOne(updated.getId());
		if (Grade == null) {
			throw new ApplicationException("Grade does not exist.");
		}
		
		return gradeRepository.save(updated);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Grade delete(Long id) throws ApplicationException {
		Grade Grade = gradeRepository.findOne(id);
		if (Grade == null) {
			throw new ApplicationException("Grade does not exist.");
		}
		gradeRepository.delete(id);
		return null;
	}

	@Override
	public Page<Grade> findAll(Grade grade, int pageStart, int offset, int pageSize) {
		if(pageStart == 0) pageStart = 1;
		if(pageSize  == 0) pageSize = 20;
		
		QGrade qgrade = QGrade.grade1;
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(qgrade.studentId.eq(grade.getId()));
		builder.and(qgrade.status.eq(grade.getStatus()));
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "id");
		// creates page info that fetches pageSize number of students
		Pageable pageReq = new PageRequest(0, 9999, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<Grade> grades = gradeRepository.findAll(builder.getValue(), pageReq);
		return grades;
	}
}

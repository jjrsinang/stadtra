package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsc128.stadtra.entities.QRequest;
import com.cmsc128.stadtra.entities.Request;
import com.cmsc128.stadtra.repository.RequestRepository;
import com.mysema.query.BooleanBuilder;
import com.toolkt.utils.model.ApplicationException;

@Service("requestService")
public class RequestServiceImpl implements RequestService {

	@Resource
	private RequestRepository requestRepository; // repository that connects with database
	
	@Transactional
	@Override
	public Request create(Request request) {
		return requestRepository.save(request);
	}

	@Transactional
	@Override
	public Request findById(Long id) {
		return requestRepository.findOne(id);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Request delete(Long id) throws ApplicationException {
		Request request = requestRepository.findOne(id);
		if (request == null) {
			throw new ApplicationException("Request does not exist.");
		}
		
		requestRepository.delete(request);
		return null;
	}
	
	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Request update(Request updated) throws ApplicationException {
		Request request = requestRepository.findOne(updated.getId());
		if (request == null) {
			throw new ApplicationException("Request does not exist.");
		}
		request.setAccepted(updated.getAccepted());
		request.setMessage(updated.getMessage());
		
		return requestRepository.save(request);
	}

	@Override
	public Page<Request> findAll(Request request, int pageStart, int offset, int pageSize) {
		if(pageStart == 0) pageStart = 1;
		if(pageSize  == 0) pageSize = 20;
		
		/*
		 * Q classes are query classes - they are objects that contain "columns"
		 *  to be used when generating SQL select statements
		 */
		QRequest qrequest = QRequest.request;
		
		/*
		 * boolean builders are java conditional statements
		 * that are used for SQL WHERE clauses 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		
		if (request.getStudentId() != null && request.getStudentId() > 0) {
			builder.and(qrequest.studentId.eq(request.getStudentId()));
			builder.and(qrequest.accepted.isNotNull());
		}
		
		if (request.getTeacherId() != null && request.getTeacherId() > 0) {
			builder.and(qrequest.teacherId.eq(request.getTeacherId()));
			builder.and(qrequest.accepted.isNull());
		}
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "id");
		// creates page info that fetches pageSize number of users
		Pageable pageReq = new PageRequest(pageStart -1, pageSize, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<Request> requests = requestRepository.findAll(builder.getValue(), pageReq);
		return requests;
	}

}

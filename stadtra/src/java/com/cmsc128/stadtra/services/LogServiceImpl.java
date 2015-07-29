package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsc128.stadtra.entities.Log;
import com.cmsc128.stadtra.entities.QLog;
import com.cmsc128.stadtra.repository.LogRepository;
import com.mysema.query.BooleanBuilder;

@Service("logService")
public class LogServiceImpl implements LogService {

	@Resource
	private LogRepository logRepository; // repository that connects with database
	
	@Transactional
	@Override
	public Log create(Log log) {
		return logRepository.save(log);
	}
	
	@Override
	public Page<Log> list(int pageStart, int offset, int pageSize) {
		if(pageStart == 0) pageStart = 1;
		if(pageSize  == 0) pageSize = 20;
		
		/*
		 * Q classes are query classes - they are objects that contain "columns"
		 *  to be used when generating SQL select statements
		 */
		QLog qlog = QLog.log;
		
		/*
		 * boolean builders are java conditional statements
		 * that are used for SQL WHERE clauses 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(qlog.id.gt(0));
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "id");
		// creates page info that fetches pageSize number of users
		Pageable pageReq = new PageRequest(pageStart -1, pageSize, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<Log> logs = logRepository.findAll(builder.getValue(), pageReq);
		return logs;
	}
}

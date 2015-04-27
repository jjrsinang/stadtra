package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsc128.stadtra.entities.Log;
import com.cmsc128.stadtra.repository.LogRepository;

@Service("logService")
public class LogServiceImpl implements LogService {

	@Resource
	private LogRepository logRepository; // repository that connects with database
	
	@Transactional
	@Override
	public Log create(Log log) {
		return logRepository.save(log);
	}
}

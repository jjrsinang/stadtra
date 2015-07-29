package com.cmsc128.stadtra.services;

import org.springframework.data.domain.Page;

import com.cmsc128.stadtra.entities.Log;

public interface LogService {
	Log	create(Log log);
	Page<Log> list(int pageStart, int offset, int pageSize);
}

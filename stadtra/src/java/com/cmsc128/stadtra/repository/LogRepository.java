package com.cmsc128.stadtra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cmsc128.stadtra.entities.Log;

public interface LogRepository extends JpaRepository<Log, Long>,
		QueryDslPredicateExecutor<Log> {

}

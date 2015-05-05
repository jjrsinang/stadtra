package com.cmsc128.stadtra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cmsc128.stadtra.entities.Student;
import com.cmsc128.stadtra.entities.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long>,
		QueryDslPredicateExecutor<Grade> {

}

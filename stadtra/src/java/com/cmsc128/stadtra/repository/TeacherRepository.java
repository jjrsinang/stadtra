package com.cmsc128.stadtra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cmsc128.stadtra.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>,
	QueryDslPredicateExecutor<Teacher>{

}

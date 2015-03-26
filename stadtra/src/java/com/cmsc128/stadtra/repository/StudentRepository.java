package com.cmsc128.stadtra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cmsc128.stadtra.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>,
		QueryDslPredicateExecutor<Student> {

}

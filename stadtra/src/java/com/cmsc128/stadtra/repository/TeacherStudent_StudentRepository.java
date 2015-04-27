package com.cmsc128.stadtra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cmsc128.stadtra.entities.TeacherStudent_Student;

public interface TeacherStudent_StudentRepository extends JpaRepository<TeacherStudent_Student, Long>,
		QueryDslPredicateExecutor<TeacherStudent_Student> {

}

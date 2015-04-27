package com.cmsc128.stadtra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cmsc128.stadtra.entities.TeacherStudent_Teacher;

public interface TeacherStudent_TeacherRepository extends JpaRepository<TeacherStudent_Teacher, Long>,
		QueryDslPredicateExecutor<TeacherStudent_Teacher> {

}

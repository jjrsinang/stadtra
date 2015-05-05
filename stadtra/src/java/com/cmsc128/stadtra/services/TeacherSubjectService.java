package com.cmsc128.stadtra.services;

import org.springframework.data.domain.Page;

import com.cmsc128.stadtra.entities.TeacherSubject;

public interface TeacherSubjectService {
	Page<TeacherSubject>	findAll(Long teacherId);
}

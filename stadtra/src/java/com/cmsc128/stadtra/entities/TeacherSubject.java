package com.cmsc128.stadtra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("teacherSubjects")
@Entity
@Table(name="t_teacher_subject")
public class TeacherSubject extends AbstractEntity {
	private	Long teacherId;
	private Long subjectId;
	private Boolean isStillEffective;
	private Subject subject;
	
	public TeacherSubject(){}

	@Column(name="teacher_id")
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	@Column(name="subject_id")
	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name="is_still_effective")
	public Boolean getIsStillEffective() {
		return isStillEffective;
	}

	public void setIsStillEffective(Boolean isStillEffective) {
		this.isStillEffective = isStillEffective;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id", referencedColumnName = "subject_id", insertable = false, updatable = false)
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}

package com.cmsc128.stadtra.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("teacherSubjects")
@Entity
@Table(name="t_teacher_subject")
public class TeacherSubject extends AbstractEntity {
	private	Long teacherId;
	private Long subjectId;
	private Boolean isStillEffective;
	private List<Subject> subject;
	
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

	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "subject_id", referencedColumnName = "id", insertable = false, updatable = false)
	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}
}

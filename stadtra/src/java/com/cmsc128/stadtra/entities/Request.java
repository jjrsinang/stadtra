package com.cmsc128.stadtra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("requests")
@Entity
@Table(name="t_request")
public class Request extends AbstractEntity {
	private Long studentId;
	private Long teacherId;
	private Boolean accepted;
	private String reason;
	private String message;
	
	private Student student;
	private Teacher teacher;
	
	public Request(){}

	@Column(name="student_id")
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@Column(name="teacher_id")
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	@Column(name="accepted")
	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	@Column(name="reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name="message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "teacher_id", referencedColumnName = "id", insertable = false, updatable = false)
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}

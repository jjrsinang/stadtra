package com.cmsc128.stadtra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("grades")
@Entity
@Table(name="t_grade")
public class Grade extends AbstractEntity {

	private Long	courseId;
	private Long	studentId;
	private String	status;
	private	String	grade;
	private	String	semTaken;
	private	Integer	year;
	
	private Subject subject;
	
	public Grade(){}
	
	@Column(name="course_id")
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Column(name="student_id")
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	@Column(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name="grade")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name="sem_taken")
	public String getSemTaken() {
		return semTaken;
	}

	public void setSemTaken(String semTaken) {
		this.semTaken = semTaken;
	}

	@Column(name="year")
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
	public Subject getSubject(){
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}

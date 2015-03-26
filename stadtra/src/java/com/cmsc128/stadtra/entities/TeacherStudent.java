package com.cmsc128.stadtra.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.toolkt.utils.json.DateDeserializer;
import com.toolkt.utils.json.DateSerializer;

@XStreamAlias("teacherStudent")
@Entity
@Table(name="t_teacher_student")
public class TeacherStudent extends AbstractEntity {
	private Long	studentId;
	private String	studentNo;
	private Long	teacherId;
	private String	employeeNo;
	private Date	dateApproved;
	private Boolean	isStillEffective;
	
	public TeacherStudent(){}

	@Column(name="student_id")
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	@Column(name="student_no")
	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	@Column(name="teacher_id")
	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	@Column(name="employee_no")
	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	@JsonSerialize(using=DateSerializer.class)
	@Column(name="date_approved")
	public Date getDateApproved() {
		return dateApproved;
	}
	@JsonDeserialize(using=DateDeserializer.class)
	public void setDateApproved(Date dateApproved) {
		this.dateApproved = dateApproved;
	}

	@Column(name="is_still_effective")
	public Boolean getIsStillEffective() {
		return isStillEffective;
	}

	public void setIsStillEffective(Boolean isStillEffective) {
		this.isStillEffective = isStillEffective;
	}
	
	
}

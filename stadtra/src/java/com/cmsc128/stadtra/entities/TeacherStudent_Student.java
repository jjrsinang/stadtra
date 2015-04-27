package com.cmsc128.stadtra.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.toolkt.utils.json.DateDeserializer;
import com.toolkt.utils.json.DateSerializer;

@XStreamAlias("teacherStudent")
@Entity
@Table(name="t_teacher_student")
public class TeacherStudent_Student extends AbstractEntity {
	private Long	studentId;
	private String	studentNo;
	private String	studentName;
	private Long	teacherId;
	private String	employeeNo;
	private String	teacherName;
	private Date	dateApproved;
	private Boolean	isStillEffective;
	
	private Student student;
	
	public TeacherStudent_Student(){}

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
	
	@Column(name="student_name")
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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
	
	@Column(name="teacher_name")
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}

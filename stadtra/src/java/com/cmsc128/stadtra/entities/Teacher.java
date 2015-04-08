package com.cmsc128.stadtra.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.toolkt.utils.json.DateDeserializer;
import com.toolkt.utils.json.DateSerializer;

@XStreamAlias("teachers")
@Entity
@Table(name="t_teacher")
public class Teacher extends AbstractEntity {
	private String	employeeNo;
	private String	fName;
	private String	mName;
	private	String	lName;
	private Date	birthDate;
	private String	sex;
	private String	email;
	
	private Set<TeacherStudent> students;
	
	public Teacher(){}

	@Column(name="employee_no")
	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	@Column(name="f_name")
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	@Column(name="m_name")
	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	@Column(name="l_name")
	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	@JsonSerialize(using=DateSerializer.class)
	@Column(name="birth_date")
	public Date getBirthDate() {
		return birthDate;
	}
	@JsonDeserialize(using=DateDeserializer.class)
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "teacher_id", referencedColumnName = "id", insertable = false, updatable = false)
	public Set<TeacherStudent> getStudents() {
		return students;
	}

	public void setStudents(Set<TeacherStudent> students) {
		this.students = students;
	}
}

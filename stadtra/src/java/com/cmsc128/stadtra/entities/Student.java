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

@XStreamAlias("students")
@Entity
@Table(name="t_student")
public class Student extends AbstractEntity {
	private String	studentNo;
	private String	fName;
	private String	mName;
	private	String	lName;
	private Date	birthDate;
	private String	sex;
	private String	email;
	private String	classification;
	
	private Set<TeacherStudent> advisers;
	
	public Student(){}

	@Column(name="student_no")
	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
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

	@Column(name="classification")
	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
	public Set<TeacherStudent> getAdvisers() {
		return advisers;
	}

	public void setAdvisers(Set<TeacherStudent> advisers) {
		this.advisers = advisers;
	}
	
	
}

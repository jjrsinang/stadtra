package com.cmsc128.stadtra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("users")
@Entity
@Table(name="t_user")
public class User extends AbstractEntity {
	private String	loginId;
	private String	password;
	private String	fName;
	private String	mName;
	private String	lName;
	private String	role;
	private Long	referenceId;
	private Teacher	teacher;
	private Student	student;
	
	public User(){}

	@Column(name="login_id")
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Column(name="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Column(name="role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name="reference_id")
	public Long getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "reference_id", referencedColumnName = "id", insertable = false, updatable = false),
		@JoinColumn(name = "login_id", referencedColumnName = "employee_no", insertable = false, updatable = false)
	})
	
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "reference_id", referencedColumnName = "id", insertable = false, updatable = false),
		@JoinColumn(name = "login_id", referencedColumnName = "student_no", insertable = false, updatable = false)
	})
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}

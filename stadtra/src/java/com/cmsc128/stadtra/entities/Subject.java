package com.cmsc128.stadtra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("subjects")
@Entity
@Table(name="t_subject")
public class Subject extends AbstractEntity {
	private String	courseNum;
	private String	courseTitle;
	private String	units;
	private String	section;
	private String	lecture;
	private String	lab;
	
	public Subject(){}

	@Column(name="course_num")
	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	@Column(name="course_title")
	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	@Column(name="units")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	@Column(name="section")
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@Column(name="lecture")
	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	@Column(name="lab")
	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}
}

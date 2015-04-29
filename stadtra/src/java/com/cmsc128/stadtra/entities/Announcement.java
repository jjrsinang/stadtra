package com.cmsc128.stadtra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("announcements")
@Entity
@Table(name="t_announcement")
public class Announcement extends AbstractEntity {
	private String title;
	private String body;
	private String file;
	
	public Announcement(){}

	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="body")
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name="file")
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}

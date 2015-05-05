package com.cmsc128.stadtra.entities;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("announcements")
@Entity
@Table(name="t_announcement")
public class Announcement extends AbstractEntity {
	private String	title;
	private String	body;
	private String	filename;
	private Blob	data;
	
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

	@Column(name="filename")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@JsonIgnore
	@Lob
	@Column(name="data")
	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}
}

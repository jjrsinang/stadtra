package com.cmsc128.stadtra.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("notififcations")
@Entity
@Table(name="t_notification")
public class Notification extends AbstractEntity {
	private String title;
	private String messsage;
	private Long receiverId;
	
	public Notification() {
		// TODO Auto-generated constructor stub
	}

	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="message")
	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	@Column(name="receiver_id")
	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
}

package com.cmsc128.stadtra.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.toolkt.utils.json.TimeStampDeserializer;
import com.toolkt.utils.json.TimeStampSerializer;

@XStreamAlias("logs")
@Entity
@Table(name="t_log")
public class Log extends AbstractEntity {
	private String operation;
	private String user;
	private Date time;
	
	public Log(){}

	@Column(name="operation")
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Column(name="user")
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@JsonSerialize(using=TimeStampSerializer.class)
	@Column(name="time")
	public Date getTime() {
		return time;
	}
	@JsonDeserialize(using=TimeStampDeserializer.class)
	public void setTime(Date time) {
		this.time = time;
	}
}

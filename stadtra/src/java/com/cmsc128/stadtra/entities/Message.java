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
import com.toolkt.utils.json.TimeStampDeserializer;
import com.toolkt.utils.json.TimeStampSerializer;

@XStreamAlias("messages")
@Entity
@Table(name="t_message")
public class Message extends AbstractEntity {
	private Long	senderId;
	private Long	receiverId;
	private String	message;
	private Date	tstamp;
	private Long	threadId;
	
	private User	sender;
	private User	receiver;
	
	public Message(){}

	@Column(name="sender_id")
	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	@Column(name="receiver_id")
	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	@Column(name="message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonSerialize(using=TimeStampSerializer.class)
	@Column(name="tstamp")
	public Date getTstamp() {
		return tstamp;
	}
	@JsonDeserialize(using=TimeStampDeserializer.class)
	public void setTstamp(Date tstamp) {
		this.tstamp = tstamp;
	}

	@Column(name="thread_id")
	public Long getThreadId() {
		return threadId;
	}

	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "sender_id", referencedColumnName = "id", insertable = false, updatable = false)
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "receiver_id", referencedColumnName = "id", insertable = false, updatable = false)
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
}

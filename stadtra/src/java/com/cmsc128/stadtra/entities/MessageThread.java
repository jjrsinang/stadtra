package com.cmsc128.stadtra.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.toolkt.utils.json.TimeStampDeserializer;
import com.toolkt.utils.json.TimeStampSerializer;

@XStreamAlias("messageThreads")
@Entity
@Table(name="t_message_thread")
public class MessageThread extends AbstractEntity {
	private Long	participant1Id;
	private Long	participant2Id;
	private Boolean	seen1;
	private Boolean	seen2;
	private Date	tstamp;
//	private List<Message> messages;
	
	private User	participant1;
	private User	participant2;
	
	public MessageThread() {}

	@Column(name="participant1_id")
	public Long getParticipant1Id() {
		return participant1Id;
	}

	public void setParticipant1Id(Long participant1Id) {
		this.participant1Id = participant1Id;
	}

	@Column(name="participant2_id")
	public Long getParticipant2Id() {
		return participant2Id;
	}

	public void setParticipant2Id(Long participant2Id) {
		this.participant2Id = participant2Id;
	}

	@Column(name="seen1")
	public Boolean getSeen1() {
		return seen1;
	}

	public void setSeen1(Boolean seen1) {
		this.seen1 = seen1;
	}

	@Column(name="seen2")
	public Boolean getSeen2() {
		return seen2;
	}

	public void setSeen2(Boolean seen2) {
		this.seen2 = seen2;
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

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "participant1_id", referencedColumnName = "id", insertable = false, updatable = false)
	public User getParticipant1() {
		return participant1;
	}

	public void setParticipant1(User participant1) {
		this.participant1 = participant1;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "participant2_id", referencedColumnName = "id", insertable = false, updatable = false)
	public User getParticipant2() {
		return participant2;
	}

	public void setParticipant2(User participant2) {
		this.participant2 = participant2;
	}

//	@OneToMany(fetch=FetchType.EAGER)
//	@JoinColumn(name = "id", referencedColumnName = "thread_id", insertable = true, updatable = true)
//	public List<Message> getMessages() {
//		return messages;
//	}
//
//	public void setMessages(List<Message> messages) {
//		this.messages = messages;
//	}
}

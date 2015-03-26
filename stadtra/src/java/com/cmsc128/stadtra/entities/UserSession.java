package com.cmsc128.stadtra.entities;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.toolkt.utils.json.TimeStampDeserializer;
import com.toolkt.utils.json.TimeStampSerializer;

public class UserSession extends AbstractEntity {
	private User 		user;
	private Date 		loginDate;
	private HttpSession session;
	private Long 		durationInMinutes;
	private	String		remoteAddress;
	private	String		userSessionId;

	public UserSession(){}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonSerialize(using=TimeStampSerializer.class)
	public Date getLoginDate() {
		return loginDate;
	}
	@JsonDeserialize(using=TimeStampDeserializer.class)
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	@JsonIgnore
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public Long getDurationInMinutes(){
		Date now = new Date();
		return ((now.getTime() - this.loginDate.getTime()) /(1000*60));
	}
	
	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}
}

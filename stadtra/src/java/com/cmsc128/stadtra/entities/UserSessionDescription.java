package com.cmsc128.stadtra.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserSessionDescription implements Serializable {
	private static final long serialVersionUID = 7367215979167044299L;
	
	private String	loginId;
	private String 	userName;
	private Date	loginDate;
	private Long	durationInMinutes;
	private String	remoteAddress;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public void setDurationInMinutes(Long durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}
	
	public Long getDurationInMinutes() {
		return durationInMinutes;
	}
	
	
	public String getRemoteAddress() {
		return remoteAddress;
	}
	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}
	
	@JsonIgnore
	public String toString() {
		String name = this.getClass().getName(); 
		
		return name.substring(name.lastIndexOf(".")+1) + "[" + getUserName() + "/" + new SimpleDateFormat("MM/dd/yy hh:mm").format(loginDate) +"]";
	}
}

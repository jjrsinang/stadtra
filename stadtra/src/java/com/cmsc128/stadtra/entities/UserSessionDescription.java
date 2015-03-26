package com.cmsc128.stadtra.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserSessionDescription implements Serializable {
	private static final long serialVersionUID = 7367215979167044299L;
	
	private String	userCode;
	private String 	userName;
	private String 	organization;
	private Date	loginDate;
	private Integer	durationInMinutes;
	private String	remoteAddress;
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public void setDurationInMinutes(Integer durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}
	
	public Integer getDurationInMinutes() {
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

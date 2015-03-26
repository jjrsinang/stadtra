package com.cmsc128.stadtra.entities;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserSessionListener implements HttpSessionListener, Serializable {
	private static final long serialVersionUID = -3348428107419824158L;

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		//at this pt, session attributes are not yet bound
		//sessions.put(event.getSession().getId(), event.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		//remove session from sessions on invalidation, log out, session timeout etc
		String sessionId = event.getSession().getId();
		Map<String, UserSession> sessions = (Map<String, UserSession>)event.getSession().getServletContext().getAttribute("sessions");
		sessions.remove(sessionId);
	}

}

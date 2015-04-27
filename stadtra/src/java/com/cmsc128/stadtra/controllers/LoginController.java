package com.cmsc128.stadtra.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsc128.stadtra.entities.User;
import com.cmsc128.stadtra.entities.UserSession;
import com.cmsc128.stadtra.entities.UserSessionDescription;
import com.cmsc128.stadtra.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.toolkt.utils.CrudError;
import com.toolkt.utils.ErrorHandler;
import com.toolkt.utils.json.JsonData;
import com.toolkt.utils.model.ApplicationError;
import com.toolkt.utils.model.ApplicationException;

@RequestMapping(value="/security")
@Controller
public class LoginController extends AbstractController {
	private final Log log = LogFactory.getLog(getClass());
	
	@Resource
	private UserService service;
	
	public LoginController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody JsonData authenticateLogin(HttpServletRequest request,
			@RequestBody User user) throws JsonProcessingException {
		JsonData data = new JsonData();
		
		try {
			
			Page<User> users = service.findAll(user, 0, 0, 20);
			if(!users.hasContent()) {
				throw new ApplicationException("Invalid User and/or password.");
			}
			
			//check if user is already logged
			if(isLoggedIn(request, user)) {
				throw new ApplicationException("User already logged in.");
			}
			
			//at this point User successfully logged in.
			user = users.getContent().get(0);
			
			System.out.println("LOCAL PORT: "+request.getLocalPort());
			System.out.println("SERVER PORT: "+request.getServerPort());
			System.out.println("REMOTE PORT: "+request.getRemotePort());
			
			//create a UserSession to store user-specific info
			UserSession userSession = new UserSession();
			userSession.setUser(user);
			userSession.setLoginDate(new Date());
			userSession.setUserSessionId(request.getSession().getId());
			
			//retrieve ip address and hostname of user attempting to login
			userSession.setRemoteAddress(request.getRemoteAddr() + "/" + request.getRemoteHost());
			//check requester info
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println("IP Address: " + request.getLocalAddr());
			System.out.println("Hostname  : " + request.getLocalName());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			
			data.setData(userSession);
			data.setRecordCount(1);
			data.setSuccess(true);
			
			//create an http session if none exist yet (new login)
			HttpSession session = request.getSession(true);
			
			//save http session reference so user's can be logged off by administrator
			userSession.setSession(session);
						
			//bound UserSession to http session
			session.setAttribute("userSession", userSession);
			
			//update session map initialized in ApplicationIntiailizer
			Map<String, UserSession> sessions = getSessions(request.getSession());
			sessions.put(session.getId(), userSession);
			
			System.out.println("User logged in successfully...");
			
		} catch (Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}

	@RequestMapping(value = "/sessions", method = RequestMethod.GET)
	public @ResponseBody JsonData list(HttpServletRequest request) {
		JsonData data = new JsonData();

		try {
			Collection<UserSessionDescription> descriptions = new ArrayList<UserSessionDescription>();
			UserSession session = null;
			Map<String, UserSession> sessions = getSessions(request.getSession());
			for(Iterator<Map.Entry<String, UserSession>> itr = sessions.entrySet().iterator(); itr.hasNext();) {
				session = itr.next().getValue();
				
				UserSessionDescription description = new UserSessionDescription();
				description.setLoginId(session.getUser().getLoginId());
				description.setUserName(session.getUser().getlName() + ", " + session.getUser().getfName());
				description.setLoginDate(session.getLoginDate());
				description.setDurationInMinutes(session.getDurationInMinutes());
				description.setRemoteAddress(session.getRemoteAddress());
				
				descriptions.add(description);
			}
			
			// sort by dealer code, dealer name
			
			data = new JsonData(descriptions);
			data.setRecordCount(descriptions.size());
			data.setSuccess(true);

		} catch (Exception e) {
			data = controllerError(e);
		}

		return data;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public @ResponseBody JsonData logout(HttpServletRequest request){
		JsonData data = new JsonData();
		
		try {
			//invalidate user on logout
			System.out.println("Invalidating session[" + request.getSession().getId() + "]");
			
			request.getSession().invalidate();
			data.setSuccess(true);
			
		} catch (Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}
	
//	@RequestMapping(value = "/logout/sessions", method = RequestMethod.POST)
//	public JsonData multiLogout(HttpServletRequest request, @RequestBody UserSession user) {
//		JsonData data = new JsonData();
//		
//		try {
//			// get all sessions
//			Map<String, UserSession> sessions = getSessions(request.getSession());
//			
//			// loop through all sessions
//			UserSession loggedUser = null;
//			for(Iterator<Map.Entry<String, UserSession>> itr = sessions.entrySet().iterator(); itr.hasNext(); ) {
//				if (!itr.hasNext()) {
//					continue;
//				}
//				loggedUser = itr.next().getValue();
//				if(loggedUser == null) {
//					continue;
//				}
//				
//				// invalidate user session if found and if selected
//				String loginId = loggedUser.getUser().getLoginId();
//				String selValue = loginId;
//				
//				// remove/invalidate if session is in selection
//				if(user.getSelectedUsers().contains(selValue)) {
//					if(log.isInfoEnabled()) {
//						log.info("Invalidating session[" + loggedUser.getSession().getId() + "] " + selValue);
//					}
//					loggedUser.getSession().invalidate();
//					
//					// recurse to avoid next() reference errors
//					multiLogout(request, user);
//					break;
//				}
//			}
//			
//			data.setSuccess(true);
//		} catch (Exception e) {
//			data = controllerError(e);
//		}
//		
//		return data;
//	}
	
	@RequestMapping(value = "/session/authenticate", method = RequestMethod.GET)
	public JsonData authenticateJSESSIONID (HttpServletRequest request, @RequestParam("sessionId") String sessionId) {
		JsonData data = new JsonData();
		
		try {
			Map<String, UserSession> sessions = getSessions(request.getSession());
			if(sessions == null || sessions.isEmpty()) {
				data.setData(null);
				data.setSuccess(false);
				data.setRecordCount(0);
				return data;
			}
			
			UserSession loggedUser = null;
			for(Iterator<Map.Entry<String, UserSession>> itr = sessions.entrySet().iterator(); itr.hasNext(); ) {
				loggedUser = itr.next().getValue();
				if(loggedUser == null) {
					continue;
				}
				
				if(loggedUser.getUserSessionId().equalsIgnoreCase(sessionId)) {
					data.setData(loggedUser);
					data.setSuccess(true);
					data.setRecordCount(1);
					return data;
				}
			}
			data.setSuccess(true);
		} catch (Exception e) {
			controllerError(e);
		}
		
		return data;
	}
	
	private JsonData controllerError(Exception e) {
		JsonData data = new JsonData();
		ApplicationError error = new ApplicationError();
		String logMarker = ErrorHandler.logError(log, e);
		error.setErrorLog(e.getMessage());
		error.setLogMarker(logMarker);
		CrudError crudError = new CrudError("DB ERROR; "+e.getMessage() + "\n" + ErrorHandler.getStackTrace(e));
		data.setData(crudError);
		data.setSuccess(false);
		data.setError(error);
		return data; 
	}
	
	private Map<String, UserSession> getSessions(HttpSession session) {
		Map<String, UserSession> sessions = (Map<String, UserSession>)session.getServletContext().getAttribute("sessions");
		
		if(sessions == null) {
			//initialize sessions map
			sessions = new LinkedHashMap<String, UserSession>();
			session.getServletContext().setAttribute("sessions", sessions);
		}
		
		return sessions;
	}
	
	private boolean isLoggedIn(HttpServletRequest request, User user) {
		boolean logged = false;
		
		User loggedUser = null;
		Map<String, UserSession> sessions = getSessions(request.getSession());
		
		//check for case when nobody has logged on yet
		if(sessions == null || sessions.isEmpty()) {
			return false;
		}
				
		for(Iterator<Map.Entry<String, UserSession>> itr = sessions.entrySet().iterator(); itr.hasNext(); ) {
			loggedUser = itr.next().getValue().getUser();
			if(loggedUser == null) {
				continue;
			}
			
			if(loggedUser.getLoginId().equals(user.getLoginId())) {
				logged = true;
				break;
			}
		}
		
		return logged;
	}

}

package com.cmsc128.stadtra.controllers;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

import com.cmsc128.stadtra.entities.UserSession;

public class ApplicationInitializer implements ApplicationContextAware, ServletContextAware {
	private final Log log = LogFactory.getLog(getClass()); 
	
    private ServletContext servletCtx = null;
    private ApplicationContext appCtx = null;
    
    public ApplicationContext getApplicationContext() {         
        return appCtx;    
    }
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.appCtx = context;
    }

    public void setServletContext(ServletContext context) {
    	servletCtx = context;
    }
	
	public ApplicationInitializer() {}
	
	public void init() {
		if(log.isInfoEnabled()) {
			log.info("STADTRA Initializing application lookup data.");
		}
		
		//initialize map for user sessions
		servletCtx.removeAttribute("sessions");
		Map<String, UserSession> sessions = new LinkedHashMap<String, UserSession>();
		servletCtx.setAttribute("sessions", sessions);
	}
	
}
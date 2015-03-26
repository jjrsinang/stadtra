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
		
//		try {
//	    	loadReferenceCodes();
//		} catch(Exception e) {
//			log.error("Error in loading Reference codes.");
//			log.error("Error: " + e.getMessage());
//		}
//		
//		try {
//	    	loadAppConfigs();
//	    	setAllowedDomains();
//		} catch(Exception e) {
//			log.error("Error in loading Configurations.");
//			log.error("Error: " + e.getMessage());
//		}
    }
	
//	private void loadReferenceCodes() {
//		if(log.isInfoEnabled()) {
//			log.info("Loading application reference codes. ");
//		}
//
//		CodeService codeService = (CodeService)appCtx.getBean("CodeService");
//		Map<String,String> codeTypeMap = new LinkedHashMap<String,String>();
//		
//		List<Code> codeTypes = codeService.findAll().getContent();
//		for(Code codeType : codeTypes){
//			codeTypeMap.put(codeType.getType(), codeType.getType().replace("_", " ").toUpperCase());
//		}
//		servletCtx.setAttribute("codeMap", codeTypeMap);
//				
//		if(log.isInfoEnabled()) {
//			log.info("Reference codes loaded.");
//		}
//	}
//	
//	private void loadAppConfigs() {
//		if(log.isInfoEnabled()) {
//			log.info("Loading application configurations. ");
//		}
//
//		Map<String,String> appConfigMap = new LinkedHashMap<String,String>();
//		
//		ConfigService configService = (ConfigService)appCtx.getBean("ConfigService");
//		Map<String, Config> tmp = configService.findAll();
//		for (Iterator<String> it = tmp.keySet().iterator(); it.hasNext(); ) {
//			Config c = tmp.get(it.next());
//			
//			appConfigMap.put(c.getAttrName(), c.getAttrValue());
//		}
//		servletCtx.setAttribute("appConfigMap", appConfigMap);
//				
//		if(log.isInfoEnabled()) {
//			log.info("Application configurations loaded.");
//		}
//	}
//	
//	private void setAllowedDomains() {
//		Map<String, String> appConfigMap = (Map<String, String>) servletCtx.getAttribute("appConfigMap");
//		Set<String> allowedDomains = new HashSet<String>();
//		for (Iterator<String> keySet = appConfigMap.keySet().iterator(); keySet.hasNext(); ) {
//			String currentConfig = keySet.next();
//			if (currentConfig.endsWith("_domain")) {
//				allowedDomains.add(appConfigMap.get(currentConfig));
//			}
//		}
//		servletCtx.setAttribute("allowedDomains", allowedDomains);
//	}
	
}
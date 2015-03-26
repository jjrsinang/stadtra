package com.cmsc128.stadtra.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;

import com.cmsc128.stadtra.entities.UserSession;
import com.toolkt.utils.CrudError;
import com.toolkt.utils.ErrorHandler;
import com.toolkt.utils.json.JsonData;
import com.toolkt.utils.model.ApplicationError;

/*
 * UPLOAD AND ATTACHMENTS NOTES
 *	Extend AbstractController and implement getAttachments() (see ClaimController for sample)
 *	Change configs: remove "/uploads/[own_directory]" (e.g., from "/uploads/claims/" to "/claims/")
 *	Change configs: base_upload_dir = "/WEB-INF/uploads".
 *	See updated schema. 
 */

public abstract class AbstractController {
	
	/*
	 * Returns the user's session configuration. A user session is created
	 * only upon successful log in.
	 */
	protected UserSession getUserSession(HttpServletRequest request) {
		return (UserSession)request.getSession().getAttribute("userSession");
	}

	/*
	 * 	Indicates if a user has successfully logged in to the system.
	 *  Returns true if and only if the user logged in to the system
	 *  	and the userId/Password is valid
	 */
	protected Boolean isAuthenticated(HttpServletRequest request) {
		return getUserSession(request) != null;
	}
	
	protected JsonData controllerError(Exception e, Log log){
		JsonData data = new JsonData();
		
		ApplicationError error = new ApplicationError();
		String logMarker = ErrorHandler.logError(log, e);
		error.setErrorLog(e.getMessage());
		error.setLogMarker(logMarker);
		
		CrudError crudError = new CrudError(CrudError.SERVER_ERROR + "; "+e.getMessage() + "\n" + ErrorHandler.getStackTrace(e));
		data.setData(crudError);
		data.setSuccess(false);
		data.setError(error);
		
		return data;
	}
}

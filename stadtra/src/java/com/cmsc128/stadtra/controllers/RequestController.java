package com.cmsc128.stadtra.controllers;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsc128.stadtra.entities.Notification;
import com.cmsc128.stadtra.entities.Request;
import com.cmsc128.stadtra.services.LogService;
import com.cmsc128.stadtra.services.RequestService;
import com.cmsc128.stadtra.services.StudentService;
import com.toolkt.utils.CrudError;
import com.toolkt.utils.ErrorHandler;
import com.toolkt.utils.json.JsonData;
import com.toolkt.utils.model.ApplicationError;

@RequestMapping(value="/requests")
@Controller
public class RequestController extends AbstractController {
	private final Log log = LogFactory.getLog(getClass());
	
	@Resource
	private RequestService service;
	
	@Resource
	private StudentService studentService;
	
	@Resource
	private LogService logService;

	@RequestMapping(method=RequestMethod.POST)
	@SendTo("/notify")
	@ResponseBody
	public JsonData create(HttpServletRequest servletRequest, @RequestBody Request request) {
		JsonData data = new JsonData();
		
		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			Request created = service.create(request);
			data.setData(created);
			data.setRecordCount(1);
			data.setSuccess(true);
			
			com.cmsc128.stadtra.entities.Log activityLog = new com.cmsc128.stadtra.entities.Log();
			activityLog.setOperation("add request");
			activityLog.setUser(getUserSession(servletRequest).getUser().getLoginId());
			activityLog.setTime(new Date());
			logService.create(activityLog);
		} catch(Exception e) {
			data = controllerError(e);		
		}
		return data;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody JsonData delete(HttpServletRequest request, @PathVariable("id") Long id) {
		JsonData data = new JsonData();
		
		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			
			service.delete(id);
			data.setSuccess(true);
			
			com.cmsc128.stadtra.entities.Log activityLog = new com.cmsc128.stadtra.entities.Log();
			activityLog.setOperation("delete request");
			activityLog.setUser(getUserSession(request).getUser().getLoginId());
			activityLog.setTime(new Date());
			logService.create(activityLog);
		} catch (Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}
	
	@RequestMapping(value = "/list/{id}", method = RequestMethod.PUT)
	public @ResponseBody JsonData update(HttpServletRequest servletRequest, 
			@RequestBody Request request, @PathVariable("id") Long id) {
		JsonData data = new JsonData();
		
		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			
			Request updated = service.update(request);
			data.setData(updated);
			data.setRecordCount(1);
			data.setSuccess(true);
			
			com.cmsc128.stadtra.entities.Log activityLog = new com.cmsc128.stadtra.entities.Log();
			activityLog.setOperation("update request");
			activityLog.setUser(getUserSession(servletRequest).getUser().getLoginId());
			activityLog.setTime(new Date());
			logService.create(activityLog);
			
		} catch (Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody JsonData search(
			@RequestParam(value="studentId", required=false) Long studentId,
			@RequestParam(value="teacherId", required=false) Long teacherId,
			@RequestParam("page") int page,  
			@RequestParam("start") int start, 
			@RequestParam("limit") int limit) {
		JsonData data = new JsonData();

		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			
			if ((studentId == null || studentId == 0) && (teacherId == null || teacherId == 0)) {
				data.setData(null);
				data.setRecordCount(0);
				data.setSuccess(true);
				return data;
			}
			
			Request request = new Request();
			request.setStudentId(studentId);
			request.setTeacherId(teacherId);
			
			Page<Request> requests = service.findAll(request, page, start, limit);
			data.setData(requests.getContent());
			data.setRecordCount(requests.getTotalElements());
			data.setSuccess(true);
			
		} catch (Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}
	
	@RequestMapping(value="/notify", method=RequestMethod.GET)
	@MessageMapping("/notify")
    public @ResponseBody JsonData greeting() throws Exception {
        JsonData data = new JsonData();
        data.setSuccess(true);
        Notification notification = new Notification();
        notification.setTitle("Hello");
        
        data.setData(notification);
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
}

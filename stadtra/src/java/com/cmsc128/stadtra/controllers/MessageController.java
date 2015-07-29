package com.cmsc128.stadtra.controllers;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsc128.stadtra.entities.Message;
import com.cmsc128.stadtra.entities.MessageThread;
import com.cmsc128.stadtra.services.LogService;
import com.cmsc128.stadtra.services.MessageService;
import com.toolkt.utils.CrudError;
import com.toolkt.utils.ErrorHandler;
import com.toolkt.utils.json.JsonData;
import com.toolkt.utils.model.ApplicationError;

@RequestMapping(value="/messages")
@Controller
public class MessageController extends AbstractController {
	private final Log log = LogFactory.getLog(getClass());
	
	@Resource
	private MessageService service;
	
	@Resource
	private LogService logService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public JsonData create(HttpServletRequest request, @RequestBody Message message) {
		JsonData data = new JsonData();
		
		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			Message created = service.reply(message);
			data.setData(created);
			data.setRecordCount(1);
			data.setSuccess(true);
			
			com.cmsc128.stadtra.entities.Log activityLog = new com.cmsc128.stadtra.entities.Log();
			activityLog.setOperation("add message");
			activityLog.setUser(getUserSession(request).getUser().getLoginId());
			activityLog.setTime(new Date());
			logService.create(activityLog);
		} catch(Exception e) {
			data = controllerError(e);		
		}
		return data;
	}	
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody JsonData list(HttpServletRequest request, 
			HttpServletResponse response,
			@RequestParam("threadId") Long threadId,
			@RequestParam("page") int page,  
			@RequestParam("start") int start, 
			@RequestParam("limit") int limit) {
		JsonData data = new JsonData();

		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			Message message = new Message();
			message.setThreadId(threadId);
			Page<Message> messages = service.findAllMessages(message, page, start, limit);
			data.setData(messages.getContent());
			data.setRecordCount(messages.getTotalElements());
			data.setSuccess(true);
			
		} catch (Exception e) {
			data = controllerError(e);
		}
		
		return data;
	}
	
	@RequestMapping(value="/threads", method=RequestMethod.GET)
	public @ResponseBody JsonData listThread(HttpServletRequest request, 
			HttpServletResponse response, 
			@RequestParam(value="participant1Id", required=false) Long participant1Id,
			@RequestParam(value="participant2Id", required=false) Long participant2Id,
			@RequestParam(value="seen1", required=false) Boolean seen1,
			@RequestParam(value="seen2", required=false) Boolean seen2,
			@RequestParam("page") int page,  
			@RequestParam("start") int start, 
			@RequestParam("limit") int limit) {
		JsonData data = new JsonData();

		try {
//			if (!isAuthenticated(request)) {
//				throw new ApplicationException(CrudError.NOT_AUTHENTICATED);
//			}
			
			
			MessageThread thread = new MessageThread();
			if (participant1Id != null && participant1Id > 0) {
				thread.setParticipant1Id(participant1Id);				
			}
			
			if (participant2Id != null && participant2Id > 0) {
				thread.setParticipant2Id(participant2Id);				
			}
			
			Page<MessageThread> threads = service.findAllThreads(thread, page, start, limit);
			data.setData(threads.getContent());
			data.setRecordCount(threads.getTotalElements());
			data.setSuccess(true);
			
		} catch (Exception e) {
			data = controllerError(e);
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
}

package com.cmsc128.stadtra.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsc128.stadtra.entities.Notification;
import com.toolkt.utils.json.JsonData;

@RequestMapping(value="/notifications")
@Controller
public class MessageController extends AbstractController {
	
	@RequestMapping(method=RequestMethod.GET)
    
    public @ResponseBody JsonData greeting(HttpServletRequest request, @RequestBody Notification notification) throws Exception {
		JsonData data = new JsonData();
		
		data.setData("Hello");
		data.setRecordCount(1);
		data.setSuccess(true);
		
        return data;
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public JsonData retrieve(HttpServletRequest request, @PathVariable("id") Long id) {
		JsonData data = new JsonData();
		data.setData("World");
		data.setRecordCount(1);
		data.setSuccess(true);
		return data;
	}
}

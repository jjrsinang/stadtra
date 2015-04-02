package com.cmsc128.stadtra.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmsc128.stadtra.entities.Email;
import com.toolkt.utils.json.JsonData;

@RequestMapping(value="/email")
@Controller
public class EmailController extends AbstractController {
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value="/send", method = RequestMethod.POST)
	public @ResponseBody JsonData send(@RequestBody Email email){
		JsonData data = new JsonData();
		try{
		SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email.getEmail());
			message.setSubject(email.getSubject());
			message.setText(email.getMessage());
			// sends the e-mail
	        mailSender.send(message);
			data.setRecordCount(1);
		}
		catch(Exception e){
			data = controllerError(e, log);
		}
		data.setSuccess(true);
		return data;
	}
}

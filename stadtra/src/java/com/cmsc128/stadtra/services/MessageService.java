package com.cmsc128.stadtra.services;

import org.springframework.data.domain.Page;

import com.cmsc128.stadtra.entities.Message;
import com.cmsc128.stadtra.entities.MessageThread;
import com.toolkt.utils.model.ApplicationException;

public interface MessageService {
	MessageThread			createThread(MessageThread thread);
	Message					reply(Message message) throws ApplicationException;
	
	Page<MessageThread> 	findAllThreads(MessageThread thread, int pageStart, int offset, int pageSize);
	Page<Message> 			findAllMessages(Message message, int pageStart, int offset, int pageSize);
}

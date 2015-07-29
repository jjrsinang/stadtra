package com.cmsc128.stadtra.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsc128.stadtra.entities.Message;
import com.cmsc128.stadtra.entities.MessageThread;
import com.cmsc128.stadtra.entities.QMessage;
import com.cmsc128.stadtra.entities.QMessageThread;
import com.cmsc128.stadtra.repository.MessageRepository;
import com.cmsc128.stadtra.repository.MessageThreadRepository;
import com.mysema.query.BooleanBuilder;
import com.toolkt.utils.model.ApplicationException;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

	@Resource
	private MessageRepository messageRepository; // repository that connects with database
	
	@Resource
	private MessageThreadRepository messageThreadRepository; // repository that connects with database

	@Transactional
	@Override
	public MessageThread createThread(MessageThread thread) {
		return messageThreadRepository.save(thread);
	}

	@Transactional
	@Override
	public Message reply(Message message) throws ApplicationException {
		MessageThread criteria = new MessageThread();
		criteria.setParticipant1Id(message.getSenderId());
		criteria.setParticipant2Id(message.getReceiverId());
		List<MessageThread> threads = findAllThreads(criteria, 0, 0, 10).getContent();
		Date date = new Date();
		MessageThread thread = null;
		if (threads.isEmpty()) {
			MessageThread newThread = new MessageThread();
			newThread.setParticipant1Id(message.getSenderId());
			newThread.setParticipant2Id(message.getReceiverId());
			newThread.setSeen1(true);
			newThread.setSeen2(false);
			newThread.setTstamp(date);
			thread = createThread(newThread);
		} else {
			thread = threads.get(0);
			if (message.getSenderId() == thread.getParticipant1Id() &&
					message.getReceiverId() == thread.getParticipant2Id()) {
				thread.setSeen1(true);
				thread.setSeen2(false);
			} else if (message.getSenderId() == thread.getParticipant2Id() &&
					message.getReceiverId() == thread.getParticipant1Id()) {
				thread.setSeen1(false);
				thread.setSeen2(true);
			}
			
			thread.setTstamp(date);
			updateThread(thread);
		}
		message.setThreadId(thread.getId());
		message.setTstamp(date);
		return messageRepository.save(message);
	}
	
	@Transactional(rollbackFor=ApplicationException.class)
	private MessageThread updateThread(MessageThread updated) throws ApplicationException {
		MessageThread thread = messageThreadRepository.findOne(updated.getId());
		if (thread == null) {
			throw new ApplicationException("Thread does not exist.");
		}
		
		return messageThreadRepository.save(updated);
	}
	
	@Transactional(rollbackFor=ApplicationException.class)
	private Message updateMessage(Message updated) throws ApplicationException {
		Message message = messageRepository.findOne(updated.getId());
		if (message == null) {
			throw new ApplicationException("Message does not exist.");
		}
		
		return messageRepository.save(updated);
	}

	@Override
	public Page<MessageThread> findAllThreads(MessageThread thread, int pageStart, int offset,
			int pageSize) {
		if(pageStart == 0) pageStart = 1;
		if(pageSize  == 0) pageSize = 20;
		
		/*
		 * Q classes are query classes - they are objects that contain "columns"
		 *  to be used when generating SQL select statements
		 */
		QMessageThread qmessageThread = QMessageThread.messageThread;
		
		/*
		 * boolean builders are java conditional statements
		 * that are used for SQL WHERE clauses 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		
		if (thread.getParticipant1Id() != null && thread.getParticipant1Id() > 0) {
			builder.and(qmessageThread.participant1Id.eq(thread.getParticipant1Id()).
					or(qmessageThread.participant2Id.eq(thread.getParticipant1Id())));
		}
		
		if (thread.getParticipant2Id() != null && thread.getParticipant2Id() > 0) {
			builder.and(qmessageThread.participant1Id.eq(thread.getParticipant2Id()).
					or(qmessageThread.participant2Id.eq(thread.getParticipant2Id())));
		}
		
		if (thread.getSeen1() != null) {
			if (thread.getSeen1()) {
				builder.and(qmessageThread.seen1.isTrue());
			} else {
				builder.and(qmessageThread.seen1.isFalse());
			}
		}
		
		if (thread.getSeen2() != null) {
			if (thread.getSeen2()) {
				builder.and(qmessageThread.seen2.isTrue());
			} else {
				builder.and(qmessageThread.seen2.isFalse());
			}
		}
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "tstamp");
		// creates page info that fetches pageSize number of users
		Pageable pageReq = new PageRequest(pageStart -1, pageSize, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<MessageThread> threads = messageThreadRepository.findAll(builder.getValue(), pageReq);
		return threads;
	}

	@Override
	public Page<Message> findAllMessages(Message message, int pageStart,
			int offset, int pageSize) {
		if(pageStart == 0) pageStart = 1;
		if(pageSize  == 0) pageSize = 20;
		
		/*
		 * Q classes are query classes - they are objects that contain "columns"
		 *  to be used when generating SQL select statements
		 */
		QMessage qmessage = QMessage.message1;
		
		/*
		 * boolean builders are java conditional statements
		 * that are used for SQL WHERE clauses 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		
		if (message.getThreadId() != null && message.getThreadId() > 0) {
			builder.and(qmessage.threadId.eq(message.getThreadId()));
		}
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "id");
		// creates page info that fetches pageSize number of users
		Pageable pageReq = new PageRequest(pageStart -1, pageSize, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<Message> messages = messageRepository.findAll(builder.getValue(), pageReq);
		return messages;
	}
}

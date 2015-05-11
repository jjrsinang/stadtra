package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cmsc128.stadtra.entities.Notification;
import com.cmsc128.stadtra.repository.NotificationRepository;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

	@Resource
	private NotificationRepository repository;
	
	@Override
	public Notification create(Notification notification) {
		return repository.save(notification);
	}

}

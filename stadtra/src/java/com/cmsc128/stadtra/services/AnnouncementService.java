package com.cmsc128.stadtra.services;

import org.springframework.data.domain.Page;

import com.cmsc128.stadtra.entities.Announcement;
import com.toolkt.utils.model.ApplicationException;

public interface AnnouncementService {
	Announcement	create(Announcement announcement);
	Announcement	findById(Long id);
	Announcement	update(Announcement updated) throws ApplicationException;
	Announcement	delete(Long id) throws ApplicationException;
	
	Page<Announcement>	findAll(Announcement announcement, int pageStart, int offset, int pageSize);
}

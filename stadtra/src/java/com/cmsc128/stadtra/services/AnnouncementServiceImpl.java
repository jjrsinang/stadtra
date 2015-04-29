package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsc128.stadtra.entities.Announcement;
import com.cmsc128.stadtra.entities.QAnnouncement;
import com.cmsc128.stadtra.repository.AnnouncementRepository;
import com.mysema.query.BooleanBuilder;
import com.toolkt.utils.model.ApplicationException;

@Service("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {

	@Resource
	private AnnouncementRepository announcementRepository; // repository that connects with database
	
	@Transactional
	@Override
	public Announcement create(Announcement announcement) {
		return announcementRepository.save(announcement);
	}

	@Transactional
	@Override
	public Announcement findById(Long id) {
		return announcementRepository.findOne(id);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Announcement update(Announcement updated) throws ApplicationException {
		Announcement announcement = announcementRepository.findOne(updated.getId());
		if (announcement == null) {
			throw new ApplicationException("Announcement does not exist.");
		}
		
		return announcementRepository.save(updated);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Announcement delete(Long id) throws ApplicationException {
		Announcement announcement = announcementRepository.findOne(id);
		if (announcement == null) {
			throw new ApplicationException("Announcement does not exist.");
		}
		
		announcementRepository.delete(announcement);
		return null;
	}

	@Override
	public Page<Announcement> findAll(Announcement announcement, int pageStart, int offset, int pageSize) {
		if(pageStart == 0) pageStart = 1;
		if(pageSize  == 0) pageSize = 20;
		
		/*
		 * Q classes are query classes - they are objects that contain "columns"
		 *  to be used when generating SQL select statements
		 */
		QAnnouncement qannouncement = QAnnouncement.announcement;
		
		/*
		 * boolean builders are java conditional statements
		 * that are used for SQL WHERE clauses 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qannouncement.id.goe(1));
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "id");
		// creates page info that fetches pageSize number of users
		Pageable pageReq = new PageRequest(pageStart -1, pageSize, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<Announcement> announcements = announcementRepository.findAll(builder.getValue(), pageReq);
		return announcements;
	}

}

package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsc128.stadtra.entities.Subject;
import com.cmsc128.stadtra.repository.SubjectRepository;
import com.toolkt.utils.model.ApplicationException;
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService{
	@Resource
	private SubjectRepository subjectRepository; // repository that connects with database
	
	@Transactional
	@Override
	public Subject create(Subject Subject) {
		return subjectRepository.save(Subject);
	}

	@Transactional
	@Override
	public Subject findById(Long id) {
		return subjectRepository.findOne(id);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Subject update(Subject updated) throws ApplicationException {
		Subject Subject = subjectRepository.findOne(updated.getId());
		if (Subject == null) {
			throw new ApplicationException("Subject does not exist.");
		}
		
		return subjectRepository.save(updated);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public Subject delete(Long id) throws ApplicationException {
		Subject Subject = subjectRepository.findOne(id);
		if (Subject == null) {
			throw new ApplicationException("Subject does not exist.");
		}
		subjectRepository.delete(id);
		return null;
	}

	}

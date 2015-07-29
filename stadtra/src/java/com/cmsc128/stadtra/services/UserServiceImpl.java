package com.cmsc128.stadtra.services;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmsc128.stadtra.entities.User;
import com.cmsc128.stadtra.repository.UserRepository;
import com.mysema.query.BooleanBuilder;
import com.toolkt.utils.StringUtils;
import com.toolkt.utils.model.ApplicationException;

import com.cmsc128.stadtra.entities.QUser;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository; // repository that connects with database
	
	@Transactional
	@Override
	public User create(User user) {
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public User update(User updated) throws ApplicationException {
		User user = userRepository.findOne(updated.getId());
		if (user == null) {
			throw new ApplicationException("User does not exist.");
		}
		
		return userRepository.save(updated);
	}

	@Transactional(rollbackFor=ApplicationException.class)
	@Override
	public User delete(Long id) throws ApplicationException {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new ApplicationException("User does not exist.");
		}
		
		userRepository.delete(user);
		return null;
	}

	@Override
	public Page<User> findAll(User user, int pageStart, int offset, int pageSize) {
		if(pageStart == 0) pageStart = 1;
		if(pageSize  == 0) pageSize = 20;
		
		/*
		 * Q classes are query classes - they are objects that contain "columns"
		 *  to be used when generating SQL select statements
		 */
		QUser quser = QUser.user;
		
		/*
		 * boolean builders are java conditional statements
		 * that are used for SQL WHERE clauses 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		
		if (user.getId() != null && user.getId() > 0) {
			builder.and(quser.id.eq(user.getId())); // translates to "... AND t_user.id = id"
		}
		
		if (StringUtils.hasText(user.getLoginId())) {
			builder.and(quser.loginId.eq(user.getLoginId()));
		}
		
		if (StringUtils.hasText(user.getPassword())) {
			builder.and(quser.password.eq(user.getPassword()));
		}
		
		if (StringUtils.hasText(user.getfName())) {
			builder.and(quser.fName.eq(user.getfName()));
		}
		
		if (StringUtils.hasText(user.getmName())) {
			builder.and(quser.mName.eq(user.getmName()));
		}
		
		if (StringUtils.hasText(user.getlName())) {
			builder.and(quser.lName.eq(user.getlName()));
		}
		
		if (StringUtils.hasText(user.getRole())) {
			builder.and(quser.role.eq(user.getRole()));
		}
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "id");
		// creates page info that fetches pageSize number of users
		Pageable pageReq = new PageRequest(pageStart -1, pageSize, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<User> users = userRepository.findAll(builder.getValue(), pageReq);
		return users;
	}

	@Override
	public Page<User> findAllLike(User user, int pageStart, int offset, int pageSize) {
		if(pageStart == 0) pageStart = 1;
		if(pageSize  == 0) pageSize = 20;
		
		/*
		 * Q classes are query classes - they are objects that contain "columns"
		 *  to be used when generating SQL select statements
		 */
		QUser quser = QUser.user;
		
		/*
		 * boolean builders are java conditional statements
		 * that are used for SQL WHERE clauses 
		 */
		BooleanBuilder builder = new BooleanBuilder();
		
		if (user.getId() != null && user.getId() > 0) {
			builder.and(quser.id.eq(user.getId())); // translates to "... AND t_user.id = id"
		}
		
		if (StringUtils.hasText(user.getLoginId())) {
			builder.and(quser.loginId.eq(user.getLoginId()));
		}
		
		if (StringUtils.hasText(user.getPassword())) {
			builder.and(quser.password.eq(user.getPassword()));
		}
		
		if (StringUtils.hasText(user.getfName())) {
			builder.and(quser.fName.eq(user.getfName()));
		}
		
		if (StringUtils.hasText(user.getmName())) {
			builder.and(quser.mName.eq(user.getmName()));
		}
		
		if (StringUtils.hasText(user.getlName())) {
			builder.and(quser.lName.contains(user.getlName()));
		}
		
		if (StringUtils.hasText(user.getRole())) {
			builder.and(quser.role.eq(user.getRole()));
		}
		
		// sort results by id. similar to "... ORDER BY id DESC"
		Sort.Order order = new Sort.Order(Sort.Direction.DESC,  "id");
		// creates page info that fetches pageSize number of users
		Pageable pageReq = new PageRequest(pageStart -1, pageSize, new Sort(order)); //zero-indexed
		// creates and executes an SQL statement based on above info
		Page<User> users = userRepository.findAll(builder.getValue(), pageReq);
		return users;
	}
}

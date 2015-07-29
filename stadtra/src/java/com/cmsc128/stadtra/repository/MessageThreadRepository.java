package com.cmsc128.stadtra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.cmsc128.stadtra.entities.MessageThread;

public interface MessageThreadRepository extends JpaRepository<MessageThread, Long>,
		QueryDslPredicateExecutor<MessageThread> {

}

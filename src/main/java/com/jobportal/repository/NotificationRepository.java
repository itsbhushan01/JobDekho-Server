package com.jobportal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jobportal.dto.NotificationStatus;
import com.jobportal.entity.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Integer>{
	public List<Notification> findByUserIdAndStatus(int userId,NotificationStatus status);
}

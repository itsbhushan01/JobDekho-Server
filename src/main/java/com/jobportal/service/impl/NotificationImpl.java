package com.jobportal.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.NotificationDTO;
import com.jobportal.dto.NotificationStatus;
import com.jobportal.entity.Notification;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.NotificationRepository;
import com.jobportal.service.NotificationService;

@Service
public class NotificationImpl implements NotificationService{

	@Autowired
	private NotificationRepository notiRepo;

	@Override
	public void sendNotification(NotificationDTO notificationDTO) {
		notificationDTO.setStatus(NotificationStatus.UNREAD);
		notificationDTO.setTimestamp(LocalDateTime.now());
		notiRepo.save(notificationDTO.toEntity());
	}

	@Override
	public List<Notification> getUnreadNotification(int userId) {
		return notiRepo.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
	}

	@Override
	public void readNotification(int id) throws JobPortalException {
		Notification noti=notiRepo.findById(id).orElseThrow(()->new JobPortalException("NO_NOTIFICATION_FOUND"));
		noti.setStatus(NotificationStatus.READ);
		notiRepo.save(noti);
	}
}

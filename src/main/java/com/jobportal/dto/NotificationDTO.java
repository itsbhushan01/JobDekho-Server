package com.jobportal.dto;

import java.time.LocalDateTime;

import com.jobportal.entity.Notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {
	private int id;
	private int userId;
	private String message;
	private String action;
	private String route;
	private LocalDateTime timestamp;
	private NotificationStatus status;
	
	public Notification toEntity() {
		return new Notification(this.id,this.userId,this.message,this.action,this.route,this.timestamp,this.status);
	}
}

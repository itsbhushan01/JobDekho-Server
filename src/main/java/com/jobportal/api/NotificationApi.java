package com.jobportal.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ResponseDto;
import com.jobportal.entity.Notification;
import com.jobportal.exception.JobPortalException;
import com.jobportal.service.NotificationService;

@RestController
@CrossOrigin(origins = {"http://localhost:5173","https://jobdekho-gamma.vercel.app"})
@RequestMapping("/notification")
@Validated
public class NotificationApi {
	
	@Autowired
	private NotificationService notiService;
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<List<Notification>> getNotification(@PathVariable int userId){
		return new ResponseEntity<>(notiService.getUnreadNotification(userId),HttpStatus.OK);
	}
	@PutMapping("/read/{id}")
	public ResponseEntity<ResponseDto> readNotification(@PathVariable int id) throws JobPortalException{
		notiService.readNotification(id);
		return new ResponseEntity<>(new ResponseDto("Success"),HttpStatus.OK);
	}
}

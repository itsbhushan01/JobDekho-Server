package com.jobportal.service.impl;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.dto.UserDto;
import com.jobportal.dto.Experience;
import com.jobportal.dto.Login;
import com.jobportal.dto.NotificationDTO;
import com.jobportal.dto.ResponseDto;
import com.jobportal.entity.OTP;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.NotificationRepository;
import com.jobportal.repository.OTPRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.NotificationService;
import com.jobportal.service.ProfileService;
import com.jobportal.service.UserService;
import com.jobportal.utility.Data;
import com.jobportal.utility.Utilities;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service

public class UserServiceImple implements UserService{
	
	@Autowired
	private OTPRepository otpRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private NotificationService notiService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;
	@Override
	public UserDto userRegister(UserDto userDto) throws JobPortalException {
			Experience exp=new Experience();
			Optional<User> optional=userRepository.findByEmail(userDto.getEmail());
			if(optional.isPresent())throw new JobPortalException("User Found");
			userDto.setProfileId(profileService.createProfile(userDto.getName()));
			User user=userDto.toEntity();
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			user=userRepository.save(user);
			return user.toDto();
	}

	@Override
	public UserDto userLogin(Login login) throws JobPortalException{
		User users=userRepository.findByEmail(login.getEmail()).orElseThrow(()->new JobPortalException("User Not Found"));
		
		
		if(!passwordEncoder.matches(login.getPassword(),users.getPassword()))throw new JobPortalException(("Invalid Creadentials")); 
		
		return users.toDto();
		
	}

	@Override
	public void sendOTP(String email) throws Exception {


		MimeMessage mm=mailSender.createMimeMessage();
		MimeMessageHelper message=new MimeMessageHelper(mm,true);
		message.setTo(email);
		message.setSubject("Your OTP Code");
		String genOtp=Utilities.generateOTP();
		OTP otp=new OTP(email,genOtp,LocalDateTime.now());
		otpRepository.save(otp);
		message.setText(genOtp,false);
		mailSender.send(mm);
	}

	@Override
	public void verifyOTP(String email,String otp) throws JobPortalException {
		 OTP otpEntity=otpRepository.findById(email).orElseThrow(()->new JobPortalException("OTP is expired"));
		 if(!otpEntity.getOtpCode().equals(otp))throw new JobPortalException("OTP is incorrect");
	}

	public ResponseDto changePassword(Login login)throws JobPortalException {
		User users=userRepository.findByEmail(login.getEmail()).orElseThrow(()->new JobPortalException("User Not Found"));
		users.setPassword(passwordEncoder.encode(login.getPassword()));
		userRepository.save(users);
		NotificationDTO noti=new NotificationDTO();
		noti.setUserId(users.getId());
		noti.setMessage("Password Reset Successfully");
		noti.setAction("Password Reset");
		notiService.sendNotification(noti);
		return new ResponseDto("Password changed Successfully");
	}

	@Override
	public UserDto getUserByEmail(String email) throws JobPortalException {
		return userRepository.findByEmail(email).orElseThrow(()->new JobPortalException("User Not Found")).toDto();
	}
	
//	@Scheduled(fixedRate = 3000)
//	public void removeExpiredOtp() {
//		LocalDateTime exp=LocalDateTime.now().minusMinutes(5);
//		List<OTP> expiredOTP=otpRepository.findByCreationTimeBefore(exp);
//		if(!expiredOTP.isEmpty()) {
//			otpRepository.deleteAll(expiredOTP);
//			System.out.println("Removed "+expiredOTP.size()+" expired OTPs");
//		}
//	}
	
		
}
	
	



package com.jobportal.service;

import org.springframework.stereotype.Service;

import com.jobportal.dto.UserDto;
import com.jobportal.dto.Login;
import com.jobportal.dto.ResponseDto;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;

@Service
public interface UserService {
	
	public UserDto userRegister(UserDto userDto) throws  JobPortalException ; 
	
	public UserDto getUserByEmail(String email) throws JobPortalException;
	
	public UserDto userLogin(Login login) throws  JobPortalException;
	
	public void  sendOTP(String email) throws JobPortalException,Exception;
	
	public void verifyOTP(String email,String otp) throws JobPortalException;
	
	public ResponseDto changePassword(Login login) throws JobPortalException;
}

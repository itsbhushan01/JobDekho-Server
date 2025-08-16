package com.jobportal.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.Login;
import com.jobportal.dto.ResponseDto;
import com.jobportal.dto.UserDto;

import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.service.impl.UserServiceImple;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@RestController
@CrossOrigin(origins = {"http://localhost:5173","https://jobdekho-gamma.vercel.app"})
@RequestMapping("/users")
@Validated
public class UserApi {
	
	@Autowired
	private UserServiceImple service;
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody  UserDto userDto) throws JobPortalException{
		userDto=service.userRegister(userDto);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody  Login login) throws JobPortalException{
		System.out.println(login);
		return new ResponseEntity<UserDto>(service.userLogin(login), HttpStatus.OK);
	}

	@PostMapping("/sendOTP/{email}")
	public ResponseEntity<ResponseDto> sendOTP(@PathVariable @Email(message="Email is Invalid") String email) throws Exception{
		service.sendOTP(email);
		
		return new ResponseEntity<>(new ResponseDto("OTP sent Successfully"), HttpStatus.OK);
	}
	
	@GetMapping("/verifyOTP/{email}/{otp}")
	public  ResponseEntity<ResponseDto> verifyOTP(@PathVariable @Email(message="Email is Invalid") String email,@PathVariable @Pattern(regexp="^[0-9]{6}$",message="OTP is Invalid") String otp) throws JobPortalException{
		System.out.println(email+" "+ otp);
		service.verifyOTP(email,otp);
		return new ResponseEntity<>(new ResponseDto("OTP has been verified"),HttpStatus.OK);
	}
	
	@PostMapping("/changePass")
	public ResponseEntity<ResponseDto> changePassword(@RequestBody  Login login) throws JobPortalException{
		System.out.println(login);
		return new ResponseEntity<ResponseDto>(service.changePassword(login), HttpStatus.OK);
	}
	
}

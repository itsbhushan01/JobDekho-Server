package com.jobportal.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ProfileDTO;
import com.jobportal.dto.ResponseDto;
import com.jobportal.entity.Profile;
import com.jobportal.exception.JobPortalException;
import com.jobportal.service.ProfileService;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@RestController
@CrossOrigin(origins = {"http://localhost:5173","https://jobdekho-gamma.vercel.app"})
@RequestMapping("/profiles")
public class ProfileApi {

	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/get/{id}")
	public  ResponseEntity<ProfileDTO> getProfile(@PathVariable int id) throws JobPortalException{
		
		return new ResponseEntity<>(profileService.getProfile(id),HttpStatus.OK);
	}
	@GetMapping("/getall")
	public  ResponseEntity<List<ProfileDTO>> getAllProfile() throws JobPortalException{
		
		return new ResponseEntity<>(profileService.getAllProfile(),HttpStatus.OK);
	}
	@PutMapping("/update")
	public  ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDto) throws JobPortalException{
		System.out.println(profileDto);
		return new ResponseEntity<>(profileService.updateProfile(profileDto),HttpStatus.OK);
	}
}

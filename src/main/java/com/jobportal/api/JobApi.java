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

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.Application;
import com.jobportal.dto.JobDTO;
import com.jobportal.dto.ResponseDto;
import com.jobportal.dto.UserDto;
import com.jobportal.exception.JobPortalException;
import com.jobportal.service.impl.JobServiceImpl;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@RestController
@CrossOrigin(origins = {"http://localhost:5173","https://jobdekho-gamma.vercel.app"})
@RequestMapping("/job")
public class JobApi {

	@Autowired
	private JobServiceImpl jobService;
	

	@PostMapping("/post")
	public ResponseEntity<JobDTO> postjob(@RequestBody  JobDTO jobDto) throws JobPortalException{
		System.out.println(jobDto);
		jobDto=jobService.jobsave(jobDto);
		return new ResponseEntity<>(jobDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/getall")
	public  ResponseEntity<List<JobDTO>> getAllJobs() throws JobPortalException{
		
		return new ResponseEntity<>(jobService.getAllJobs(),HttpStatus.OK);
	}
	
	
	@GetMapping("/get/{id}")
	public  ResponseEntity<JobDTO> getJob(@PathVariable  int id) throws JobPortalException{
		return new ResponseEntity<>(jobService.findJob(id),HttpStatus.OK);
	}
	
	@PostMapping("/apply/{id}")
	public ResponseEntity<ResponseDto> applyjob(@PathVariable int id,@RequestBody ApplicantDTO applicantDto) throws JobPortalException{
		System.out.println(applicantDto);
		
		jobService.applyJob(id,applicantDto);
		return new ResponseEntity<>(new ResponseDto("Applied Suucessfully"), HttpStatus.OK);
	}
	
	@GetMapping("/postedby/{id}")
	public  ResponseEntity<List<JobDTO>> getJobsPostedBy(@PathVariable Long id) throws JobPortalException{
		return new ResponseEntity<>(jobService.getAllJobsPostedBy(id),HttpStatus.OK);
	}
	
	@PutMapping("/updatedjob")
	public ResponseEntity<JobDTO> updateJob(@RequestBody JobDTO jobdto ){
		return new ResponseEntity<>(jobService.updateJob(jobdto),HttpStatus.OK);
	}
	
	@PostMapping("/changeappstatus")
	public ResponseEntity<ResponseDto> changeAppStatus(@RequestBody Application application) throws JobPortalException{
		System.out.println(application);
		jobService.changeAppStatus(application);
		return new ResponseEntity<>(new ResponseDto("Application Status Change Suucessfully"), HttpStatus.OK);
	}
}

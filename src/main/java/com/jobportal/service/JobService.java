package com.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.Application;
import com.jobportal.dto.JobDTO;
import com.jobportal.entity.Job;
import com.jobportal.exception.JobPortalException;

@Service
public interface JobService {
	public JobDTO jobsave(JobDTO jobDto) throws JobPortalException;

	List<JobDTO> getAllJobs();
	
	public JobDTO findJob(int id) throws JobPortalException;
	
	public void applyJob(int id,ApplicantDTO applicantDTO) throws JobPortalException;
	
	public List<JobDTO> getAllJobsPostedBy(Long id) throws JobPortalException;
	
	public void changeAppStatus(Application application) throws JobPortalException;
	
	public JobDTO updateJob(JobDTO jobdto);
}

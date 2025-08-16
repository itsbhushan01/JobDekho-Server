package com.jobportal.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.ProfileDTO;
import com.jobportal.dto.Application;
import com.jobportal.dto.ApplicationStatus;
import com.jobportal.dto.JobDTO;
import com.jobportal.dto.JobStatus;
import com.jobportal.dto.NotificationDTO;
import com.jobportal.entity.Applicant;
import com.jobportal.entity.Job;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.JobRepository;
import com.jobportal.service.JobService;
import com.jobportal.service.NotificationService;

@Transactional
@Service
public class JobServiceImpl implements JobService{
	
	@Autowired 
	private JobRepository jobRepo;
	
	@Autowired
	private NotificationService notiService;

	public JobDTO jobsave(JobDTO jobDto) throws JobPortalException {
		if(jobDto.getId()==0) {
			jobDto.setPostTime(LocalDateTime.now());
			NotificationDTO notiDto=new NotificationDTO();
			notiDto.setAction("Job Posted");
			notiDto.setMessage("JobPosted Successfully for : "+jobDto.getJobTitle()+" at "+ jobDto.getCompany());
			notiDto.setUserId(jobDto.getPostedBy());
			notiDto.setRoute("/posted-jobs/"+jobDto.getId());
			notiService.sendNotification(notiDto);
		}
		else {
			Job job=jobRepo.findById(jobDto.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
			if((job.getJobStatus().equals(JobStatus.DRAFT))||(jobDto.getJobStatus().equals(JobStatus.CLOSED))) {
				jobDto.setPostTime(LocalDateTime.now());
			}
			}
		
		return jobRepo.save(jobDto.toEntity()).toDto();
	}
	
	@Override
	public List<JobDTO> getAllJobs() {
		
		return StreamSupport.stream(jobRepo.findAll().spliterator(), false).map(job->job.toDto()).collect(Collectors.toList());
	}

	public JobDTO findJob(int id) throws JobPortalException {
		
		return jobRepo.findById(id).orElseThrow(()->new JobPortalException("Job Not Found")).toDto();
	}
	
	
	public void applyJob(int id,ApplicantDTO applicantDto) throws JobPortalException {
	
		Job job=jobRepo.findById(id).orElseThrow(()->new JobPortalException("JOB NOT FOUND"));
		List<Applicant> applicants=job.getApplicant();
		System.out.println(job.getId());
		if(applicants==null)applicants=new ArrayList<>();
		if(applicants.stream().filter((x)->x.getApplicantId()==applicantDto.getApplicantId()).toList().size()>0)throw new JobPortalException("JOB Applied Already");
		applicantDto.setApplicationStatus(ApplicationStatus.APPLIED);
		applicants.add(applicantDto.toEntity());
		job.setApplicant(applicants);				
		jobRepo.save(job);
		
		
	}

	@Override
	public List<JobDTO> getAllJobsPostedBy(Long id) throws JobPortalException {
		
		return  StreamSupport.stream(jobRepo.findByPostedBy(id).spliterator(), false).map(job->job.toDto()).collect(Collectors.toList());
	}

	@Transactional
	public void changeAppStatus(Application application) throws JobPortalException {
		Job job=jobRepo.findById(application.getId()).orElseThrow(()->new JobPortalException("JOB_NOT_FOUND"));
		List<Applicant>applicant=job.getApplicant().stream().map((x)->{
			if(application.getApplicantId()==x.getApplicantId()) {
				x.setApplicationStatus(application.getApplicationStatus());
				if(application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)) {
					x.setInterviewTime(application.getInterviewTime());

					
				}
			}
			return x;
		}).toList();
		System.out.println("applicant : "+applicant);
		job.setApplicant(applicant);
		System.out.println("job : "+job);
		jobRepo.save(job);
			
	}

	public JobDTO updateJob(JobDTO jobdto) {
		
		return jobRepo.save(jobdto.toEntity()).toDto();
	}
	
	
}

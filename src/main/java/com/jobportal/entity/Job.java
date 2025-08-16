package com.jobportal.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.JobDTO;
import com.jobportal.dto.JobStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String jobTitle;
	private String company;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="job_id")
	private List<Applicant> applicant;
	private String about;
	private String experience;
	private String jobType;
	private String location;
	private String packageOffered;
	private LocalDateTime postTime;
	private String description;
	private List<String>skills;
	private JobStatus jobStatus;
	private int postedBy;
	
	
	
	
	public JobDTO toDto() {
		return new JobDTO(this.id,this.jobTitle,this.company,this.applicant!=null?this.applicant.stream().map((x)->x.toDto()).toList():null,this.about,this.experience,this.jobType,this.location,this.packageOffered,this.postTime,this.description,this.skills,this.jobStatus,this.postedBy);
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getJobTitle() {
		return jobTitle;
	}




	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}




	public String getCompany() {
		return company;
	}




	public void setCompany(String company) {
		this.company = company;
	}




	public List<Applicant> getApplicant() {
		return applicant;
	}




	public void setApplicant(List<Applicant> applicant) {
		this.applicant = applicant;
	}




	public String getAbout() {
		return about;
	}




	public void setAbout(String about) {
		this.about = about;
	}




	public String getExperience() {
		return experience;
	}




	public void setExperience(String experience) {
		this.experience = experience;
	}




	public String getJobType() {
		return jobType;
	}




	public void setJobType(String jobType) {
		this.jobType = jobType;
	}




	public String getLocation() {
		return location;
	}




	public void setLocation(String location) {
		this.location = location;
	}




	public String getPackageOffered() {
		return packageOffered;
	}




	public void setPackageOffered(String packageOffered) {
		this.packageOffered = packageOffered;
	}




	public LocalDateTime getPostTime() {
		return postTime;
	}




	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public List<String> getSkills() {
		return skills;
	}




	public void setSkills(List<String> skills) {
		this.skills = skills;
	}




	public JobStatus getJobStatus() {
		return jobStatus;
	}




	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}




	@Override
	public String toString() {
		return "Job [id=" + id + ", jobTitle=" + jobTitle + ", company=" + company + ", applicant=" + applicant
				+ ", about=" + about + ", experience=" + experience + ", jobType=" + jobType + ", location=" + location
				+ ", packageOffered=" + packageOffered + ", postTime=" + postTime + ", description=" + description
				+ ", skills=" + skills + ", jobStatus=" + jobStatus + ", postedBy=" + postedBy + "]";
	}
	
}

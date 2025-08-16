package com.jobportal.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;

import com.jobportal.dto.ApplicantDTO;
import com.jobportal.dto.ApplicationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {
	
	@Id
	private int applicantId;
	private String name;
	private String email;
	private Long phone;
	private String website;
	private byte[] resume;
	private String coverLetter;
	private LocalDateTime timestamp;
	private ApplicationStatus applicationStatus;
	private LocalDateTime interviewTime;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getCoverLetter() {
		return coverLetter;
	}
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public int getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	
	
	public byte[] getResume() {
		return resume;
	}
	public void setResume(byte[] resume) {
		this.resume = resume;
	}
	public LocalDateTime getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(LocalDateTime interviewTime) {
		this.interviewTime = interviewTime;
	}
	
	
	public ApplicantDTO toDto() {
		return new ApplicantDTO(this.applicantId,this.name,this.email,this.phone,this.website,this.resume!=null?Base64.getEncoder().encodeToString(this.resume):null,
				this.coverLetter,this.timestamp,this.applicationStatus,this.interviewTime);
	}
	@Override
	public String toString() {
		return "Applicant [applicantId=" + applicantId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", website=" + website + ", resume=" + Arrays.toString(resume) + ", coverLetter=" + coverLetter
				+ ", timestamp=" + timestamp + ", applicationStatus=" + applicationStatus + ", interviewTime="
				+ interviewTime + "]";
	}
}

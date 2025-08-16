package com.jobportal.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.jobportal.entity.Job;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
	private int id;
	private String jobTitle;
	private String company;
	private List<ApplicantDTO> applicant;
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
	public Job toEntity() {
		return new Job(this.id,this.jobTitle,this.company,this.applicant!=null?this.applicant.stream().map((x)->x.toEntity()).toList():null,this.about,this.experience,this.jobType,this.location,this.packageOffered,this.postTime,this.description,this.skills,this.jobStatus,this.postedBy);
	}
	@Override
	public String toString() {
		return "JobDTO [id=" + id + ", jobTitle=" + jobTitle + ", company=" + company + ", applicant=" + applicant
				+ ", about=" + about + ", experience=" + experience + ", jobType=" + jobType + ", location=" + location
				+ ", packageOffered=" + packageOffered + ", postTime=" + postTime + ", description=" + description
				+ ", skills=" + skills + ", jobStatus=" + jobStatus + ", postedBy=" + postedBy + "]";
	}
}

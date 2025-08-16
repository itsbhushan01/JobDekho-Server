package com.jobportal.entity;

import java.util.Base64;
import java.util.List;

import com.jobportal.dto.Certification;
import com.jobportal.dto.Experience;
import com.jobportal.dto.ProfileDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String role;
	private String company;
	private String location;
	private String about;
	private List<String>skills;
	private int totalExperience;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	private List<Experience> experience;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="profile_id")
	private List<Certification> certification;
	
	private List<Long>savedJobs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Experience> getExperience() {
		return experience;
	}
	
	public List<Certification> getCertification() {
		return certification;
	}

	public void setCertification(List<Certification> certification) {
		this.certification = certification;
	}

	public void setExperience(List<Experience> experience) {
		this.experience = experience;
	}
	
	
	public ProfileDTO toDto() {
		return new ProfileDTO(this.id,this.name,this.role,this.company,this.location,this.about,this.totalExperience,this.skills,this.certification,this.experience,this.savedJobs); 
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public int getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(int totalExperience) {
		this.totalExperience = totalExperience;
	}

	public List<Long> getSavedJobs() {
		return savedJobs;
	}

	public void setSavedJobs(List<Long> savedJobs) {
		this.savedJobs = savedJobs;
	}

	
}

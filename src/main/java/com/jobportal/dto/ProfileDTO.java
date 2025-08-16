package com.jobportal.dto;

import java.util.List;
import java.util.Base64;
import com.jobportal.entity.Profile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDTO {
	
	private int id;
	private String name;
	private String role;
	private String company;
	private String location;
	private String about;
	private int totalExperience;
	private List<String>skills;
	private List<Certification> certification;
	private List<Experience> experience;
	private List<Long>savedJobs;
	
	public Profile toEntity() {
		return new Profile(this.id,this.name,this.role,this.company,this.location,this.about,this.skills,this.totalExperience,this.experience,this.certification,this.savedJobs);
	}

	@Override
	public String toString() {
		return "ProfileDTO [id=" + id + ", name=" + name + ", role=" + role + ", company=" + company + ", location="
				+ location + ", about=" + about + ", totalExperience=" + totalExperience + ", skills=" + skills
				+ ", certification=" + certification + ", experience=" + experience + ", savedJobs=" + savedJobs + "]";
	}
}

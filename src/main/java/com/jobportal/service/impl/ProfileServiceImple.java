package com.jobportal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.Certification;
import com.jobportal.dto.Experience;
import com.jobportal.dto.ProfileDTO;
import com.jobportal.entity.Profile;
import com.jobportal.exception.JobPortalException;
import com.jobportal.repository.ProfileRepository;
import com.jobportal.service.ProfileService;

@Service
public class ProfileServiceImple implements ProfileService{

	@Autowired
	private ProfileRepository profileRepo;
	
	@Override
	public int createProfile(String name) {
		Experience exp=new Experience();
		exp.setCompany(null);
		exp.setEndDate(null);
		exp.setLocation(null);
		exp.setStartDate(null);
		exp.setTitle(null);
		exp.setDiscription(null);
		List<Experience> exp1=new ArrayList<>();
		exp1.add(exp);
		
		Certification certi=new Certification();
		certi.setCertificateId(null);
		certi.setIssueDate(null);
		certi.setName(null);
	
		List<Certification> certi1=new ArrayList<>();
		certi1.add(certi);
		Profile profile=new Profile();
		profile.setName(name);
		profile.setSkills(new ArrayList<>());
		profile.setCertification(certi1);
		profile.setExperience(exp1);
		profileRepo.save(profile);
		return profile.getId();
	}

	@Override
	public ProfileDTO getProfile(int id) throws JobPortalException {
		
		return profileRepo.findById(id).orElseThrow(()->new JobPortalException("Profile Not Found")).toDto();
	}

	@Override
	public ProfileDTO updateProfile(ProfileDTO profileDto) throws JobPortalException{
		profileRepo.findById(profileDto.getId()).orElseThrow(()->new JobPortalException("Profile Not Found"));
		profileRepo.save(profileDto.toEntity());
		return profileDto;
	}

	@Override
	public List<ProfileDTO> getAllProfile() throws JobPortalException {
		
		return StreamSupport.stream(profileRepo.findAll().spliterator(), false).map(job->job.toDto()).collect(Collectors.toList());
	}

}

package com.jobportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobportal.dto.ProfileDTO;
import com.jobportal.entity.Profile;
import com.jobportal.exception.JobPortalException;

@Service
public interface ProfileService {
	public int createProfile(String name); 
	public ProfileDTO getProfile(int id) throws JobPortalException ;
	public ProfileDTO updateProfile(ProfileDTO profileDto) throws JobPortalException;
	public List<ProfileDTO> getAllProfile() throws JobPortalException;
}

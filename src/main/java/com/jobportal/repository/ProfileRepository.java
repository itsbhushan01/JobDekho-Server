package com.jobportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.Profile;



@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer>{

}

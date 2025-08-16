package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.jobportal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Integer>{
	public List<Job>findByPostedBy(Long postedBy);
}

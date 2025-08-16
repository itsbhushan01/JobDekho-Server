package com.jobportal.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.OTP;

@Repository
public interface OTPRepository extends CrudRepository<OTP, String>{
	List<OTP>findByCreationTimeBefore(LocalDateTime expiry);
}

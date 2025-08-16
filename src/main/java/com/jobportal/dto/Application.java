package com.jobportal.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {
	private int id;
	private int applicantId;
	private LocalDateTime interviewTime;
	private ApplicationStatus applicationStatus;
}
								
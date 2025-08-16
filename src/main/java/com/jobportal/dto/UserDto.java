package com.jobportal.dto;

import com.jobportal.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private int id;
	private String name;
	private String email;
	private String password;
	private AccountType accountType;
	private int profileId;
	
	public User toEntity() {
		return new User(this.id,this.name,this.email,this.password,this.accountType,this.profileId);
	}
	
	
}

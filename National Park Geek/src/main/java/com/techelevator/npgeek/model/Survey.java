package com.techelevator.npgeek.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class Survey {
	
	@NotEmpty(message="*Park name is required*")
	private String parkCode;
	
	@NotBlank(message="*Email is required*")
	@Email(message="*Email must be a valid email address*")
	private String emailAddress;
	
	@NotEmpty(message="*State is required*")
	private String state;
	
	@NotEmpty(message="*Activity level is required*")
	private String activityLevel;
	
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	
	

}

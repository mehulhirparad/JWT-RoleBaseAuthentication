package com.employee.mgt.user;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.employee.mgt.userValidation.FieldMatch;
import com.employee.mgt.userValidation.ValidEmail;
import com.employee.mgt.userValidation.ValidMobile;


// This class use for just the validation checking while new user try to register or sign up
@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class RegisterUser {

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String first_name;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String last_name;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	@ValidMobile
	@NotNull(message = "is required")
	@Size(min = 1, max = 10, message = "is required")
	private String mobile;
	
	private String roles;

	public RegisterUser() {

	}
	

	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}		
}

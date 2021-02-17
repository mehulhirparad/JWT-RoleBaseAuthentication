package com.employee.mgt.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticationResponse {

	private String token;
	private String userName;
	private Collection<? extends GrantedAuthority> role;
	
	public AuthenticationResponse()
	{
		
	}
	

	public AuthenticationResponse(String token, String userName, Collection<? extends GrantedAuthority> collection) {
		super();
		this.token = token;
		this.userName = userName;
		this.role = collection;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Collection<? extends GrantedAuthority> getRole() {
		return role;
	}

	public void setRole(Collection<? extends GrantedAuthority> role) {
		this.role = role;
	}
	
	
	
}

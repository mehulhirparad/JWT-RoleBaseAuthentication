package com.employee.mgt.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

// AuthenticationRequest class which is use for getting the parameter form the JWT Token
public class AuthenticationRequest {
	
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> roles;	
	
	
	public AuthenticationRequest(String username, String password, Collection<? extends GrantedAuthority> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public AuthenticationRequest()
	{
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(Collection<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}
	
	
	

}

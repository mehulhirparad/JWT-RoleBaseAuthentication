package com.employee.mgt.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

// Design custom filter Which is filter all request coming for frontend 
@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Value("${jwt.http.request.headers}")
    private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		System.out.println("header " + this.tokenHeader);
		 try{
			// JWT Token is in the form "Bearer token". Remove Bearer word and
			// get  only the Token
			 System.out.println("request parameter "+ request.getRequestURI());
			String jwtToken = extractJwtFromRequest(request);

			if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateToken(jwtToken)) {
				UserDetails userDetails = new User(jwtTokenUtil.getUsernameFromToken(jwtToken), "",
						jwtTokenUtil.getRolesFromToken(jwtToken));
				System.out.println("Role " + jwtTokenUtil.getRolesFromToken(jwtToken));
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				System.out.println("if condition-3");
			} else {
				System.out.println("Cannot set the Security");
			}
		 }catch(ExpiredJwtException ex)
		 {
			 request.setAttribute("exception", ex);
		 }
		 catch(BadCredentialsException ex)
		 {
			 request.setAttribute("exception", ex);
		 }
		chain.doFilter(request, response);
	}

	// It generate seperation of token
	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(this.tokenHeader);
		System.out.println("beart token"+bearerToken);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			System.out.println("Vearpr");
			
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
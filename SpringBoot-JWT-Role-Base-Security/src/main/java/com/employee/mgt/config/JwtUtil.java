package com.employee.mgt.config;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


// Check Authorities base on JWT Token convey in HTTPRequest Method
@Service
public class JwtUtil {

	private String secret;
	private int jwtExpirationInMs;

	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Value("${jwt.expirationDateInMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		System.out.println(roles);
		if (roles.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			claims.put("isAdmin", true);
			System.out.println("1"+claims);
		}
		if (roles.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			claims.put("isUser", true);
			System.out.println("2"+claims);
		}
		
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// After validation of user name and pass it's generate JWT Token
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		System.out.println();
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

	}

	// Check JWT Token is valid or not
	public boolean validateToken(String authToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} catch (ExpiredJwtException ex) {
			throw ex;
		}
	}
// Get username from the JWT Token and check
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	// Get Role from the JWT Token 
	public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		List<SimpleGrantedAuthority> roles = null;
		Boolean isAdmin = claims.get("isAdmin", Boolean.class);
		Boolean isUser = claims.get("isUser", Boolean.class);
		if (isAdmin != null && isAdmin) {
			System.out.println("role has been called from isAdmin 1 "+ isAdmin);
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if (isUser != null && isUser) {
			System.out.println("role has been called from isUser 2"+ isUser);
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return roles;
	}
}

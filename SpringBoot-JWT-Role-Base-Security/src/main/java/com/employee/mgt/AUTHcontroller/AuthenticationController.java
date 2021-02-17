package com.employee.mgt.AUTHcontroller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.employee.mgt.config.JwtUtil;
import com.employee.mgt.entitys.User;
import com.employee.mgt.model.AuthenticationRequest;
import com.employee.mgt.model.AuthenticationResponse;
import com.employee.mgt.user.RegisterUser;
import com.employee.mgt.userServices.UserService;
import com.employee.mgt.userServices.UserServiceImpl;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserServiceImpl theUserServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
    private UserService userService;

	// Check the username and password filed name for fronted design
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			System.out.println(authenticationRequest.getUsername()+" "+authenticationRequest.getPassword());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword(), authenticationRequest.getRoles()));	
			
		} catch (DisabledException e) {
			
			throw new Exception("USER_DISABLED", e);
		}
		catch (BadCredentialsException e) {
			
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		UserDetails userdetails = theUserServiceImpl.loadUserByUsername(authenticationRequest.getUsername());	
		
		String token = jwtUtil.generateToken(userdetails);
		System.out.println("form dis" + token);	
		return ResponseEntity.ok(new AuthenticationResponse(token,userdetails.getUsername(),userdetails.getAuthorities()));
	}
	
	// New user Registration API
	@RequestMapping(value="/register", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON)
	@ResponseBody
	public ResponseEntity<Map> saveUser(@Valid @RequestBody RegisterUser theRegisterUser,BindingResult result)
	{
		// getUserName from REGISTERUSER class for checking user is exist or not in the system
		String userName = theRegisterUser.getUserName();
		System.out.println(""+userName);
		Map<String,String> errormap = new HashMap<String, String>();
		if(result.hasErrors()) {			
			for(FieldError error:result.getFieldErrors()) {
				errormap.put(error.getField(), error.getDefaultMessage());				
			}
			System.out.println(errormap);
			return new ResponseEntity<Map>(errormap,HttpStatus.OK);
		}
		
		// Search base on USERNAME is it exist or not 
		 User existing = userService.findByUserName(userName);
	        if (existing != null){
	        	errormap.put("message","User name already exists");
	            return new ResponseEntity<Map>(errormap,HttpStatus.OK);
	        }
	   	  userService.save(theRegisterUser);  
	   	  errormap.put("message",userName);
	   	  return new ResponseEntity<Map>(errormap,HttpStatus.OK);
		}
}

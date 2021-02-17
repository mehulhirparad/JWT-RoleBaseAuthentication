package com.employee.mgt.userServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.employee.mgt.entitys.User;
import com.employee.mgt.user.RegisterUser;
import com.employee.mgt.userDAO.UserDao;
import java.util.Arrays;
import java.util.List;



import javax.transaction.Transactional;

// Here we mention manually role of the user.
@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;	
	
	// inject BCryptPasswordEndoer
	@Autowired(required=true)
	private PasswordEncoder passwordEncoder;

	@Override

	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(RegisterUser registerUser) {
		User user = new User();
		 // assign user details to the user object
		user.setUserName(registerUser.getUserName());
		user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
		user.setFirstName(registerUser.getFirst_name());
		user.setLastName(registerUser.getLast_name());
		user.setEmail(registerUser.getEmail());
		user.setMobile(registerUser.getMobile());
		
		// provide the filed in the React form and collect the roll like 
		// ROLE_ADMIN
		// ROLE_EMPLOYEE
		// ROLE_USER etc
		
		user.setRoles("ROLE_ADMIN");	

		 // save user in the database
		userDao.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> roles = null;		
		User user = userDao.findByUserName(userName);
		if (user != null) {
			roles = Arrays.asList(new SimpleGrantedAuthority(user.getRoles()));
			// its show's that it is not define USER class but it's Spring defined user class we used
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),roles);
		}
		throw new UsernameNotFoundException("User not found with the name " + userName);	
	}
}

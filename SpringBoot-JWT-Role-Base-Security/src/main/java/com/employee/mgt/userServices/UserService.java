package com.employee.mgt.userServices;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.employee.mgt.entitys.User;
import com.employee.mgt.user.RegisterUser;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(RegisterUser registerUser);
}

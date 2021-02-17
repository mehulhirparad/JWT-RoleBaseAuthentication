package com.employee.mgt.userDAO;

import com.employee.mgt.entitys.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    
}

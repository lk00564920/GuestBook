package com.app.entry.service;

import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entry.model.Role;
import com.app.entry.model.User;
import com.app.entry.repository.RoleRepository;
import com.app.entry.repository.UserRepository;
/**
 * @author lk00564920
 * UserService
 * Helps to manage all business logics related to application user service
 * 
 */
@Service
public class UserService {
	
	private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User saveUser(User user, String role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole(role);
        logger.debug("Roles "+userRole);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public String getUserName() {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        String username = principal.substring(principal.indexOf("Username:"), principal.indexOf(";")).split(":")[1];
        logger.debug("username "+username);
        return username.trim();

    }
}
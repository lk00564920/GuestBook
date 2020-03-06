package com.app.entry.service;


import com.app.entry.model.Role;
import com.app.entry.model.User;
import com.app.entry.repository.RoleRepository;
import com.app.entry.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyUserDetailsServiceTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private MyUserDetailsService myUserDetailsService;
    @Mock
    private User user;
    @Spy
    private Set<Role> roles = new HashSet<>();

    @Test
    public void loadUserByUsername() {
        String userName = "user1";
        Role role = new Role();
        role.setRole("ADMIN");
        roles.add(role);

        when(userService.findUserByUserName(userName)).thenReturn(user);
        when(user.getUserName()).thenReturn("admin");
        when(user.getPassword()).thenReturn("admin");
        when(user.getRoles()).thenReturn(roles);

        assertNotNull(myUserDetailsService.loadUserByUsername(userName));

        verify(userService).findUserByUserName(userName);
        verify(user).getUserName();
        verify(user).getPassword();
        verify(user).getRoles();
    }

}

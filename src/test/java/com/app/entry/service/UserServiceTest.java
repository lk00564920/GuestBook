package com.app.entry.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

import com.app.entry.model.Entry;
import com.app.entry.repository.EntryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.entry.model.User;
import com.app.entry.repository.RoleRepository;
import com.app.entry.repository.UserRepository;
import com.app.entry.service.UserService;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private RoleRepository mockRoleRepository;
    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;
    @InjectMocks
    private UserService userServiceUnderTest;

    private User user;

    @Before
    public void setUp() {
        userServiceUnderTest = new UserService(mockUserRepository,
                                               mockRoleRepository,
                                               mockBCryptPasswordEncoder);
        user = new User();
        user.setId(1);
        user.setName("Gustavo");
        user.setLastName("Ponce");
        user.setEmail("test@test.com");
        when(mockUserRepository.save(any())).thenReturn(user);
        when(mockUserRepository.findByEmail(anyString())).thenReturn(user);
    }

    @Test
    public void testFindUserByEmail() {
       String email = "test@test.com";

       User result = userServiceUnderTest.findUserByEmail(email);

        assertEquals(email, result.getEmail());
    }

    @Test
    public void testSaveUser() {
        String email = "test@test.com";

        User result = userServiceUnderTest.saveUser(user, "ADMIN");

        assertEquals(email, result.getEmail());
    }
}

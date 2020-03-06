package com.app.entry.controller;

import com.app.entry.model.Entry;
import com.app.entry.service.EntryService;
import com.app.entry.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    @InjectMocks
    private LoginController loginController;
    @Mock
    private EntryService entryService;
    @Mock
    private UserService userService;
    @Mock
    private List<Entry> entries;

    @Test
    public void login()
    {
        ModelAndView modelAndView = loginController.login();
        assertNotNull(modelAndView);
        assertEquals(modelAndView.getViewName(), "login");
    }

    @Test
    public void adminHome() {
        when(userService.getUserName()).thenReturn("admin");
        when(entryService.findAll()).thenReturn(entries);

        ModelAndView modelAndView = loginController.adminHome();
        assertNotNull(modelAndView);
        assertEquals(modelAndView.getViewName(), "admin/home");
        assertEquals(modelAndView.getModel().get("userMsg"), "Welcome admin");
        assertEquals(modelAndView.getModel().get("entries"), entries);

        verify(userService).getUserName();
        verify(entryService).findAll();
    }

    @Test
    public void userHome() {
        when(userService.getUserName()).thenReturn("user1");
        when(entryService.findEntriesByUserName()).thenReturn(entries);

        ModelAndView modelAndView = loginController.userHome();
        assertNotNull(modelAndView);
        assertEquals(modelAndView.getViewName(), "guest/home");
        assertEquals(modelAndView.getModel().get("userMsg"), "Welcome user1");
        assertEquals(modelAndView.getModel().get("entries"), entries);

        verify(userService).getUserName();
        verify(entryService).findEntriesByUserName();
    }
}
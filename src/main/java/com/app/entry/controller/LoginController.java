package com.app.entry.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.entry.model.Entry;
import com.app.entry.model.User;
import com.app.entry.service.EntryService;
import com.app.entry.service.UserService;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private EntryService entryService;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value="/admin/home")
    public ModelAndView adminHome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userMsg", "Welcome " + userService.getUserName());
        modelAndView.addObject("entries", entryService.findAll());
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    

    @GetMapping(value="/guest/home")
    public ModelAndView userHome()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userMsg", "Welcome " + userService.getUserName());
        modelAndView.addObject("entry", new Entry());
        modelAndView.addObject("entries", entryService.findEntriesByUserName());
        modelAndView.setViewName("guest/home");
        return modelAndView;
    }
}

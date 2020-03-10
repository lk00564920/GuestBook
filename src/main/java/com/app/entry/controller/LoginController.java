package com.app.entry.controller;

import com.app.entry.EntryApplication;
import com.app.entry.model.Entry;
import com.app.entry.service.EntryService;
import com.app.entry.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * @author lk00564920
 * LoginController
 * Helps to map all login related uri routes
 * 
 */
@Controller
public class LoginController {
	
	private final static Logger logger = LoggerFactory.getLogger(EntryApplication.class);

    @Autowired
    private UserService userService;
    @Autowired
    private EntryService entryService;

    @GetMapping(value={"/", "/login"})
    public ModelAndView login()
    {
    	logger.debug("inside login method");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");        
        return modelAndView;
    }

    @GetMapping(value="/admin/home")
    public ModelAndView adminHome()
    {
    	logger.debug("inside adminHome method");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userMsg", "Welcome " + userService.getUserName());
        modelAndView.addObject("entries", entryService.findAll());
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    

    @GetMapping(value="/guest/home")
    public ModelAndView userHome()
    {
    	logger.debug("inside userHome method");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userMsg", "Welcome " + userService.getUserName());
        modelAndView.addObject("entry", new Entry());
        modelAndView.addObject("entries", entryService.findEntriesByUserName());
        modelAndView.setViewName("guest/home");
        return modelAndView;
    }
}

package com.app.entry.controller;

import com.app.entry.EntryApplication;
import com.app.entry.model.Entry;
import com.app.entry.service.EntryService;
import com.app.entry.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
/**
 * @author lk00564920
 * EntryController
 * Helps to map all entries related uri routes
 * 
 */
@Controller
public class EntryController {
	
	private final static Logger logger = LoggerFactory.getLogger(EntryApplication.class);

    @Autowired
    private EntryService entryService;
    @Autowired
    private UserService userService;

    @GetMapping(path = {"/admin/edit/{id}"})
    public ModelAndView editById(@Valid Entry entry, BindingResult bindingResult, @PathVariable("id") Optional<Long> id)
    {
    	logger.debug("inside editById method");
    	
        ModelAndView modelAndView = new ModelAndView();
        if (id.isPresent()) {
        	logger.info("Edit the entry for id "+id.get());
            modelAndView.addObject("entry", entryService.findById(id.get()));
        } else {
        	logger.debug("load admin edit");
            modelAndView.addObject("entry", new Entry());
        }
        modelAndView.setViewName("admin/edit");
        return modelAndView;
    }

    @GetMapping(path = "/admin/delete/{id}")
    public ModelAndView deleteById(@PathVariable("id") Long id)
    {
    	logger.debug("inside deleteById method");
    	
        ModelAndView modelAndView = new ModelAndView();
        entryService.deleteById(id);
        setAdminEntries(modelAndView);
        return modelAndView;
    }

    @GetMapping(path = "/admin/approve/{id}")
    public ModelAndView approveById(@PathVariable("id") Long id)
    {
    	logger.debug("inside approveById method");
    	
        ModelAndView modelAndView = new ModelAndView();
        entryService.approveById(id);
        setAdminEntries(modelAndView);
        return modelAndView;
    }

    @PostMapping(value="/guest/entry")
    public ModelAndView saveEntry(@Valid Entry entry, BindingResult bindingResult)
    {
    	logger.debug("inside saveEntry method");
    	
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
        	 logger.error("entry created by guest fails due to form error"+bindingResult.getAllErrors());
            setGuestEntries(modelAndView);
        } else {
            entryService.saveEntry(entry);
            logger.info("entry created by guest "+entry.getName());
            setGuestEntries(modelAndView);

        }
        return modelAndView;
    }

    @PostMapping(value="/admin/entry")
    public ModelAndView updateEntry(@Valid Entry entry, BindingResult bindingResult)
    {
    	logger.debug("inside updateEntry method");
    	
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
        	logger.error("entry created by admin fails due to form error"+bindingResult.getAllErrors());
            setAdminEntries(modelAndView);
        } else {
            entryService.saveEntry(entry);
            logger.info("entry created by admin "+entry.getName());
            setAdminEntries(modelAndView);
        }
        return modelAndView;
    }

    private String getUserMessage() {
        return "Welcome " + userService.getUserName();
    }

    private List<Entry> findAllEntries() {
        return entryService.findAll();
    }

    private List<Entry> findEntriesByUserName() {
        return entryService.findEntriesByUserName();
    }

    private void setAdminEntries(ModelAndView modelAndView) {
        modelAndView.addObject("userMsg", getUserMessage());
        modelAndView.addObject("entries", findAllEntries());
        modelAndView.setViewName("admin/home");
    }

    private void setGuestEntries(ModelAndView modelAndView) {
        modelAndView.addObject("userMsg", getUserMessage());
        modelAndView.addObject("entries", findEntriesByUserName());
        modelAndView.setViewName("guest/home");
    }

}

package com.app.entry.controller;

import com.app.entry.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.entry.service.EntryService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EntryController {

    @Autowired
    private EntryService entryService;

    @GetMapping(path = {"/admin/edit/{id}"})
    public ModelAndView editById(@Valid Entry entry, BindingResult bindingResult, @PathVariable("id") Optional<Long> id)
    {
        ModelAndView modelAndView = new ModelAndView();
        if (id.isPresent()) {
            modelAndView.addObject("entry", entryService.findById(id.get()));
        } else {
            modelAndView.addObject("entry", new Entry());
        }
        modelAndView.setViewName("admin/edit");
        return modelAndView;
    }

    @GetMapping(path = "/admin/delete/{id}")
    public ModelAndView deleteById(@PathVariable("id") Long id)
    {
        ModelAndView modelAndView = new ModelAndView();
        entryService.deleteById(id);
        modelAndView.addObject("entries", entryService.findAll());
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @GetMapping(path = "/admin/approve/{id}")
    public ModelAndView approveById(@PathVariable("id") Long id)
    {
        ModelAndView modelAndView = new ModelAndView();
        entryService.approveById(id);
        modelAndView.addObject("entries", entryService.findAll());
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

    @PostMapping(value="/guest/entry")
    public ModelAndView saveEntry(@Valid Entry entry, BindingResult bindingResult)
    {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("guest/home");
        } else {
            entryService.saveEntry(entry);
            modelAndView.addObject("entries", entryService.findEntriesByUserName());
            modelAndView.setViewName("guest/home");

        }
        return modelAndView;
    }

    @PostMapping(value="/admin/entry")
    public ModelAndView updateEntry(@Valid Entry entry, BindingResult bindingResult)
    {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("admin/home");
        } else {
            entryService.saveEntry(entry);
            modelAndView.addObject("entries", entryService.findAll());
            modelAndView.setViewName("admin/home");
        }
        return modelAndView;
    }

}

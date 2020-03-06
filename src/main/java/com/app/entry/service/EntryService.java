package com.app.entry.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.entry.model.Entry;
import com.app.entry.repository.EntryRepository;

@Service
public class EntryService {

    @Autowired
    private EntryRepository entryRepository;
    @Autowired
    private UserService userService;

    public List<Entry> findEntriesByUserName() {
    	List<Entry> entries = entryRepository.findByUserName(userService.getUserName());
        return entries;
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }
    
    public Entry findById(Long id) {
        return entryRepository.findById(id).get();
    }
    
    public void deleteById(Long id) {
        entryRepository.delete(findById(id));
    }
    
    public void approveById(Long id) {
    	Entry entry = findById(id);
    	entry.setStatus("Approved");
        entryRepository.save(entry);
    }

    public Entry saveEntry(Entry entry) {
    	entry.setUserName(userService.getUserName());
    	entry.setCreatedDate(new Date());
    	entry.setStatus("Pending");
        return entryRepository.save(entry);
    }
}
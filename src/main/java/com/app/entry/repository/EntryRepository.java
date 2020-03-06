package com.app.entry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entry.model.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByUserName(String userName);    
}
package com.app.entry.service;


import com.app.entry.model.Entry;
import com.app.entry.repository.EntryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
@RunWith(MockitoJUnitRunner.class)
public class EntryServiceTest {
    @InjectMocks
    private EntryService entryService;
    @Mock
    private EntryRepository entryRepository;
    @Mock
    private UserService userService;
    @Mock
    private List<Entry> entries;
    @Mock
    private Entry entry;

    @Test
    public void findEntriesByUserName() {
        String user = "user1";

        when(userService.getUserName()).thenReturn(user);
        when(entryRepository.findByUserName(user)).thenReturn(entries);

        assertNotNull(entryService.findEntriesByUserName());

        verify(userService).getUserName();
        verify(entryRepository).findByUserName(user);
    }

    @Test
    public void findAll() {
        when(entryRepository.findAll()).thenReturn(entries);

        assertNotNull(entryService.findAll());

        verify(entryRepository).findAll();
    }

    @Test
    public void findById() {
        Long id = 1L;
        Optional<Entry> entryObj = Optional.of(entry);
        when(entryRepository.findById(id)).thenReturn(entryObj);

        assertNotNull(entryService.findById(id));

        verify(entryRepository).findById(id);
    }

    @Test
    public void deleteById() {
        Long id = 1L;
        Optional<Entry> entryObj = Optional.of(entry);

        when(entryRepository.findById(id)).thenReturn(entryObj);
        doNothing().when(entryRepository).delete(entryObj.get());

        entryService.deleteById(id);

        verify(entryRepository).findById(id);
        verify(entryRepository).delete(entryObj.get());
    }

    @Test
    public void approveById() {
        Long id = 1L;
        Optional<Entry> entryObj = Optional.of(entry);
        when(entryRepository.findById(id)).thenReturn(entryObj);
        when(entryRepository.save(entryObj.get())).thenReturn(entry);

        entryService.approveById(id);

        verify(entryRepository).findById(id);
        verify(entryRepository).save(entryObj.get());
    }

    @Test
    public void saveEntry() {
        when(entryRepository.save(entry)).thenReturn(entry);

        entryService.saveEntry(entry);

        verify(entryRepository).save(entry);
    }
}
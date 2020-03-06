package com.app.entry.controller;

import com.app.entry.model.Entry;
import com.app.entry.service.EntryService;
import com.app.entry.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EntryControllerTest {
    @InjectMocks
    private EntryController entryController;
    @Mock
    private EntryService entryService;
    @Mock
    private UserService userService;
    @Mock
    private List<Entry> entries;
    @Mock
    private Entry entry;
    @Mock
    private BindingResult bindingResult;

    @Test
    public void editById()
    {
        Optional<Long> id = Optional.of(new Long(1));
        when(entryService.findById(id.get())).thenReturn(entry);

        ModelAndView modelAndView = entryController.editById(entry, bindingResult, id);
        assertNotNull(modelAndView);
        assertEquals(modelAndView.getModel().get("entry"), entry);
        assertEquals(modelAndView.getViewName(), "admin/edit");

        verify(entryService).findById(id.get());
    }

    @Test
    public void editByIdForGivenEntryIdIsNull()
    {
        Optional<Long> id = Optional.ofNullable(null);

        ModelAndView modelAndView = entryController.editById(entry, bindingResult, id);
        assertNotNull(modelAndView);
        assertTrue(modelAndView.getModel().get("entry") instanceof Entry);
        assertEquals(modelAndView.getViewName(), "admin/edit");
    }

    @Test
    public void deleteById() {
        when(entryService.findAll()).thenReturn(entries);

        ModelAndView modelAndView = entryController.deleteById(1L);
        assertNotNull(modelAndView);
        assertEquals(modelAndView.getViewName(), "admin/home");
        assertEquals(modelAndView.getModel().get("entries"), entries);

        verify(entryService).findAll();
    }

    @Test
    public void approveById() {
        when(entryService.findAll()).thenReturn(entries);

        ModelAndView modelAndView = entryController.approveById(1L);
        assertNotNull(modelAndView);
        assertEquals(modelAndView.getViewName(), "admin/home");
        assertEquals(modelAndView.getModel().get("entries"), entries);

        verify(entryService).findAll();
    }

    @Test
    public void saveEntry()
    {
        when(entryService.findEntriesByUserName()).thenReturn(entries);
        when(bindingResult.hasErrors()).thenReturn(false);

        ModelAndView modelAndView = entryController.saveEntry(entry, bindingResult);
        assertNotNull(modelAndView);
        assertEquals(modelAndView.getViewName(), "guest/home");
        assertEquals(modelAndView.getModel().get("entries"), entries);

        verify(entryService).findEntriesByUserName();
        verify(bindingResult).hasErrors();
    }

    @Test
    public void saveEntryForGivenFormHasErrors()
    {
        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = entryController.saveEntry(entry, bindingResult);
        assertNotNull(modelAndView);
        assertEquals(modelAndView.getViewName(), "guest/home");

        verify(bindingResult).hasErrors();
    }

    @Test
    public void updateEntry()
    {
        when(entryService.findAll()).thenReturn(entries);
        when(bindingResult.hasErrors()).thenReturn(false);

        ModelAndView modelAndView = entryController.updateEntry(entry, bindingResult);
        assertNotNull(modelAndView);
        assertEquals(modelAndView.getViewName(), "admin/home");
        assertEquals(modelAndView.getModel().get("entries"), entries);

        verify(entryService).findAll();
        verify(bindingResult).hasErrors();
    }

    @Test
    public void updateEntryForGivenFormHasErrors()
    {
        when(bindingResult.hasErrors()).thenReturn(true);

        ModelAndView modelAndView = entryController.updateEntry(entry, bindingResult);
        assertNotNull(modelAndView);
        assertEquals(modelAndView.getViewName(), "admin/home");

        verify(bindingResult).hasErrors();
    }
}
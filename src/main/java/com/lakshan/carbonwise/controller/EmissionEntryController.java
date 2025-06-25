package com.lakshan.carbonwise.controller;

import com.lakshan.carbonwise.entity.EmissionEntry;
import com.lakshan.carbonwise.service.EmissionEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/emission_entries")
public class EmissionEntryController {

    private final EmissionEntryService emissionEntryService;

    @Autowired
    public EmissionEntryController(EmissionEntryService emissionEntryService) {
        this.emissionEntryService = emissionEntryService;
    }

    @PostMapping
    public void addEmissionEntry(@RequestBody EmissionEntry emissionEntry) {
        emissionEntryService.addNewEmissionEntry(emissionEntry);
    }

    @GetMapping
    public List<EmissionEntry> getEmissionEntries() {
        return emissionEntryService.getAllEmissionEntries();
    }

    @GetMapping("{id}")
    public EmissionEntry getEmissionEntry(@PathVariable int id) {
        return emissionEntryService.getEmissionEntryById(id);
    }

    @GetMapping("/getUserById/{userId}")
    public List<EmissionEntry> getEmissionEntryByUserId(@PathVariable int userId) {
        return emissionEntryService.getEmissionEntryByUserId(userId);
    }

    @PutMapping
    public void updateEmissionEntry(@RequestBody EmissionEntry emissionEntry) {
        emissionEntryService.updateEmissionEntry(emissionEntry);
    }

    @DeleteMapping("{id}")
    public void deleteEmissionEntry(@PathVariable int id) {
        emissionEntryService.deleteEmissionEntry(id);
    }
}

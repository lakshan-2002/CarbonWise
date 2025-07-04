package com.lakshan.carbonwise.controller;

import com.lakshan.carbonwise.annotation.AuthRequired;
import com.lakshan.carbonwise.annotation.CurrentUser;
import com.lakshan.carbonwise.entity.EmissionEntry;
import com.lakshan.carbonwise.entity.User;
import com.lakshan.carbonwise.service.EmissionEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/addEmissionEntry")
    @AuthRequired
    public ResponseEntity<?> addEmissionEntry(@RequestBody EmissionEntry emissionEntry, @CurrentUser User user) {
        emissionEntry.setUser(user);
        emissionEntryService.addNewEmissionEntry(emissionEntry);
        return ResponseEntity.status(201).body("Successfully added");
    }

    @GetMapping("/getAllEmissionEntries")
    public List<EmissionEntry> getEmissionEntries() {
        return emissionEntryService.getAllEmissionEntries();
    }

    @AuthRequired
    @GetMapping("{id}")
    public ResponseEntity<?> getEmissionEntry(@PathVariable int id, @CurrentUser User user) {
        var emissionEntry = emissionEntryService.getEmissionEntryById(id);
        if (emissionEntry.getUser().getId() == user.getId()) {
            return ResponseEntity.ok(emissionEntry);
        }
        return ResponseEntity.status(401).body("Unauthorized Access");
    }

    @AuthRequired
    @GetMapping
    public List<EmissionEntry> getEmissionEntryByUserId(@CurrentUser User user) {
        return emissionEntryService.getEmissionEntryByUserId(user.getId());
    }

    @PutMapping("/updateEmissionEntry")
    public void updateEmissionEntry(@RequestBody EmissionEntry emissionEntry) {
        emissionEntryService.updateEmissionEntry(emissionEntry);
    }

    @DeleteMapping("{id}")
    public void deleteEmissionEntry(@PathVariable int id) {
        emissionEntryService.deleteEmissionEntry(id);
    }
}

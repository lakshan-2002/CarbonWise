package com.lakshan.carbonwise.service;

import com.lakshan.carbonwise.entity.EmissionEntry;
import com.lakshan.carbonwise.repository.EmissionEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionEntryService {

    private final EmissionEntryRepository emissionEntryRepository;

    @Autowired
    public EmissionEntryService(EmissionEntryRepository emissionEntryRepository) {
        this.emissionEntryRepository = emissionEntryRepository;
    }

    public void addNewEmissionEntry(EmissionEntry emissionEntry) {
        emissionEntryRepository.save(emissionEntry);
    }

    public List<EmissionEntry> getAllEmissionEntries() {
        return emissionEntryRepository.findAll();
    }

    public EmissionEntry getEmissionEntryById(int id) {
        return emissionEntryRepository.findById(id).orElseThrow(() -> new RuntimeException("No emission entry found with id: " + id));
    }

    public void updateEmissionEntry(EmissionEntry emissionEntry) throws RuntimeException {
        if(emissionEntryRepository.existsById(emissionEntry.getId()))
            emissionEntryRepository.save(emissionEntry);
        else
            throw new RuntimeException("No emission entry found with id: " + emissionEntry.getId());
    }

    public void deleteEmissionEntry(int id) {
        if(emissionEntryRepository.existsById(id))
            emissionEntryRepository.deleteById(id);
        else
            throw new RuntimeException("No emission entry found with id: " + id);
    }
}

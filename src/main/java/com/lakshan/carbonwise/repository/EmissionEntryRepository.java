package com.lakshan.carbonwise.repository;

import com.lakshan.carbonwise.entity.EmissionEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmissionEntryRepository extends JpaRepository<EmissionEntry, Integer> {
    List<EmissionEntry> findByUserId(int userId);
}

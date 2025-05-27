package com.lakshan.carbonwise.repository;

import com.lakshan.carbonwise.entity.EmissionFactor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissionFactorRepository extends JpaRepository<EmissionFactor, Integer> {

}

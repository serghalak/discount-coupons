package com.exadel.sandbox.repository.location_repository;

import com.exadel.sandbox.model.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}

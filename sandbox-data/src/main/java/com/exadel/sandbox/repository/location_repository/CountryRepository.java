package com.exadel.sandbox.repository.location_repository;

import com.exadel.sandbox.model.location.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByNameContainingIgnoreCase(String name);

}

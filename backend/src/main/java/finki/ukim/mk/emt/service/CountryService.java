package finki.ukim.mk.emt.service;

import finki.ukim.mk.emt.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findById(Long id);
    Optional<Country> save(String name, String continent);
    Optional<Country> edit(Long countryId, String name, String continent);

    void deleteById(Long id);



}

package finki.ukim.mk.emt.service.impl;

import finki.ukim.mk.emt.model.Country;
import finki.ukim.mk.emt.model.exceptions.CountryNotFoundException;
import finki.ukim.mk.emt.repository.JpaCountryRepository;
import finki.ukim.mk.emt.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final JpaCountryRepository countryRepository;

    public CountryServiceImpl(JpaCountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(String name, String continent) {
        Country country = new Country(name, continent);
        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public Optional<Country> edit(Long countryId, String name, String continent) {
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));
        country.setName(name);
        country.setContinent(continent);

        this.countryRepository.save(country);
        return Optional.of(country);
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }
}

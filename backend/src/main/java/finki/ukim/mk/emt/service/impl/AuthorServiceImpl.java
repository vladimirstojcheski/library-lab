package finki.ukim.mk.emt.service.impl;

import finki.ukim.mk.emt.model.Author;
import finki.ukim.mk.emt.model.Country;
import finki.ukim.mk.emt.model.exceptions.AuthorNotFoundException;
import finki.ukim.mk.emt.model.exceptions.CountryNotFoundException;
import finki.ukim.mk.emt.repository.JpaAuthorRepository;
import finki.ukim.mk.emt.repository.JpaCountryRepository;
import finki.ukim.mk.emt.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final JpaAuthorRepository authorRepository;
    private final JpaCountryRepository countryRepository;

    public AuthorServiceImpl(JpaAuthorRepository authorRepository, JpaCountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));
        Author author = new Author(name, surname, country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public Optional<Author> edit(Long authorId, String name, String surname, Long countryId) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        Country country = this.countryRepository.findById(countryId)
                .orElseThrow(() -> new CountryNotFoundException(countryId));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);

        this.authorRepository.save(author);

        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}

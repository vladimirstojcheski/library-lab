package finki.ukim.mk.emt.service;

import finki.ukim.mk.emt.model.Book;
import finki.ukim.mk.emt.model.Category;
import finki.ukim.mk.emt.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Category category, Long authorId, Integer copies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long bookId, String name, Category category, Long authorId, Integer copies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteById(Long id);

}

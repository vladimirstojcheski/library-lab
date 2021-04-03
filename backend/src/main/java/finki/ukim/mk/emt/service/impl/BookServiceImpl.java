package finki.ukim.mk.emt.service.impl;

import finki.ukim.mk.emt.model.Author;
import finki.ukim.mk.emt.model.Book;
import finki.ukim.mk.emt.model.Category;
import finki.ukim.mk.emt.model.dto.BookDto;
import finki.ukim.mk.emt.model.exceptions.AuthorNotFoundException;
import finki.ukim.mk.emt.model.exceptions.BookNotFoundException;
import finki.ukim.mk.emt.repository.JpaAuthorRepository;
import finki.ukim.mk.emt.repository.JpaBookRepository;
import finki.ukim.mk.emt.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final JpaBookRepository bookRepository;
    private final JpaAuthorRepository authorRepository;

    public BookServiceImpl(JpaBookRepository bookRepository, JpaAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer copies) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(()-> new AuthorNotFoundException(authorId));
        Book book = new Book(name, category, author, copies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long bookId, String name, Category category, Long authorId, Integer copies) {
        Book book = this.bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(copies);

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        this.bookRepository.save(book);

        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}

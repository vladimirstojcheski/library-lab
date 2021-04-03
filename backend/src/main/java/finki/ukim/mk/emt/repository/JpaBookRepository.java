package finki.ukim.mk.emt.repository;

import finki.ukim.mk.emt.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long> {
}

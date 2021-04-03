package finki.ukim.mk.emt.repository;

import finki.ukim.mk.emt.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAuthorRepository extends JpaRepository<Author, Long> {
}

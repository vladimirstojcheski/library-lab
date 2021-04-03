package finki.ukim.mk.emt.repository;

import finki.ukim.mk.emt.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCountryRepository extends JpaRepository<Country, Long> {
}

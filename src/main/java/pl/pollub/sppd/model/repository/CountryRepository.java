package pl.pollub.sppd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.address.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}

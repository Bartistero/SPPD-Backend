package pl.pollub.sppd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.address.City;

public interface CityRepository extends JpaRepository<City, Long> {
}

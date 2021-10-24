package pl.pollub.sppd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.address.County;

import java.util.Optional;

public interface CountyRepository extends JpaRepository<County, Long> {
    Optional<County> findCountyById(Long id);
}

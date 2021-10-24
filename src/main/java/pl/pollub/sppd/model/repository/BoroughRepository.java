package pl.pollub.sppd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.address.Borough;

import java.util.Optional;

public interface BoroughRepository extends JpaRepository<Borough, Long> {

    Optional<Borough> findBoroughById(Long id);
}

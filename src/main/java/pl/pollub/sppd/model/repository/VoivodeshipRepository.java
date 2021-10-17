package pl.pollub.sppd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.address.Voivodeship;

import java.util.Optional;

public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Long> {
    Optional<Voivodeship> findVoivodeshipById(Long id);
}

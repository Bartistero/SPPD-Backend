package pl.pollub.sppd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.year.Year;


public interface YearRepository extends JpaRepository<Year, Long> {
}

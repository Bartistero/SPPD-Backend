package pl.pollub.sppd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;
import pl.pollub.sppd.model.address.Street;

public interface ThesisTitleRepository extends JpaRepository<ThesisTitle, Long> {
}

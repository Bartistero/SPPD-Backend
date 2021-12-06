package pl.pollub.sppd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;
import pl.pollub.sppd.model.address.Street;
import pl.pollub.sppd.model.thesisStatus.ThesisStatus;

public interface ThesisTitleRepository extends JpaRepository<ThesisTitle, Long> {

    Long countByThesisStatus(ThesisStatus thesisStatus);
}

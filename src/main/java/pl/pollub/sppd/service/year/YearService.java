package pl.pollub.sppd.service.year;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.repository.YearRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class YearService {

    private final YearRepository yearRepository;
    public List<YearDto> get() {
        return yearRepository.findAll().stream()
                .map(YearDto::yearToYearDto)
                .collect(Collectors.toList());
    }
}

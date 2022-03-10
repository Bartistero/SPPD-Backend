package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.sppd.service.year.YearDto;
import pl.pollub.sppd.service.year.YearService;

import java.util.List;

@RestController
@RequestMapping("/year")
@RequiredArgsConstructor
public class YearController {

    private final YearService yearService;
    @GetMapping
    public List<YearDto> get(){
        return yearService.get();
    }
}

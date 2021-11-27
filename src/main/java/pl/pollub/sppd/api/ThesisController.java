package pl.pollub.sppd.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.sppd.service.Thesis.ThesisDto;
import pl.pollub.sppd.service.Thesis.ThesisSaveDto;
import pl.pollub.sppd.service.Thesis.ThesisService;

import java.util.List;

@RestController
@RequestMapping("/thesis")
@RequiredArgsConstructor
public class ThesisController {

    private final ThesisService thesisService;


    //Zwraca wszystkie dostępne prace na danym wypdziale!
    @GetMapping()
    public List<ThesisDto> getAllThesis() {
        return thesisService.getAllThesis(getLogin());
    }

    @GetMapping("/my-thesis")
    public List<ThesisDto> getMyThesis() {
        return thesisService.getMyThesis(getLogin());
    }

    @PostMapping("/put")
    public ThesisSaveDto newThesis(ThesisSaveDto thesisSaveDto){

        return thesisSaveDto;
    }

    private String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
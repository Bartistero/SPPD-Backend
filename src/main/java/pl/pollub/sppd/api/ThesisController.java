package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.pollub.sppd.model.thesisStatus.ThesisStatus;
import pl.pollub.sppd.service.Thesis.ThesisDetailsDto;
import pl.pollub.sppd.service.Thesis.ThesisDto;
import pl.pollub.sppd.service.Thesis.ThesisSaveDto;
import pl.pollub.sppd.service.Thesis.ThesisService;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.exceptions.PermissionException;
import pl.pollub.sppd.service.user.LecturerDto;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/thesis")
@RequiredArgsConstructor
public class ThesisController {

    private final ThesisService thesisService;


    //Zwraca wszystkie dostępne prace na danym wypdziale!
    @GetMapping()
    public List<ThesisDto> getAllThesis(@RequestParam ThesisStatus thesisStatus) {
        return thesisService.getAllThesis(getLogin(), thesisStatus);
    }

    @GetMapping("/my-thesis")
    public List<ThesisDetailsDto> getMyThesis() {
        return thesisService.getMyThesis(getLogin());
    }

    @PostMapping()
    public ThesisSaveDto newThesis(@RequestBody ThesisSaveDto thesisSaveDto) throws PermissionException, GeneralException {
        thesisService.put(thesisSaveDto, getLogin());
        return thesisSaveDto;
    }

    @PostMapping("/update")
    public ThesisDto updateThesis(@RequestBody ThesisDto thesisDto) throws PermissionException, NotFoundException, GeneralException, MessagingException {
        thesisService.update(thesisDto, getLogin());
        return thesisDto;
    }

    @PostMapping("/new-collaborators")
    public LecturerDto addUser(@RequestBody LecturerDto lecturerDto, @RequestParam Long idThesis) throws NotFoundException, GeneralException {
       return thesisService.addNewCollaborators(lecturerDto, idThesis);
    }

    private String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

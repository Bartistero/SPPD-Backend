package pl.pollub.sppd.service.Thesis;

import com.fasterxml.jackson.databind.cfg.CoercionAction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;
import pl.pollub.sppd.model.faculty.Faculty;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.model.repository.ThesisTitleRepository;
import pl.pollub.sppd.model.thesisStatus.ThesisStatus;
import pl.pollub.sppd.service.CheckThesis;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.exceptions.PermissionException;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ThesisService {

    private final PersonRepository personRepository;
    private final CheckThesis checkThesis;
    private final ThesisTitleRepository thesisRepository;

    public List<ThesisDto> getAllThesis(String login) {
        Person person = personRepository.findPersonByLogin(login);
        Faculty faculty = person.getFaculty();
        return faculty.getThesisTitle().stream()
                .map(ThesisDto::thesisTitleToThesisDto)
                .collect(Collectors.toList());
    }


    public List<ThesisDetailsDto> getMyThesis(String login) {
        Person person = personRepository.findPersonByLogin(login);
        return person.getThesis().stream()
                .map(ThesisDetailsDto::thesisTitleToThesisDetailsDto)
                .collect(Collectors.toList());
    }

    public void put(ThesisSaveDto thesisSaveDto, String login) throws PermissionException, GeneralException {
        Person person = personRepository.findPersonByLogin(login);
        if (!checkThesis.checkStatusThesis(thesisSaveDto.getThesisStatus(), person.getPermission())) {
            throw new PermissionException("User can not make this action!");
        }
        checkThesis.validateNewThesis(thesisSaveDto);
        ThesisTitle thesisTitle = ThesisSaveDto.thesisSaveDtoToThesisTitle(thesisSaveDto);
        thesisTitle.setFaculty(person.getFaculty());
        Set<Person> personList = new HashSet<>();
        personList.add(person);
        thesisTitle.setListOfPersonThesis(personList);
        thesisRepository.save(thesisTitle);
    }

    public void update(ThesisDto thesisDto, String login) throws PermissionException, NotFoundException, GeneralException {
        Person person = personRepository.findPersonByLogin(login);
        Optional<ThesisTitle> thesisTitleOptional = thesisRepository.findById(thesisDto.getId());
        if(thesisTitleOptional.isEmpty())
            throw new NotFoundException("Thesis with id: " + thesisDto.getId() + " not found!");
        ThesisTitle thesisTitle = thesisTitleOptional.get();
        checkThesis.checkUpdateThesis(thesisDto,person,thesisTitle);
        thesisTitle.getListOfPersonThesis().add(person);
        thesisTitle.setThesisStatus(thesisDto.getThesisStatus());
        thesisRepository.save(thesisTitle);
    }
}

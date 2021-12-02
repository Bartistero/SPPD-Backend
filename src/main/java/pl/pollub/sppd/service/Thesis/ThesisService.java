package pl.pollub.sppd.service.Thesis;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.mail.Mail;
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
import pl.pollub.sppd.service.user.LecturerDto;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ThesisService {

    private final PersonRepository personRepository;
    private final CheckThesis checkThesis;
    private final ThesisTitleRepository thesisRepository;
    private final Mail mail;

    public List<ThesisDto> getAllThesis(String login, ThesisStatus thesisStatus) {
        Person person = personRepository.findPersonByLogin(login);
        Faculty faculty = person.getFaculty();
        if (thesisStatus.equals(ThesisStatus.ACCEPTED_FACULTY)) {
            return faculty.getThesisTitle().stream()
                    .filter(f -> f.getThesisStatus().equals(ThesisStatus.ACCEPTED_FACULTY))
                    .map(p -> {
                        Long quantity = p.getListOfPersonThesis().stream()
                                .filter(f -> f.getPermission().equals(Permission.STUDENT))
                                .count();
                        return ThesisDto.thesisTitleToThesisDto(p, quantity);
                    })
                    .collect(Collectors.toList());
        } else {
            return faculty.getThesisTitle().stream()
                    .filter(f -> f.getThesisStatus().equals(ThesisStatus.ADDED_LECTURER)
                           || f.getThesisStatus().equals(ThesisStatus.ACCEPTED_LECTURER))
                    .filter(f -> f.getListOfPersonThesis()
                            .stream()
                            .noneMatch(even -> even.getLogin().equals(person.getLogin())))
                    .map(p -> {
                        Long quantity = p.getListOfPersonThesis().stream()
                                .filter(f -> f.getPermission().equals(Permission.STUDENT))
                                .count();
                        return ThesisDto.thesisTitleToThesisDto(p, quantity);
                    })
                    .collect(Collectors.toList());
        }
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
        if (person.getPermission().equals(Permission.STUDENT)) {
            personList.add(personRepository.findById(thesisSaveDto.getLecturer().getId())
                    .orElseThrow(() -> new GeneralException("Lecturer not found!")));
        }
        thesisTitle.setListOfPersonThesis(personList);
        thesisRepository.save(thesisTitle);
    }

    @Transactional
    public void update(ThesisDto thesisDto, String login) throws NotFoundException, GeneralException, MessagingException {
        Person person = personRepository.findPersonByLogin(login);
        Optional<ThesisTitle> thesisTitleOptional = thesisRepository.findById(thesisDto.getId());
        if (thesisTitleOptional.isEmpty())
            throw new NotFoundException("Thesis with id: " + thesisDto.getId() + " not found!");
        ThesisTitle thesisTitle = thesisTitleOptional.get();
        if ((person.getPermission().equals(Permission.LECTURER) || person.getPermission().equals(Permission.ADMIN))
                && thesisDto.getThesisStatus().equals(ThesisStatus.REJECTED)) {
            for (Iterator<Person> it = thesisTitle.getListOfPersonThesis().iterator();
                 it.hasNext(); ) {
                Person f = it.next();
                if (f.getPermission().equals(Permission.STUDENT) || f.getPermission().equals(Permission.LECTURER) )
                    mail.sendRejectMail(f.getEmail(), f.getLogin());
            }
            thesisRepository.delete(thesisTitle);
            return;
        }
        checkThesis.checkUpdateThesis(thesisDto, person, thesisTitle);
        thesisTitle.getListOfPersonThesis().add(person);
        thesisTitle.setThesisStatus(thesisDto.getThesisStatus());
        thesisRepository.save(thesisTitle);
    }

    public LecturerDto addNewCollaborators(LecturerDto lecturerDto, Long idThesis) throws NotFoundException, GeneralException {
        ThesisTitle thesisTitle = thesisRepository.findById(idThesis).orElseThrow(
                () -> new NotFoundException("Theis with id: " + idThesis + "not found!")
        );
        for (Person p : thesisTitle.getListOfPersonThesis()
        ) {
            if (p.getId().equals(lecturerDto.getId())) {
                throw new GeneralException("the user has been taken down to this thesis");
            }
        }
        Person person = personRepository.findById(lecturerDto.getId()).orElseThrow(
                () -> new NotFoundException("Theis with id: " + idThesis + "not found!")
        );
        thesisTitle.getListOfPersonThesis().add(person);
        thesisRepository.save(thesisTitle);
        return lecturerDto;
    }
}

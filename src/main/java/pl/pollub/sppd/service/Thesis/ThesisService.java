package pl.pollub.sppd.service.Thesis;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.faculty.Faculty;
import pl.pollub.sppd.model.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ThesisService {

    PersonRepository personRepository;

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

    public void put(ThesisSaveDto thesisSaveDto, String login) {
        Person person = personRepository.findPersonByLogin(login);
        switch (person.getPermission()) {
            case STUDENT:
                student(thesisSaveDto);
                break;
            case LECTURER:
                lecturer(thesisSaveDto);
        }
    }

    private void student(ThesisSaveDto thesisSaveDto) {

    }

    private void lecturer(ThesisSaveDto thesisSaveDto) {

    }
}

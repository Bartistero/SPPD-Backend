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
        System.out.println(faculty.getThesisTitle().size());
        return faculty.getThesisTitle().stream()
                .map(ThesisDto::thesisStatusToThesisDto)
                .collect(Collectors.toList());
    }


}

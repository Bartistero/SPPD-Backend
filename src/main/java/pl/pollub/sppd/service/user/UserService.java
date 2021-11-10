package pl.pollub.sppd.service.user;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserService {

    private final PersonRepository personRepository;

    public List<UserDto> get(Integer page, Integer size) {
        int pageNumber = page != null && page >= 0 ? page : 0;
        int pageSize = size != null && size > 0 ? size : 1;
        List<Person> personList = personRepository.findPersonByPermission(Permission.LECTURER, PageRequest.of(pageNumber, pageSize));
        personList = Stream.concat(personList.stream(), personRepository.findPersonByPermission
                (Permission.STUDENT, PageRequest.of(pageNumber, pageSize)).stream())
                .collect(Collectors.toList());
        return personList.stream()
                .map(UserDto::personToUserDto)
                .collect(Collectors.toList());
    }
}


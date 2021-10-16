package pl.pollub.sppd.service;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.repository.PersonRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final PersonRepository personRepository;

    public void add(AdminDto adminDto) throws NotFoundException {
        Person person = personRepository.findPersonByLogin(adminDto.getLogin());
        if(person != null) throw new NotFoundException("taki użytkownik już istniej");
    }
}

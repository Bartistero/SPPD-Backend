package pl.pollub.sppd.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.pollub.sppd.model.Person;

import pl.pollub.sppd.model.repository.PersonRepository;

import javax.management.ConstructorParameters;
import javax.transaction.Transactional;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Person person = personRepository.findPersonByLogin(username);

        return null;// new User(person.getLogin(), person.getPassword());
    }
}

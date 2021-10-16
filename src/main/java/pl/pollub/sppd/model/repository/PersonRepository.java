package pl.pollub.sppd.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findPersonByLogin(String login);
}

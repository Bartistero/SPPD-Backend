package pl.pollub.sppd.model.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.admin.AdminDto;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findPersonByLogin(String login);

    Person findPersonByEmail(String Email);

    Person findPersonByPesel(String pesel);

    List<Person> findPersonByPermission(Permission permission, Pageable page);

    Optional<Person> findUserByActivateToken(String token);
}

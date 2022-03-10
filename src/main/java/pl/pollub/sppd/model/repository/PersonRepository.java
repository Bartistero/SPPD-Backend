package pl.pollub.sppd.model.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.permission.Permission;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {

    Person findPersonByLogin(String login);

    Person findPersonByEmail(String Email);

    Person findPersonByPesel(String pesel);

    List<Person> findPersonByPermission(Permission permission);

    Optional<Person> findUserByActivateToken(String token);

    @Query("select o from Person  o where o.email=:email and o.login<>:login")
    List<Person> checkEmail(String email, String login);

    @Query("select o from Person o where o.pesel=:pesel and o.login <>:login")
    List<Person> checkPesel(String pesel, String login);

    @Query("Select o from Person o where o.permission=:permission and o.faculty.id=:facultyId")
    List<Person> findPersonByPermissionAndFaculty(Permission permission, Long facultyId);

    @Query("select o from Person o where o.accountStatus=:accountStatus")
    List<Person> findBlockPerson(AccountStatus accountStatus);

    Long countByPermission(Permission permission);
}

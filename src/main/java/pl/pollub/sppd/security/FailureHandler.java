package pl.pollub.sppd.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.repository.PersonRepository;

@Component
@RequiredArgsConstructor
public class FailureHandler extends SimpleUrlAuthenticationFailureHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private final PersonRepository personRepository;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        Object login = event.getAuthentication().getPrincipal();
        Person person = personRepository.findPersonByLogin(login.toString());
        Integer count = person.getLoginAttempts();
        System.out.println(count);
        count++;
        person.setLoginAttempts(count);
        if(count >= 3){
            person.setAccountStatus(AccountStatus.SUSPENDED);
            count = 0;
            person.setLoginAttempts(count);
        }
        personRepository.save(person);
    }
}

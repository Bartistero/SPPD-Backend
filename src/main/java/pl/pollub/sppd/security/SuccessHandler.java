package pl.pollub.sppd.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.repository.PersonRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final long expirationTime;
    private final String secret;
    private final PersonRepository personRepository;

    public SuccessHandler(
            @Value("${jwt.expirationTime}") long expirationTime,
            @Value("${jwt.secret}") String secret, PersonRepository personRepository) {
        this.expirationTime = expirationTime;
        this.secret = secret;
        this.personRepository = personRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withClaim("permission", principal.getAuthorities().iterator().next().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(secret));
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Access-Control-Expose-Headers", "*");
        Person person = personRepository.findPersonByLogin(principal.getUsername());
        if(person.getLoginAttempts() >0){
            person.setLoginAttempts(0);
            personRepository.save(person);
        }
    }
}

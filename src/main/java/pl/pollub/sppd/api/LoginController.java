package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pollub.sppd.security.LoginCredentials;
import pl.pollub.sppd.service.exceptions.AlreadyExistsException;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.LoginService;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {
    }

    @GetMapping("/login/check/{login}")
    public void login(@PathVariable String login) throws AlreadyExistsException {
        loginService.checkAvailableLogin(login);
    }
}

package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.security.LoginCredentials;
import pl.pollub.sppd.service.CheckPermission;
import pl.pollub.sppd.service.exceptions.AlreadyExistsException;
import pl.pollub.sppd.service.LoginService;
import pl.pollub.sppd.service.exceptions.PermissionException;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {
    }

    @GetMapping("/login/check/{login}")
    public void login(@PathVariable String login) throws AlreadyExistsException, PermissionException {
        checkPermission();
        loginService.checkAvailableLogin(login);
    }

    private void checkPermission() throws PermissionException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        CheckPermission.checkPermission(Permission.SUPER_ADMIN, Permission.valueOf(authorities.iterator().next().toString()));
    }
}

package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.security.BlockUserDto;
import pl.pollub.sppd.security.LoginCredentials;
import pl.pollub.sppd.service.CheckPermission;
import pl.pollub.sppd.service.exceptions.AlreadyExistsException;
import pl.pollub.sppd.service.LoginService;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.exceptions.PermissionException;
import pl.pollub.sppd.service.user.UserSaveDto;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public void login(@RequestBody LoginCredentials credentials) {
    }

    @GetMapping("/check/{login}")
    public void login(@PathVariable String login) throws AlreadyExistsException, PermissionException {
        checkPermission();
        loginService.checkAvailableLogin(login);
    }

    @PostMapping("/activate/{token}")
    public void activate(@PathVariable String token, @RequestBody LoginCredentials loginCredentials) throws NotFoundException {
        loginService.activate(token, loginCredentials);
    }

    @GetMapping("/block-user")
    public List<BlockUserDto> blockUser() throws PermissionException {
        checkPermission();
        return loginService.getBlockUser();
    }

    private void checkPermission() throws PermissionException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        CheckPermission.checkPermission(Permission.SUPER_ADMIN, Permission.valueOf(authorities.iterator().next().toString()));
    }
}

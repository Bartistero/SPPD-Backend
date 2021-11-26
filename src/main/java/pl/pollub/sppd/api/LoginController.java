package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.pollub.sppd.model.accountStatus.AccountStatus;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.security.BlockUserDto;
import pl.pollub.sppd.security.LoginCredentials;
import pl.pollub.sppd.service.CheckPermission;
import pl.pollub.sppd.service.exceptions.AlreadyExistsException;
import pl.pollub.sppd.service.LoginService;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.exceptions.PermissionException;

import javax.mail.MessagingException;
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
    public void login(@PathVariable String login) throws AlreadyExistsException{
        //checkPermission();
        loginService.checkAvailableLogin(login);
    }

    @PostMapping("/activate/{token}")
    public void activate(@PathVariable String token, @RequestBody LoginCredentials loginCredentials) throws NotFoundException {
        loginService.activate(token, loginCredentials);
    }

    @GetMapping("/block-user")
    public List<BlockUserDto> blockUser() throws PermissionException {
        checkPermission(Permission.ADMIN);
        return loginService.getBlockUser();
    }

    @PostMapping("/block-user")
    public BlockUserDto blockUserUpdate(@RequestBody BlockUserDto userDto) throws PermissionException, MessagingException {
        checkPermission(Permission.ADMIN);
        return loginService.updateBlockUser(userDto);
    }

    @GetMapping("/account-status")
    public AccountStatus accountStatus(String login) throws PermissionException {
        return loginService.getAccountStatus(login);
    }

    private void checkPermission(Permission permission) throws PermissionException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        CheckPermission.checkPermission(permission, Permission.valueOf(authorities.iterator().next().toString()));
    }
}

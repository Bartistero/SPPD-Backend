package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.CheckPermission;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.exceptions.PermissionException;
import pl.pollub.sppd.service.user.UserDto;
import pl.pollub.sppd.service.user.UserSaveDto;
import pl.pollub.sppd.service.user.UserService;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> get(@RequestParam(required = true) Integer page,
                             @RequestParam(required = true) Integer pageSize) throws PermissionException {
        checkPermission();
        return userService.get(page, pageSize);
    }

    @PostMapping
    public UserSaveDto add(@RequestBody UserSaveDto userSaveDto) throws PermissionException, GeneralException, NotFoundException, MessagingException {
        checkPermission();
        return userService.add(userSaveDto);
    }

    @PostMapping("/put")
    public UserDto update(@RequestBody UserDto userDto) throws PermissionException, NotFoundException, GeneralException {
        checkPermission();
        return userService.update(userDto);
    }

    private void checkPermission() throws PermissionException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        CheckPermission.checkPermission(Permission.ADMIN, Permission.valueOf(authorities.iterator().next().toString()));
    }
}

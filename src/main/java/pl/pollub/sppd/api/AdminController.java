package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.admin.AdminDto;
import pl.pollub.sppd.service.admin.AdminService;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.PermissionException;

import javax.mail.MessagingException;
import java.util.Collection;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public AdminDto add(AdminDto adminDto) throws GeneralException, PermissionException, MessagingException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return adminService.add(adminDto, Permission.valueOf(authorities.iterator().next().toString()));
    }
}

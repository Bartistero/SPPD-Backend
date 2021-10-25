package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.CheckPermission;
import pl.pollub.sppd.service.admin.AdminDto;
import pl.pollub.sppd.service.admin.AdminSaveDto;
import pl.pollub.sppd.service.admin.AdminService;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.exceptions.PermissionException;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public List<AdminDto> get(@RequestParam(required = true) Integer page,
                              @RequestParam(required = true) Integer pageSize) throws PermissionException {
        checkPermission();
        return adminService.get(page, pageSize);
    }

    @PostMapping
    public AdminSaveDto add(@RequestBody AdminSaveDto adminSaveDto) throws GeneralException, PermissionException, MessagingException, NotFoundException {
        checkPermission();
        return adminService.add(adminSaveDto);
    }

    @PutMapping
    public AdminDto update(@RequestBody AdminDto adminDto) throws PermissionException, NotFoundException, GeneralException {
        checkPermission();
        return adminService.update(adminDto);
    }

    @DeleteMapping
    public AdminDto delete(@RequestBody AdminDto adminDto) throws NotFoundException {
       return adminService.delete(adminDto);
    }


    private void checkPermission() throws PermissionException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        CheckPermission.checkPermission(Permission.SUPER_ADMIN, Permission.valueOf(authorities.iterator().next().toString()));
    }
}

package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.thesisStatus.ThesisStatus;
import pl.pollub.sppd.service.CheckPermission;
import pl.pollub.sppd.service.StatisticService;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.PermissionException;

import java.util.Collection;

@RestController
@RequestMapping("/static")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/person")
    public Long getCountPerson(Permission permission) throws PermissionException, GeneralException {
        checkPermission();
        return statisticService.getCountPerson(permission);
    }

    @GetMapping("/thesis")
    public Long getCountThesis(ThesisStatus status) throws PermissionException {
        checkPermission();
        return statisticService.getCountThesis(status);
    }

    private void checkPermission() throws PermissionException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        CheckPermission.checkPermission(Permission.SUPER_ADMIN, Permission.valueOf(authorities.iterator().next().toString()));
    }
}

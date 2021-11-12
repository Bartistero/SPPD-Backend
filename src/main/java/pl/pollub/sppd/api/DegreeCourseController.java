package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.CheckPermission;
import pl.pollub.sppd.service.degreeCourse.DegreeCourseDto;
import pl.pollub.sppd.service.degreeCourse.DegreeCourseService;
import pl.pollub.sppd.service.exceptions.PermissionException;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/degree-course")
@RequiredArgsConstructor
public class DegreeCourseController {

    private final DegreeCourseService degreeCourseService;

    @GetMapping
    public List<DegreeCourseDto> get() throws PermissionException {
        checkPermission();
        return degreeCourseService.get(getLogin());
    }

    private void checkPermission() throws PermissionException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        CheckPermission.checkPermission(Permission.ADMIN, Permission.valueOf(authorities.iterator().next().toString()));
    }

    private String getLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

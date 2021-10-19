package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.sppd.service.admin.AdminDto;
import pl.pollub.sppd.service.admin.AdminService;
import pl.pollub.sppd.service.GeneralException;

import java.util.Collection;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminApi {

    private final AdminService adminService;

    @PostMapping
    public AdminDto add(AdminDto adminDto) throws GeneralException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return adminService.add(adminDto, authorities.iterator().next().toString());
    }

    //API Do istnienia loginu użytkownika
}

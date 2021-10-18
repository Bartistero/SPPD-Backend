package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.sppd.service.address.AddressNotFoundException;
import pl.pollub.sppd.service.admin.AdminDto;
import pl.pollub.sppd.service.admin.AdminService;

@RestController
@RequestMapping("/cos")
@RequiredArgsConstructor
public class AdminApi {

    private final AdminService adminService;

    @PostMapping
    public AdminDto add(AdminDto adminDto) throws AddressNotFoundException {
        String authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return adminService.add(adminDto, authorities);
    }

    //API Do istnienia loginu użytkownika
}

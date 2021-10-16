package pl.pollub.sppd.api;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.sppd.service.AdminDto;
import pl.pollub.sppd.service.AdminService;

@RestController
@RequestMapping("/cos")
@RequiredArgsConstructor
public class AdminApi {

    private final AdminService adminService;

    @PostMapping
    public AdminDto add(AdminDto adminDto) throws NotFoundException {
        adminService.add(adminDto);
        return adminDto;
    }

    
}

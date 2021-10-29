package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.CheckPermission;
import pl.pollub.sppd.service.exceptions.AlreadyExistsException;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.NotFoundException;
import pl.pollub.sppd.service.exceptions.PermissionException;
import pl.pollub.sppd.service.faculty.FacultyDto;
import pl.pollub.sppd.service.faculty.FacultySaveDto;
import pl.pollub.sppd.service.faculty.FacultyService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping
    public List<FacultyDto> get() throws PermissionException {
        checkPermission();
        return facultyService.get();
    }

    @PostMapping
    public FacultySaveDto post(@RequestBody FacultySaveDto facultySaveDtoDto) throws AlreadyExistsException, PermissionException {
        checkPermission();
        return facultyService.post(facultySaveDtoDto);
    }

    @PutMapping
    public FacultyDto update(@RequestBody FacultyDto facultyDto) throws AlreadyExistsException, NotFoundException, GeneralException, PermissionException {
        checkPermission();
        return facultyService.update(facultyDto);
    }


    @DeleteMapping
    public void delete(@RequestParam Long id) throws NotFoundException, GeneralException, PermissionException {
        checkPermission();
        facultyService.delete(id);
    }

    private void checkPermission() throws PermissionException {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        CheckPermission.checkPermission(Permission.SUPER_ADMIN, Permission.valueOf(authorities.iterator().next().toString()));
    }
}

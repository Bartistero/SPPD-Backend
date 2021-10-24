package pl.pollub.sppd.service;

import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.exceptions.PermissionException;

import java.util.List;

@Component
public class CheckPermission {

    public void checkPermission(List<Permission> RequirePermission, List<Permission> permissions)
            throws PermissionException {
        permissions.stream()
                .filter(RequirePermission::equals)
                .findFirst()
                .orElseThrow(() -> new PermissionException("User haven't got enough permissions"));
    }

    public void checkPermission(Permission RequirePermission, Permission permissions)
            throws PermissionException {
        if (!permissions.equals(RequirePermission))
            throw new PermissionException("User haven't got enough permissions");
    }
}

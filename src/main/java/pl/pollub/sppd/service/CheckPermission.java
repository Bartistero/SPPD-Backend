package pl.pollub.sppd.service;

import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.exceptions.PermissionException;

@Component
public class CheckPermission {

    public static void checkPermission(Permission RequirePermission, Permission permissions)
            throws PermissionException {
        if (!permissions.equals(RequirePermission))
            throw new PermissionException("User haven't got enough permissions");
    }
}

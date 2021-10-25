package pl.pollub.sppd.service;

import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.exceptions.GeneralException;
import pl.pollub.sppd.service.exceptions.NotFoundException;

import java.util.Arrays;

public class PermissionVerification {

    public static void checkPermission(Permission permission) throws GeneralException, NotFoundException {
        if(permission == null)
            throw new GeneralException("Field permission can not be null!");
        Arrays.stream(Permission.values())
                .filter(p -> p == permission)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Permision not found!"));
    }
}

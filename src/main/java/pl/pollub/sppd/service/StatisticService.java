package pl.pollub.sppd.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.hql.internal.ast.ParseErrorHandler;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.repository.PersonRepository;
import pl.pollub.sppd.model.repository.ThesisTitleRepository;
import pl.pollub.sppd.model.thesisStatus.ThesisStatus;
import pl.pollub.sppd.service.exceptions.GeneralException;

@Component
@RequiredArgsConstructor
public class StatisticService {

    private final PersonRepository personRepository;
    private final ThesisTitleRepository thesisRepository;

    public Long getCountPerson(Permission permission) throws GeneralException {
        if(permission.equals(Permission.LECTURER) || permission.equals(Permission.STUDENT)){
            return  personRepository.countByPermission(permission);
        }else {
            throw new GeneralException("You can use only LECTURER or STUDENT permissions!");
        }
    }

    public Long getCountThesis(ThesisStatus status) {

        return thesisRepository.countByThesisStatus(status);
    }
}

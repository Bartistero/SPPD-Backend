package pl.pollub.sppd.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.repository.*;
import pl.pollub.sppd.service.address.AddressDto;
import pl.pollub.sppd.service.address.AddressNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final PersonRepository personRepository;
    private final VoivodeshipRepository voivodeshipRepository;
    private final CountyRepository countyRepository;
    private final BoroughRepository boroughRepository;
    private final CityRepository cityRepository;
    private final StreetRepository streetRepository;

    public AdminDto add(AdminDto adminDto, String authorities) throws AddressNotFoundException {
        checkAvailabilityAddress(adminDto.getVoivodeshipDto());
        return null;
    }

    private void checkAvailabilityAddress(AddressDto ... address) {
        List<String> errors = new ArrayList<>();
        if (voivodeshipRepository.findVoivodeshipById(adminDto.getVoivodeshipDto().getId()).isEmpty()) {
            errors.add("voivodeship with id=" + adminDto.getVoivodeshipDto().getId() + " not found");
        }else if (countyRepository.findCountyById(adminDto.getCountyDto().getId()).isEmpty()){
            errors.add("voivodeship with id=" + adminDto.getVoivodeshipDto().getId() + " not found");
        }
    }
}

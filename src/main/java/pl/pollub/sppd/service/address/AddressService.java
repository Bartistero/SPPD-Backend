package pl.pollub.sppd.service.address;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.address.Voivodeship;
import pl.pollub.sppd.model.repository.VoivodeshipRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final VoivodeshipRepository repository;

    public List<VoivodeshipDto> getVoivodeship(){
        List<Voivodeship> voivodeshipList = repository.findAll();
        return  voivodeshipList.stream()
                .map(VoivodeshipDto::voivodeshipToVoivodeshipDto)
                .collect(Collectors.toList());
    }
}

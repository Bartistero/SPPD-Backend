package pl.pollub.sppd.service.address;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import pl.pollub.sppd.model.address.Borough;
import pl.pollub.sppd.model.address.County;
import pl.pollub.sppd.model.address.Voivodeship;
import pl.pollub.sppd.model.repository.BoroughRepository;
import pl.pollub.sppd.model.repository.CountyRepository;
import pl.pollub.sppd.model.repository.VoivodeshipRepository;
import pl.pollub.sppd.service.GeneralException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final VoivodeshipRepository voivodeshipRepository;
    private final CountyRepository countyRepository;
    private final BoroughRepository boroughRepository;

    public List<VoivodeshipDto> getVoivodeship() {
        List<Voivodeship> voivodeshipList = voivodeshipRepository.findAll();
        return voivodeshipList.stream()
                .map(VoivodeshipDto::voivodeshipToVoivodeshipDto)
                .collect(Collectors.toList());
    }

    public List<CountyDto> getCounty(Long id) throws GeneralException {
        Voivodeship voivodeship = voivodeshipRepository.findVoivodeshipById(id).orElseThrow(
                () -> new GeneralException("voivodeship with id: " + id + " not found!"));
        return voivodeship.getCounty().stream()
                .map(CountyDto::countyToCountyDto)
                .collect(Collectors.toList());
    }

    public List<BoroughDto> getBorough(Long id) throws GeneralException {
        County county = countyRepository.findCountyById(id).orElseThrow(
                () -> new GeneralException("County with id: " + id + " not found!"));
        return county.getBorough().stream()
                .map(BoroughDto::boroughToBoroughDto)
                .collect(Collectors.toList());
    }

    public List<CityDto> getCity(Long id) throws GeneralException {
        Borough borough = boroughRepository.findBoroughById(id).orElseThrow(
                () -> new GeneralException("County with id: " + id + " not found!"));
        return borough.getCity().stream()
                .map(CityDto::cityToCityDto)
                .collect(Collectors.toList());
    }
}

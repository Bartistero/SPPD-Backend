package pl.pollub.sppd.service.address;

import lombok.RequiredArgsConstructor;
import pl.pollub.sppd.model.address.Country;
import pl.pollub.sppd.model.repository.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AddressVerification {

    private final CountryRepository countryRepository;
    private final VoivodeshipRepository voivodeshipRepository;
    private final CountyRepository countyRepository;
    private final BoroughRepository boroughRepository;
    private final CityRepository cityRepository;
    private final StreetRepository streetRepository;


    private void checkAvailabilityAddress(CountryDto country, VoivodeshipDto voivodeship, BoroughDto borough) {
        List<String> errors = new ArrayList<>();

    }
}

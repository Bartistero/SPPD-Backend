package pl.pollub.sppd.service.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pollub.sppd.model.repository.*;
import pl.pollub.sppd.service.GeneralException;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AddressVerification {

    private final CountryRepository countryRepository;
    private final VoivodeshipRepository voivodeshipRepository;
    private final CountyRepository countyRepository;
    private final BoroughRepository boroughRepository;
    private final CityRepository cityRepository;
    private final StreetRepository streetRepository;


    public void checkAvailabilityAddress(CountryDto country, VoivodeshipDto voivodeship, CountyDto county, BoroughDto borough,
                                         CityDto city, StreetDto street) throws GeneralException {
        List<String> errors = new ArrayList<>();

        if (country == null)
            throw new GeneralException("Country field can not be null!");
        else if (countryRepository.findCountryById(country.getId()).isEmpty())
            errors.add("Voivodeship with id = " + voivodeship.getId() + " not found");

        if (voivodeship == null)
            throw new GeneralException("Voivodeship field can not be null!");
        else if (voivodeshipRepository.findVoivodeshipById(voivodeship.getId()).isEmpty())
            errors.add("Voivodeship with id = " + voivodeship.getId() + " not found");

        if (county == null)
            throw new GeneralException("County field can not be null!");
        else if (countyRepository.findCountyById(county.getId()).isEmpty())
            errors.add("County with id = " + county.getId() + " not found");

        if (borough == null)
            throw new GeneralException("Borough field can not be null!");
        else if (boroughRepository.findBoroughById(borough.getId()).isEmpty())
            errors.add("Borough with id = " + borough.getId() + " not found");

        if (city == null)
            throw new GeneralException("City field can not be null!");
        else if (cityRepository.findCityById(city.getId()).isEmpty())
            errors.add("City with id = " + city.getId() + " not found");

        if (street == null)
            throw new GeneralException("Street field can not be null!");
        else if (streetRepository.findById(street.getId()).isEmpty())
            errors.add("Street with id = " + street.getId() + " not found");

        if (errors.size() > 0)
            throw new GeneralException(errors);
    }
}

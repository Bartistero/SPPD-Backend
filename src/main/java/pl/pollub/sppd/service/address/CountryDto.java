package pl.pollub.sppd.service.address;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.address.Country;

@Getter
public class CountryDto extends AddressDto {

    public static CountryDto countryToCountryDto(Country country) {
        CountryDto countryDto = new CountryDto();
        BeanUtils.copyProperties(country, countryDto);
        return countryDto;
    }

    public static Country countryDtoToCountry(CountryDto countryDto) {
        Country country = new Country();
        country.setId(countryDto.getId());
        return country;
    }
}

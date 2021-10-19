package pl.pollub.sppd.service.address;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.address.City;

@Getter
public class CityDto extends AddressDto {

    public static CityDto cityToCityDto(City city) {
        CityDto cityDto = new CityDto();
        BeanUtils.copyProperties(city, cityDto, "voivodeshipCode", "CountyCode","BoroughCode");
        return cityDto;
    }

    public static City cityDtoToCity(CityDto cityDto){
        City city = new City();
        city.setId(cityDto.getId());
        return city;
    }
}

package pl.pollub.sppd.service.address;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.address.City;

@Getter
public class CityDto extends Address{

    public static CityDto boroughToBoroughDto(City city) {
        CityDto cityDto = new CityDto();
        BeanUtils.copyProperties(city, cityDto, "voivodeshipCode", "CountyCode","BoroughCode");
        return cityDto;
    }
}

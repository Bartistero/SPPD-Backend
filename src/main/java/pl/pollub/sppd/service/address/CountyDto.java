package pl.pollub.sppd.service.address;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.address.County;

@Getter
public class CountyDto extends AddressDto {

    public static CountyDto countyToCountyDto(County County) {
        CountyDto countyDto = new CountyDto();
        BeanUtils.copyProperties(County, countyDto, "voivodeshipCode","CountyCode");
        return countyDto;
    }
}

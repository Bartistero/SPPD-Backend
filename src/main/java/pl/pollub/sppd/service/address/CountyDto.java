package pl.pollub.sppd.service.address;

import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.address.County;

public class CountyDto extends AddressDto {

    public static CountyDto countyToCountyDto(County County) {
        CountyDto countyDto = new CountyDto();
        BeanUtils.copyProperties(County, countyDto, "voivodeshipCode", "CountyCode");
        return countyDto;
    }

    public static County countyDtoToCounty(CountyDto countyDto) {
        County county = new County();
        county.setId(countyDto.getId());
        return county;
    }
}

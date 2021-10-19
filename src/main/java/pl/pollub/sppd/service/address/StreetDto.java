package pl.pollub.sppd.service.address;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.address.Street;

@Getter
public class StreetDto extends AddressDto {

    private String characteristic;
    private String firstPart;
    private String secondPart;

    public static StreetDto streetToStreetDto(Street street) {
        StreetDto streetDto = new StreetDto();
        BeanUtils.copyProperties(street, streetDto, "voivodeshipCode", "CountyCode", "BoroughCode");
        return streetDto;
    }

    public static Street streetDtoToStreet(StreetDto streetDto) {
        Street street = new Street();
        street.setId(streetDto.getId());
        return street;
    }
}



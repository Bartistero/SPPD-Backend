package pl.pollub.sppd.service.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.address.Street;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class StreetDto {

    @Id
    private Long id;
    private String characteristic;
    private String firstPart;
    private String secondPart;

    public static StreetDto streetToStreetDto(Street street) {
        StreetDto streetDto = new StreetDto();
        BeanUtils.copyProperties(street, streetDto, "voivodeshipCode", "countyCode", "boroughCode");
        return streetDto;
    }

    public static Street streetDtoToStreet(StreetDto streetDto) {
        Street street = new Street();
        street.setId(streetDto.getId());
        return street;
    }
}



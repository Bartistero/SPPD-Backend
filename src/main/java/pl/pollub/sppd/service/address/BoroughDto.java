package pl.pollub.sppd.service.address;

import lombok.Getter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.address.Borough;

@Getter
public class BoroughDto extends AddressDto {

    public static BoroughDto boroughToBoroughDto(Borough borough) {
        BoroughDto boroughDto = new BoroughDto();
        BeanUtils.copyProperties(borough, boroughDto, "voivodeshipCode", "CountyCode","BoroughCode");
        return boroughDto;
    }
}

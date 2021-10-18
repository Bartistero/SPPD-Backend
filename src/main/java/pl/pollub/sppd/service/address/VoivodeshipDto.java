package pl.pollub.sppd.service.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.address.Voivodeship;

@Getter
@Setter
@NoArgsConstructor
public class VoivodeshipDto extends AddressDto {

    public static VoivodeshipDto voivodeshipToVoivodeshipDto(Voivodeship voivodeship) {
        VoivodeshipDto voivodeshipDto = new VoivodeshipDto();
        BeanUtils.copyProperties(voivodeship, voivodeshipDto, "voivodeshipCode");
        return voivodeshipDto;
    }
}

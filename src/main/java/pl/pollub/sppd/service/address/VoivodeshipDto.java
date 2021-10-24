package pl.pollub.sppd.service.address;

import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.address.Voivodeship;

public class VoivodeshipDto extends AddressDto {

    public static VoivodeshipDto voivodeshipToVoivodeshipDto(Voivodeship voivodeship) {
        VoivodeshipDto voivodeshipDto = new VoivodeshipDto();
        BeanUtils.copyProperties(voivodeship, voivodeshipDto, "voivodeshipCode");
        return voivodeshipDto;
    }

    public static Voivodeship voivodeshipDtoToVoivodeship(VoivodeshipDto voivodeshipDto) {
        Voivodeship voivodeship = new Voivodeship();
        voivodeship.setId(voivodeshipDto.getId());
        return voivodeship;
    }
}

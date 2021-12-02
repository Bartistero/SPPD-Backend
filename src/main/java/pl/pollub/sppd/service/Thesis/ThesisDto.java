package pl.pollub.sppd.service.Thesis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;

@Getter
@Setter
@NoArgsConstructor
public class ThesisDto extends ThesisSaveDto {

    Long id;
    Long reservation;

    public static ThesisDto thesisTitleToThesisDto(ThesisTitle thesisTitle, Long quantity) {
        ThesisDto thesisDto = new ThesisDto();
        thesisTitleToThesisSaveDto(thesisTitle, thesisDto);
        thesisDto.setId(thesisTitle.getId());
        thesisDto.setReservation(quantity);
        return thesisDto;
    }

    public static ThesisTitle thesisDtoToThesisTitle(ThesisDto thesisDto){
        ThesisTitle thesisTitle = new ThesisTitle();
        return thesisSaveDtoToThesisTitle(thesisDto);
    }
}

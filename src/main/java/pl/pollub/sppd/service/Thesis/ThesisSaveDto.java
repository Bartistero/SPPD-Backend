package pl.pollub.sppd.service.Thesis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;
import pl.pollub.sppd.model.thesisStatus.ThesisStatus;
import pl.pollub.sppd.model.typeOfThesis.TypeOfThesis;
import pl.pollub.sppd.service.year.YearDto;
import pl.pollub.sppd.service.user.UserDto;

@Getter
@Setter
@NoArgsConstructor
public class ThesisSaveDto {

    private String thesisName;
    private String description;
    private TypeOfThesis typeOfThesis;
    private YearDto year;
    private ThesisStatus thesisStatus;
    private UserDto lecturer;
    private Integer amountPeople;

    public static ThesisTitle thesisSaveDtoToThesisTitle(ThesisSaveDto thesisSaveDto) {
        ThesisTitle thesisTitle = new ThesisTitle();
        thesisTitle.setThesisName(thesisSaveDto.getThesisName());
        thesisTitle.setDescription(thesisSaveDto.getDescription());
        thesisTitle.setTypeOfThesis(thesisSaveDto.getTypeOfThesis());
        thesisTitle.setYear(YearDto.yearDtoToYear(thesisSaveDto.year));
        thesisTitle.setThesisStatus(thesisSaveDto.getThesisStatus());
        thesisTitle.getListOfPersonThesis().add(UserDto.userDtoToPerson(thesisSaveDto.getLecturer()));
        thesisTitle.setAmountPeople(thesisSaveDto.getAmountPeople());
        return thesisTitle;
    }
}

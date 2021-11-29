package pl.pollub.sppd.service.Thesis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.thesisStatus.ThesisStatus;
import pl.pollub.sppd.model.typeOfThesis.TypeOfThesis;
import pl.pollub.sppd.service.user.LecturerDto;
import pl.pollub.sppd.service.year.YearDto;

@Getter
@Setter
@NoArgsConstructor
public class ThesisSaveDto {

    private String thesisName;
    private String description;
    private TypeOfThesis typeOfThesis;
    private YearDto year;
    private ThesisStatus thesisStatus;
    private LecturerDto lecturer;
    private Integer amountPeople;

    protected static void thesisTitleToThesisSaveDto(ThesisTitle thesisTitle, ThesisSaveDto thesisDto){
        Person person = thesisTitle.getListOfPersonThesis().stream()
                .filter( p -> p.getPermission().equals(Permission.LECTURER))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        thesisDto.setThesisName(thesisTitle.getThesisName());
        thesisDto.setDescription(thesisTitle.getDescription());
        thesisDto.setTypeOfThesis(thesisTitle.getTypeOfThesis());
        thesisDto.setYear(YearDto.yearToYearDto(thesisTitle.getYear()));
        thesisDto.setThesisStatus(thesisTitle.getThesisStatus());
        thesisDto.setLecturer(LecturerDto.LecturerDtoToUserDto(person));
        thesisDto.setAmountPeople(thesisTitle.getAmountPeople());
    }
}

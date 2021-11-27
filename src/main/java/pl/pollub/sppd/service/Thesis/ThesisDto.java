package pl.pollub.sppd.service.Thesis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.user.LecturerDto;
import pl.pollub.sppd.service.year.YearDto;
import pl.pollub.sppd.service.user.UserDto;

@Getter
@Setter
@NoArgsConstructor
public class ThesisDto extends ThesisSaveDto {

    Long id;

    public static ThesisDto thesisTitleToThesisDto(ThesisTitle thesisTitle) {

        ThesisDto thesisDto = new ThesisDto();
        Person person = thesisTitle.getListOfPersonThesis().stream()
                .filter( p -> p.getPermission().equals(Permission.LECTURER))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        thesisDto.setId(thesisTitle.getId());
        thesisDto.setThesisName(thesisTitle.getThesisName());
        thesisDto.setDescription(thesisTitle.getDescription());
        thesisDto.setTypeOfThesis(thesisTitle.getTypeOfThesis());
        thesisDto.setYear(YearDto.yearToYearDto(thesisTitle.getYear()));
        thesisDto.setThesisStatus(thesisTitle.getThesisStatus());
        thesisDto.setLecturer(LecturerDto.LecturerDtoToUserDto(person));
        thesisDto.setAmountPeople(thesisTitle.getAmountPeople());
        return thesisDto;
    }

  /*  public static ThesisTitle thesisDtoToThesisTitle(ThesisDto thesisDto) {
        ThesisTitle thesisTitle = thesisSaveDtoToThesisTitle(thesisDto);
        thesisTitle.setId(thesisDto.getId());
        return thesisTitle;
    }*/
}

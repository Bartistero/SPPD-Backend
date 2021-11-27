package pl.pollub.sppd.service.Thesis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.pollub.sppd.model.Person;
import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.model.thesisStatus.ThesisStatus;
import pl.pollub.sppd.model.typeOfThesis.TypeOfThesis;

@Getter
@Setter
@NoArgsConstructor
public class ThesisDto {

    String thesisName;
    String description;
    TypeOfThesis typeOfThesis;
    String year;
    ThesisStatus thesisStatus;
    String lecturer;
    Integer amountPeople;

    public static ThesisDto thesisStatusToThesisDto(ThesisTitle thesisTitle){
        ThesisDto thesisDto = new ThesisDto();
        thesisDto.setThesisName(thesisTitle.getThesisName());
        thesisDto.setDescription(thesisTitle.getDescription());
        thesisDto.setTypeOfThesis(thesisTitle.getTypeOfThesis());
        thesisDto.setYear(thesisTitle.getYear().getYear());
        thesisDto.setThesisStatus(thesisTitle.getThesisStatus());
        Person person = thesisTitle.getListOfPersonThesis().stream()
                .filter( p -> p.getPermission().equals(Permission.LECTURER))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        thesisDto.setLecturer(person.getName() + " " +  person.getSurname());
        thesisDto.setAmountPeople(thesisTitle.getAmountPeople());
        return thesisDto;
    }
}

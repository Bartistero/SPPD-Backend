package pl.pollub.sppd.service.Thesis;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import pl.pollub.sppd.model.ThesisTitle.ThesisTitle;
import pl.pollub.sppd.model.permission.Permission;
import pl.pollub.sppd.service.user.LecturerDto;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ThesisDetailsDto extends ThesisSaveDto {

    private Long id;
    private List<LecturerDto> collaborator;

    public static ThesisDetailsDto thesisTitleToThesisDetailsDto(ThesisTitle thesisTitle) {
        ThesisDetailsDto thesisDto = new ThesisDetailsDto();
        thesisTitleToThesisSaveDto(thesisTitle, thesisDto);
        thesisDto.setId(thesisTitle.getId());
        thesisDto.setCollaborator(thesisTitle.getListOfPersonThesis().stream()
                .filter(f -> f.getPermission().equals(Permission.STUDENT))
                .map(LecturerDto::LecturerDtoToUserDto)
                .collect(Collectors.toList()));
        return thesisDto;
    }
}

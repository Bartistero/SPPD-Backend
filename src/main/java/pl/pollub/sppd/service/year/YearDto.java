package pl.pollub.sppd.service.year;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import pl.pollub.sppd.model.year.Year;

@Getter
@Setter
public class YearDto {

    Long id;
    String year;

    public static Year yearDtoToYear(YearDto yearDto) {
        Year year = new Year();
        BeanUtils.copyProperties(yearDto, year);
        return year;
    }

    public static YearDto yearToYearDto(Year year) {
        YearDto yearDto = new YearDto();
        BeanUtils.copyProperties(year, yearDto);
        return yearDto;
    }
}

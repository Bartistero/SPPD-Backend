package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.pollub.sppd.service.address.*;
import pl.pollub.sppd.service.GeneralException;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/voivodeship")
    public List<VoivodeshipDto> get() {
        return addressService.getVoivodeship();
    }

    @GetMapping("/count/{id}")
    public List<CountyDto> getCounty(@PathVariable Long id) throws GeneralException {
        return addressService.getCounty(id);
    }

    @GetMapping("/borough/{id}")
    public List<BoroughDto> getBorough(@PathVariable Long id) throws GeneralException {
        return addressService.getBorough(id);
    }

    @GetMapping("/city/{id}")
    public List<CityDto> getCity(@PathVariable Long id) throws GeneralException {
        return addressService.getCity(id);
    }
}

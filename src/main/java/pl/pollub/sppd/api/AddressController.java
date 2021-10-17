package pl.pollub.sppd.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.sppd.service.address.AddressService;
import pl.pollub.sppd.service.address.VoivodeshipDto;

import java.util.List;


@RestController
@RequestMapping("/address")
//@RequiredArgsConstructor
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/Voivodeship")
    public List<VoivodeshipDto> get() {
        return addressService.getVoivodeship();
    }
}

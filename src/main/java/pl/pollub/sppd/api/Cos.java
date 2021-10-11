package pl.pollub.sppd.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cos {

    @GetMapping("/cos")
    public String get(){
        return "Hello";
    }
}

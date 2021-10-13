package pl.pollub.sppd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SppdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SppdApplication.class, args);
		System.out.println("hasło bxcyrpt   " + new BCryptPasswordEncoder().encode("zaq1@WSX"));
	}

}

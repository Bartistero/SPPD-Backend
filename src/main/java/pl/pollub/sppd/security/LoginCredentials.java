package pl.pollub.sppd.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginCredentials {
    private String username;
    private String password;
}

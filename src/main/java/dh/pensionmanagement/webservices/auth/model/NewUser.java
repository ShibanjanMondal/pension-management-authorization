package dh.pensionmanagement.webservices.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUser {
    private String username;
    private String email;
    private String password;

}


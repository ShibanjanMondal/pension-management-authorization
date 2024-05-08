package dh.pensionmanagement.webservices.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_DETAILS")
public class UserDetailsEntity {
    @Id
    @Column(nullable = false, unique = true)
    private String username;
    private String email;
    private String password;

}

package custom.keycloak.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_custom")
@Getter
@Setter
public class UserCustom {

    @Id
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    @GeneratedValue(generator = "users_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_password_changed")
    private Boolean isPasswordChanged = false;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "last_login_date")
    private String lastLoginDate;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "email")
    private String email;

}

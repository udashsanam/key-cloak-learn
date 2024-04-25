package custom.keycloak.repo;

import custom.keycloak.model.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<UserCustom, Long> {
    UserCustom findByUsername(String username);
}

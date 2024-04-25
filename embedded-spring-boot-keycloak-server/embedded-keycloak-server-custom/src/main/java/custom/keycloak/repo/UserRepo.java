package custom.keycloak.repo;

import custom.keycloak.model.UserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<UserCustom, Long> {
    Optional<UserCustom> findByUsername(String username);
}

package custom.keycloak.spi;

import custom.keycloak.model.UserCustom;
import custom.keycloak.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCustomService {

    @Autowired
    private UserRepo userRepository;

    public UserCustom getUserByUserName(String username){
        Optional<UserCustom> userCustom = userRepository.findByUsername(username);
        if(userCustom.isPresent()){return userCustom.get();}
        return null;

    }

    public List<UserCustom> findAll(){
        return userRepository.findAll();
    }
}

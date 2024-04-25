package custom.keycloak.spi;

import custom.keycloak.model.UserCustom;
import custom.keycloak.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCustomService {

    @Autowired
    private UserRepo userRepository;

    public UserCustom getUserByUserName(String username){
        UserCustom userCustom = userRepository.findByUsername(username);
        if(userCustom == null){return  userCustom;}
        if(!username.equals("demo")) return null;
        UserCustom user = new UserCustom();
        user.setId(1l);
        user.setUsername("demo");
        user.setPassword("demo");
        user.setEmail("demo@demo.com");
        return user;
    }
}

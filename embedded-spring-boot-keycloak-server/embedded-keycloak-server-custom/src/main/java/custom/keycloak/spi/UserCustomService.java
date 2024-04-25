package custom.keycloak.spi;

import org.springframework.stereotype.Service;

@Service
public class UserCustomService {

    public UserCustom getUserByUserName(String username){
        if(!username.equals("demo")) return null;
        UserCustom user = new UserCustom();
        user.setId(1l);
        user.setUsername("demo");
        user.setPassword("demo");
        user.setEmail("demo@demo.com");
        return user;
    }
}

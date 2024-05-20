package custom.keycloak.test;

import java.util.Map;

public interface ValueExtractor <T>{

    Map<String, FormateChecker<String, Boolean>> extract(T laon);
}

package custom.keycloak.auth;

import custom.keycloak.spi.UserCustomService;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class CustomUserStorageProvider implements UserStorageProvider,
        UserLookupProvider {

    private static final Logger log = LoggerFactory.getLogger(CustomUserStorageProvider.class);
    private KeycloakSession session;
    private ComponentModel model;

    public CustomUserStorageProvider(UserCustomService userCustomService, KeycloakSession session, ComponentModel model) {
        this.session = session;
        this.model = model;
    }

    @Override
    public void close() {
        log.info("Closing UserStorageProvider");
    }

    @Override
    public UserModel getUserById(String id, RealmModel realmModel) {
        log.info("Getting user by id {}", id);
        StorageId storageId =  new StorageId(id);
        return getUserByUsername( storageId.getExternalId(), realmModel );
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realmModel) {
        log.info("Getting user by username {}", username);
        return null;
    }

    @Override
    public UserModel getUserByEmail(String s, RealmModel realmModel) {
        int[] arr;
        arr = new int[3];
        arr = new int[] {3,3,2};
        Arrays.stream(arr).forEach(x-> System.out.println(x));
        return null;
    }

//    private UserModel mapUser(RealmModel realm, ResultSet rs) throws SQLException {
//
//        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//        CustomUser user = new CustomUser.Builder(ksession, realm, model, rs.getString("username"))
//                .email(rs.getString("email"))
//                .firstName(rs.getString("firstName"))
//                .lastName(rs.getString("lastName"))
//                .birthDate(rs.getDate("birthDate"))
//                .build();
//
//        return user;
//    }
}

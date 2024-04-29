package custom.keycloak.spi;

import custom.keycloak.model.UserCustom;
import lombok.extern.jbosslog.JBossLog;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.CredentialModel;
import org.keycloak.models.*;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@JBossLog
public class UserStorageProvider implements org.keycloak.storage.UserStorageProvider ,
        UserLookupProvider,
        CredentialInputValidator ,
        UserQueryProvider

//        UserRegistrationProvider
         {

    private final UserCustomService userCustomService;

    private final KeycloakSession  session;

    private final ComponentModel  model;





    public UserStorageProvider(UserCustomService userCustomService, KeycloakSession session, ComponentModel model) {
        this.userCustomService = userCustomService;
        this.session = session;
        this.model = model;
    }



    @Override
    public void close() {
        log.info("closing transcion");
    }

    @Override
    public UserModel getUserById(String s, RealmModel realmModel) {
        StorageId storageId = new StorageId(s);
       String externalId = storageId.getExternalId();

        return getUserByUsername(externalId, realmModel);
    }

    @Override
    public UserModel getUserByUsername(String s, RealmModel realmModel) {

        UserCustom userCustom= userCustomService.getUserByUserName(s);

        if(userCustom ==null) return null;
        return  createAdapter(realmModel, userCustom);
    }

    @Override
    public UserModel getUserByEmail(String s, RealmModel realmModel) {
        return null;
    }

    private UserModel createAdapter(RealmModel realm, UserCustom user) {

        return new AbstractUserAdapterFederatedStorage(session, realm, model) {
            @Override
            public Set<RoleModel> getRealmRoleMappings() {
                return Set.of();
            }

            @Override
            public Set<RoleModel> getClientRoleMappings(ClientModel clientModel) {
                return Set.of();
            }

            @Override
            public Set<RoleModel> getRoleMappings() {
                return Set.of();
            } // anonymous class inheriting the abstract parent
            @Override
            public String getUsername() {
                log.infov("[Keycloak UserModel Adapter] Getting username ....");
                return user.getUsername();
            }

            @Override
            public String getEmail() {
                log.infov("[Keycloak UserModel Adapter] Getting email ....");
                return user.getEmail();
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public String getFirstName() {
                return user.getId().toString();
            }

            @Override
            public void setUsername(String username) {
                log.infov("[Keycloak UserModel Adapter] Setting username: {0}", username);
                user.setUsername(username);
            }

            @Override
            public void setEmail(String email) {
                log.infov("[Keycloak UserModel Adapter] Setting email: email={0}", email);
                user.setEmail(email);
            }

            @Override
            public Set<GroupModel> getGroups() {
                return Set.of();
            }

            @Override
            public Map<String, List<String>> getAttributes() {
                log.infov("[Keycloak UserModel Adapter] Getting all attributes ....");
                return getFederatedStorage().getAttributes(realm, this.getId());
            }

            @Override
            public Set<String> getRequiredActions() {
                return Set.of();
            }

            @Override
            public void setAttribute(String name, List<String> values) {
                log.infov("[Keycloak UserModel Adapter] Setting attribute {0} with values {1}", name, values);

                getFederatedStorage().setAttribute(realm, this.getId(), "id", Arrays.asList(user.getId().toString()));
            }

            @Override
            public List<String> getAttribute(String s) {
                return List.of();
            }
        };
    }

    @Override
    public boolean supportsCredentialType(String s) {
        return s.equals(CredentialModel.PASSWORD);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String s) {

        UserCustom userData = userCustomService.getUserByUserName(userModel.getUsername());
        String password = userData==null?null : userData.getPassword();
        log.infov("Checking authentication setup ...");
        return s.equals(CredentialModel.PASSWORD) && password != null;
    }

    @Override
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        UserCredentialModel cred = (UserCredentialModel) credentialInput;
        log.info(cred.getValue());
        UserCustom userData = userCustomService.getUserByUserName(userModel.getUsername());
        if(cred.getValue().equals(userData.getPassword())) return true;
        return false;
    }

    @Override
    public int getUsersCount(RealmModel realmModel) {
        return 0;
    }

    @Override
    public List<UserModel> getUsers(RealmModel realmModel) {

        log.infov("[Keycloak UserModel Adapter] Getting users ....");

        return List.of();
    }

    @Override
    public List<UserModel> getUsers(RealmModel realmModel, int i, int i1) {
        log.infov("[Keycloak UserModel Adapter] Getting users ....");
        return List.of();
    }

    @Override
    public List<UserModel> searchForUser(String s, RealmModel realmModel) {

        log.infov("[Keycloak UserModel Adapter] Getting users ....from user query provider for all users  ");
        List<UserCustom> userCustoms = userCustomService.findByUsernamelike(s);
        List<UserModel> userModels = userCustoms.stream().map(userCustom -> createAdapter(realmModel, userCustom)).collect(Collectors.toList());
        return userModels;
    }


    @Override
    public List<UserModel> searchForUser(String s, RealmModel realmModel, int i, int i1) {
        return List.of();
    }

    @Override
    public List<UserModel> searchForUser(Map<String, String> map, RealmModel realmModel) {
        log.infov("[Keycloak UserModel Adapter] Getting users ....from user query provider for all users  ");
        List<UserCustom> userCustoms = userCustomService.findAll();
        List<UserModel> userModels = userCustoms.stream().map(userCustom -> createAdapter(realmModel, userCustom)).collect(Collectors.toList());
        return userModels;
    }

    @Override
    public List<UserModel> searchForUser(Map<String, String> map, RealmModel realmModel, int i, int i1) {
        return List.of();
    }

    @Override
    public List<UserModel> getGroupMembers(RealmModel realmModel, GroupModel groupModel) {
        return List.of();
    }


    @Override
    public List<UserModel> getGroupMembers(RealmModel realmModel, GroupModel groupModel, int i, int i1) {
        return List.of();
    }

    @Override
    public List<UserModel> searchForUserByUserAttribute(String s, String s1, RealmModel realmModel) {
        return List.of();
    }

    /*
    this if to add user
     */
//    @Override
//    public UserModel addUser(RealmModel realmModel, String s) {
//        return null;
//    }
//
//    @Override
//    public boolean removeUser(RealmModel realmModel, UserModel userModel) {
//        return false;
//    }
}

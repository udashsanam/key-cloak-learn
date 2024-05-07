package custom.keycloak.config;

import com.google.auto.service.AutoService;
import lombok.extern.jbosslog.JBossLog;
import org.keycloak.models.ClientSessionContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.protocol.ProtocolMapper;
import org.keycloak.protocol.oidc.mappers.*;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.representations.AccessToken;

import java.util.ArrayList;
import java.util.List;

@AutoService(ProtocolMapper.class)
@JBossLog
public class TokenEnhancer extends AbstractOIDCProtocolMapper implements OIDCAccessTokenMapper, OIDCIDTokenMapper, UserInfoTokenMapper {


    private static final List<ProviderConfigProperty> configProperties = new ArrayList<>();






    static {
        OIDCAttributeMapperHelper.addIncludeInTokensConfig(configProperties, TokenEnhancer.class);
    }
    @Override
    public AccessToken transformAccessToken(AccessToken token, ProtocolMapperModel mappingModel, KeycloakSession session, UserSessionModel userSession, ClientSessionContext clientSessionCtx) {
        token.getOtherClaims().put("hello", "hello");
        return token;
    }

    // display on mapper
    @Override
    public String getDisplayCategory() {
        return "Token enhancer";
    }

    @Override
    public String getDisplayType() {
        return "Token enhancer";
    }

    @Override
    public String getHelpText() {
        return "this mapper is used to enhance token types";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return List.of();
    }

    @Override
    public String getId() {
        return "test--mapper";
    }
}

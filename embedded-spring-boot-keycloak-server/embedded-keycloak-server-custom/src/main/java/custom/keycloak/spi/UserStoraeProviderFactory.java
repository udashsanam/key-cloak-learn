package custom.keycloak.spi;

import com.google.auto.service.AutoService;
import lombok.extern.jbosslog.JBossLog;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

@JBossLog
@AutoService(org.keycloak.storage.UserStorageProviderFactory.class)
public class UserStoraeProviderFactory implements UserStorageProviderFactory<UserStorageProvider> {


    @Override
    public UserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        ServletContext servletContext = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getServletContext();
        WebApplicationContext appCtxt = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        UserCustomService userService = appCtxt.getBean(UserCustomService.class);
        return new UserStorageProvider(userService, keycloakSession, componentModel);
    }

    @Override
    public String getId() {
        return "testtestetst";
    }
}

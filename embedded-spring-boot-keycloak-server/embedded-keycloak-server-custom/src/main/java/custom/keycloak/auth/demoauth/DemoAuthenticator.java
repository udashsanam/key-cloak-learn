package custom.keycloak.auth.demoauth;

import custom.keycloak.auth.GreeterBean;
import org.jboss.logging.Logger;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.services.validation.Validation;
import org.keycloak.sessions.AuthenticationSessionModel;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class DemoAuthenticator implements Authenticator {

    private static Logger log = Logger.getLogger(DemoAuthenticator.class);

    private final KeycloakSession session;

    public DemoAuthenticator(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void authenticate(AuthenticationFlowContext context) {

        UserModel user = context.getUser();

        if (user != null) {
            greet(user.getUsername());
            log.infof("Pass through: %s%n", user.getUsername());
        } else {
            log.infof("Pass through: %s%n", "anonymous");
        }
        AuthenticationSessionModel authSession = context.getAuthenticationSession();
        authSession.setAuthNote("username", user.getUsername());
        authSession.setAuthNote("ttl", Long.toString(System.currentTimeMillis() + (300 * 1000)));

        // give new challege with new form for password reset
        // or you can give any challege for user
        Response challenge = context.form()
                .setAttribute("challengeQuestion", "What is your favorite color?")
                .createForm("custom-password-reset.ftl");
        context.challenge(challenge);

    }

    private void greet(String username) {

        ServletContext servletContext = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getServletContext();
        WebApplicationContext appCtxt = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        GreeterBean greeter = appCtxt.getBean(GreeterBean.class);
        greeter.greet(username);
    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
        // NOOP
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        // after challenge user will submit answe to this section
        // here valdate anser and send back chellenge to success
        // we can put passwor reset opt send and many more challege
        String userAnswer = context.getHttpRequest().getDecodedFormParameters().getFirst("challengeAnswer");
        // Assuming the correct answer is "blue" for simplicity. Replace with your logic.
        if ("blue".equalsIgnoreCase(userAnswer)) {
            context.success();
        } else {
            Response challenge = context.form()
                    .setError("Invalid answer", "adsflkhasfdkl", "ahlsdf")
                    .setAttribute("challengeQuestion", "What is your favorite color?")
                    .setAttribute("errorMessage", "invalid color")
                    .createForm("custom-password-reset.ftl");
//            context.failureChallenge(, challenge);
            context.failureChallenge(AuthenticationFlowError.INVALID_CREDENTIALS, challenge);
        }

//        AuthenticationSessionModel authSession = context.getAuthenticationSession();
//        String ttl = authSession.getAuthNote("ttl");
//
//        if (ttl == null) {
//            context.failureChallenge(AuthenticationFlowError.INTERNAL_ERROR,
//                    context.form().createErrorPage(Response.Status.INTERNAL_SERVER_ERROR));
//            return;
//        }

//        AuthenticationExecutionModel execution = context.getExecution();
//        if (execution.isRequired()) {
//            if (Long.parseLong(ttl) < System.currentTimeMillis()) {
//                // expired
//                Response challenge = context.form()
//                        .setAttribute("realm", context.getRealm())
//                        .setError("timeExpire")
//                        .createForm(TPL_CODE_EXPIRED);
//                context.challenge(challenge);
////                context.failureChallenge(AuthenticationFlowError.EXPIRED_CODE,
////                        context.form()
////                                .setAttribute("realm", context.getRealm())
////                                .setError("timeExpire")
////                                .createForm(TPL_CODE));
//                return;
//            } else {
//                // valid
//                MultivaluedMap<String, String> formData = context.getHttpRequest().getDecodedFormParameters();
//                String passwordOld = formData.getFirst("password-old");
//                String passwordNew = formData.getFirst("password-new");
//                String passwordConfirm = formData.getFirst("password-confirm");
//
//                if (Validation.isBlank(passwordOld)) {
//                    Response challenge = context.form()
//                            .setAttribute("realm", context.getRealm())
//                            .setError("oldPasswordEmpty")
//                            .createForm(TPL_CODE);
//                    context.challenge(challenge);
//                    return;
//                }
//                if (Validation.isBlank(passwordNew)) {
//                    Response challenge = context.form()
//                            .setAttribute("realm", context.getRealm())
//                            .setError("newPasswordEmpty")
//                            .createForm(TPL_CODE);
//                    context.failureChallenge(AuthenticationFlowError.INTERNAL_ERROR, challenge);
//                    return;
//                }
//                else if (!passwordNew.equals(passwordConfirm)) {
//                    Response challenge = context.form()
//                            .setAttribute("realm", context.getRealm())
//                            .setError("confirmPasswordNotEqual")
//                            .createForm(TPL_CODE);
//                    context.failureChallenge(AuthenticationFlowError.INTERNAL_ERROR, challenge);
//                    return;
//                }
//
//                if (!this.validatePasswordStrength(passwordNew)) {
//                    Response challenge = context.form()
//                            .setAttribute("realm", context.getRealm())
//                            .setError("newPasswordValidate")
//                            .createForm(TPL_CODE);
//                    context.challenge(challenge);
//                    return;
//                }
//
//                UserModel userModel = context.getUser();
//                String userName = null;
//                if(userModel.getUsername()!=null){
//                    userName = userModel.getUsername();
//                }else {
//                    userName = authSession.getAuthNote("username");
//                }
//                if(userName==null){
//                    Response challenge = context.form()
//                            .setAttribute("realm", context.getRealm())
//                            .setError("timeExpire")
//                            .createForm(TPL_CODE_EXPIRED);
//                    context.challenge(challenge);
//                    return;
//                }
//                ServletContext servletContext = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getServletContext();
//                WebApplicationContext appCtxt = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
//                UserMapper userMapper = appCtxt.getBean(UserMapper.class);
//                String dbOld = userMapper.getUserPasswordHash(userName);
//                if(!this.validatePassword(passwordOld, dbOld)){
//                    Response challenge = context.form()
//                            .setAttribute("realm", context.getRealm())
//                            .setError("oldPasswordDoesntMatch")
//                            .createForm(TPL_CODE);
//                    context.failureChallenge(AuthenticationFlowError.INTERNAL_ERROR, challenge);
//                    return;
//                }
//                userMapper.updatePasswordAndStatus(this.encodePassword(passwordNew), userName);
//                context.success();
//            }
//        } else if (execution.isConditional() || execution.isAlternative()) {
//            context.attempted();
//        }
        // NOOP
    }

    @Override
    public void close() {
        // NOOP
    }
}
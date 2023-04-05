//package com.learn.keycloakstudentapp.config;
//
//import com.learn.keycloakstudentapp.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests().antMatchers("/","/login").permitAll()
//            .antMatchers("/home/**").authenticated()
//            .and()
//            .csrf().disable()
//            .formLogin().loginPage("/login")
//            .defaultSuccessUrl("/home")
//            .usernameParameter("username")
//            .passwordParameter("password")
//            .and()
//            .exceptionHandling().accessDeniedPage("/access-denial")
//            .and()
//            .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//    }
//
//    // docker run  --name keycloak1 --link mysql:mysql -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin  -e DB_VENDOR=mysql -e DB_ADDR=mysql -e DB_PORT=3306 -e DB_DATABASE=keycloak -e DB_USER=root -e DB_PASSWORD=System@123 -p 8080:8080 jboss/keycloak:15.0.2
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//}

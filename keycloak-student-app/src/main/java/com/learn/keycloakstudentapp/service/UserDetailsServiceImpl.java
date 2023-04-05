//package com.learn.keycloakstudentapp.service;
//
//import com.learn.keycloakstudentapp.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User  user =  userService.findByUsername(username);
//        if(user==null){
//            throw new UsernameNotFoundException("User not found");
//        }
//        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
//        user.getRoles().stream().forEach(roles -> {
//            grantedAuthoritySet.add(new SimpleGrantedAuthority(roles.getRole()));
//        });
//
//        return new com.learn.keycloakstudentapp.entity.UserDetails(user.getUsername(), user.getPassword(), grantedAuthoritySet);
//    }
//}

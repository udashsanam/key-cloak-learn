//package com.learn.keycloakprofessorapp.security;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import com.learn.keycloakprofessorapp.entity.Roles;
//import com.learn.keycloakprofessorapp.entity.User;
//import com.learn.keycloakprofessorapp.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//
//	@Autowired
//	private UserService userService;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		User user = userService.getUserByUsername(username);
//
//		if(user == null) {
//			throw new UsernameNotFoundException("User not found");
//		}
//
//		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//
//		for(Roles role : user.getRoles()) {
//			authorities.add(new SimpleGrantedAuthority(role.getRole()));
//		}
//
//		return new org.springframework.security.core.userdetails
//		.User(user.getUsername(), user.getPassword(), authorities);
//
//	}
//
//}

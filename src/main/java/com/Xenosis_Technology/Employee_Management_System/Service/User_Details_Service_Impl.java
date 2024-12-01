package com.Xenosis_Technology.Employee_Management_System.Service;

import com.Xenosis_Technology.Employee_Management_System.Entity.User;
import com.Xenosis_Technology.Employee_Management_System.Repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class User_Details_Service_Impl implements UserDetailsService {

    @Autowired
    private User_Repository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve the user from the repository
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Convert roles to authorities
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .authorities(user.getRole().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Add "ROLE_" prefix
                        .collect(Collectors.toList()))
                .build();
    }

}



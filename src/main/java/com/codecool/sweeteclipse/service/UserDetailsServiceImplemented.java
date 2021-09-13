package com.codecool.sweeteclipse.service;


import com.codecool.sweeteclipse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImplemented implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public UserDetailsServiceImplemented(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<com.codecool.sweeteclipse.model.User> maybeOurUser = userRepo.findUserByName(s);
        Optional<UserDetails> maybeUserDetails = maybeOurUser.map(this::mapOurUserToSecurityUser);
        return maybeUserDetails.orElseThrow( () -> new UsernameNotFoundException("Could not find user " + s) );


    }

    public UserDetails mapOurUserToSecurityUser(com.codecool.sweeteclipse.model.User ourUser) {
        String[] ourUserAuthorities = ourUser.getRoles().stream()
                .map(userRole -> userRole.toNoPrefixString())
                .toArray(String[]::new);
        UserDetails result = User
                .withUsername(ourUser.getName())
                .password(ourUser.getPassword())
                .authorities(ourUserAuthorities)
                .build();
        return result;
    }
}

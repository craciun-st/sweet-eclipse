package com.codecool.sweeteclipse.service;

import com.codecool.sweeteclipse.controller.exceptions.ObjectIdNotFoundException;
import com.codecool.sweeteclipse.model.authentication.SignUpRequest;
import com.codecool.sweeteclipse.model.user.PublicUserData;
import com.codecool.sweeteclipse.model.user.User;
import com.codecool.sweeteclipse.model.user.UserRole;
import com.codecool.sweeteclipse.repository.UserRepository;
import com.codecool.sweeteclipse.service.exceptions.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {
    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserManagementService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUserAccount(
            SignUpRequest userInfo
    ) throws UserAlreadyExistException {
        if (usernameExists(userInfo.getUsername())) {
            throw new UserAlreadyExistException(
                    "There is an account with that username: "
                            + userInfo.getUsername()
            );
        }

        User eclipseUser = new User();

        eclipseUser.setName(userInfo.getUsername());
        eclipseUser.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        eclipseUser.setEmail(userInfo.getEmail());

        eclipseUser.addRole(UserRole.USER);

        return userRepo.save(eclipseUser);
    }

    private boolean usernameExists(String name) {
        return userRepo.existsByName(name);
    }

    public User getUserWithName(String username) {
        return userRepo.findUserByName(username).orElseThrow(
                () -> { throw new RuntimeException("User not found: "+username); }
        );
    }

    public PublicUserData mapUserToPublicData(User user) {
        return new PublicUserData(
                user.getId(),
                user.getName(),
                user.getRoles()
        );
    }

    public User getLocalContextUser(SecurityContext context) throws ObjectIdNotFoundException {
        String usernameFromHeader = context.getAuthentication().getName();
        User user = userRepo.findUserByName(usernameFromHeader).orElseThrow(ObjectIdNotFoundException::new);
        return user;
    }
}

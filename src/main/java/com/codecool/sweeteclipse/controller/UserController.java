package com.codecool.sweeteclipse.controller;

import com.codecool.sweeteclipse.controller.exceptions.ObjectIdNotFoundException;
import com.codecool.sweeteclipse.model.user.PublicUserData;
import com.codecool.sweeteclipse.model.user.User;
import com.codecool.sweeteclipse.model.authentication.SignUpRequest;
import com.codecool.sweeteclipse.repository.UserRepository;
import com.codecool.sweeteclipse.service.UserManagementService;
import com.codecool.sweeteclipse.service.exceptions.UserAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import static com.codecool.sweeteclipse.controller.DonationController.getFieldToErrorMap;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserRepository userRepo;
    private UserManagementService userManagementService;

    @Autowired
    public UserController(UserRepository userRepo, UserManagementService userManagementService) {
        this.userRepo = userRepo;
        this.userManagementService = userManagementService;
    }




    @PostMapping("/api/signup")
    public ResponseEntity<PublicUserData> addUser(
            @Valid @RequestBody SignUpRequest userInfo
    ) throws UserAlreadyExistException {
        User createdUser = userManagementService.registerNewUserAccount(userInfo);

        Long createdUserId = createdUser.getId();
        URI createdUserUri = linkTo(
                methodOn(UserController.class).getPublicUserInfo(createdUserId)
        ).toUri();
        PublicUserData createdUserPublicData = userManagementService.mapUserToPublicData(createdUser);

        return ResponseEntity.created(createdUserUri).body(createdUserPublicData);
    }



    @GetMapping("/api/user/{id}")
    public ResponseEntity<PublicUserData> getPublicUserInfo(@PathVariable Long id) {
        User foundUser = userRepo.findUserById(id)
                .orElseThrow(ObjectIdNotFoundException::new);
        PublicUserData foundUserPublicData = userManagementService.mapUserToPublicData(foundUser);
        return ResponseEntity.ok(foundUserPublicData);
    }


    @CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
    @GetMapping("/api/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok().build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserAlreadyExistException.class)
    public Map<String, String> handleUserAlreadyExistsException(UserAlreadyExistException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("username", "Username already exists!");
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return getFieldToErrorMap(ex);
    }

}

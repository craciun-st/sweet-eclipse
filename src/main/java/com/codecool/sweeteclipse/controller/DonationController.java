package com.codecool.sweeteclipse.controller;

import com.codecool.sweeteclipse.controller.exceptions.ObjectIdNotFoundException;
import com.codecool.sweeteclipse.model.Project;
import com.codecool.sweeteclipse.model.request.AnonDonationDto;
import com.codecool.sweeteclipse.model.user.User;
import com.codecool.sweeteclipse.model.user.UserRole;
import com.codecool.sweeteclipse.repository.ProjectRepository;
import com.codecool.sweeteclipse.repository.UserRepository;
import com.codecool.sweeteclipse.service.DonationManagementService;
import com.codecool.sweeteclipse.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(value = "http://localhost:3000")
@RestController
public class DonationController {

    private UserRepository userRepo;
    private ProjectRepository projectRepo;
    private DonationManagementService donationManagementService;
    private UserManagementService userManagementService;

    @Autowired
    public DonationController(
            UserRepository userRepo,
            ProjectRepository projectRepo,
            DonationManagementService donationManagementService,
            UserManagementService userManagementService
    ) {
        this.userRepo = userRepo;
        this.projectRepo = projectRepo;
        this.donationManagementService = donationManagementService;
        this.userManagementService = userManagementService;
    }

    @PostMapping("/api/donate/as/anon")
    public void donateAnonymously(@Valid @RequestBody AnonDonationDto donationRequest) {
        User anonUser = userRepo.findFirstByRolesContains(UserRole.PLACEHOLDER_ANONYMOUS)
                .orElseThrow( () -> {throw new RuntimeException("There is no Anonymous User in DB!");} );
        double amountToDonate = donationRequest.getAmount();
        Long projectId = donationRequest.getProjectId();
        Project targetProject = projectRepo.findById(projectId).orElseThrow(ObjectIdNotFoundException::new);
        anonUser.donate(amountToDonate, targetProject, donationManagementService);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        return getFieldToErrorMap(ex);
    }

    static Map<String, String> getFieldToErrorMap(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );
        return errors;
    }
}

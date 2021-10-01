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
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    Logger logger = LoggerFactory.getLogger(DonationController.class);


    @Value("${stripe.apiKey}")
    String stripeSecret;

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
    public ResponseEntity< Map<String, String> > donateAnonymously(
            @Valid @RequestBody AnonDonationDto donationRequest
    ) {

        double amountToDonate = donationRequest.getAmount();
        Long projectId = donationRequest.getProjectId();
        String emailOrDefault = donationRequest.getEmailOrDefault();

        Map<String, String> responseMap = new HashMap<>();
        Map<String,String> metadata = new HashMap<>();
        metadata.put("projectId", projectId.toString());

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount((long) Math.floor(amountToDonate*100))
                        .setCurrency("eur")
                        .addPaymentMethodType("card")
                        .putAllMetadata(metadata)
                        .setReceiptEmail(emailOrDefault)
                        .build();
        PaymentIntent intent = null;
        try {
            intent = PaymentIntent.create(params);
        } catch (StripeException exception) {
            logger.warn("Error while trying to donate " + amountToDonate +"EUR: " + exception.getMessage());
            responseMap.put("error", "Could not finalize donation!");
            return ResponseEntity.internalServerError().body(responseMap);
        }

        responseMap.put("client_secret", intent.getClientSecret());

        return ResponseEntity.ok(responseMap);



    }

    @PostMapping("/api/donate/as/user")
    public void donateAsUser(@Valid @RequestBody AnonDonationDto donationRequest) {
        User user = userManagementService.getLocalContextUser(SecurityContextHolder.getContext());
        double amountToDonate = donationRequest.getAmount();
        Long projectId = donationRequest.getProjectId();
        Project targetProject = projectRepo.findById(projectId).orElseThrow(ObjectIdNotFoundException::new);
        user.donate(amountToDonate, targetProject, donationManagementService);
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

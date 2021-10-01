package com.codecool.sweeteclipse.controller;

import com.codecool.sweeteclipse.controller.exceptions.ObjectIdNotFoundException;
import com.codecool.sweeteclipse.model.Project;
import com.codecool.sweeteclipse.model.user.User;
import com.codecool.sweeteclipse.model.user.UserRole;
import com.codecool.sweeteclipse.repository.ProjectRepository;
import com.codecool.sweeteclipse.repository.UserRepository;
import com.codecool.sweeteclipse.service.DonationManagementService;
import com.google.gson.JsonSyntaxException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class StripeListener {

    Logger logger = LoggerFactory.getLogger(StripeListener.class);

    @Value("${stripe.webhookSecret}")
    private String webhookSecret;


    private UserRepository userRepo;
    private ProjectRepository projectRepo;
    private DonationManagementService donationManagementService;


    @Autowired
    public StripeListener(UserRepository userRepo, ProjectRepository projectRepo, DonationManagementService donationManagementService) {
        this.userRepo = userRepo;
        this.projectRepo = projectRepo;
        this.donationManagementService = donationManagementService;
    }

    @PostMapping("/api/stripe/listen")
    public ResponseEntity<String> webhookALaStripe(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String signatureHeader
    ) {
        Event event = null;
        try {
            event = Webhook.constructEvent(
                    payload,
                    signatureHeader,
                    webhookSecret
            );
        } catch (JsonSyntaxException e) {
            logger.error("Invalid payload: " + payload);
            return ResponseEntity.notFound().build();
        } catch (SignatureVerificationException e) {
            logger.error(
                    "Invalid signature: " +
                            ((signatureHeader != null && !signatureHeader.equals("")) ? signatureHeader : "EMPTY")
            );
            return ResponseEntity.notFound().build();
        }

        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            logger.error("Deserialization failed, probably due to an API version mismatch!");
            return ResponseEntity.internalServerError().build();
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }

        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                Long projectId = Long.parseLong(paymentIntent.getMetadata().get("projectId"));
                Double amountToDonate = paymentIntent.getAmount() / 100.00;
                logger.info("Payment succeeded for: " + paymentIntent.toString().substring(31, 64) + "...");
                logger.info(paymentIntent.getId() + " projectId: " + projectId);
                logger.info(paymentIntent.getId() + " amount: " + amountToDonate);
                handlePaymentSuccess(amountToDonate, projectId);
                break;
            // ... handle other event types
            default:
                logger.warn("Unhandled event type: " + event.getType());
        }

        return ResponseEntity.ok().build();
    }

    public void handlePaymentSuccess(Double amountToDonate, Long projectId) {
        User anonUser = userRepo.findFirstByRolesContains(UserRole.PLACEHOLDER_ANONYMOUS)
                .orElseThrow( () -> {throw new RuntimeException("There is no Anonymous User in DB!");} );
        Project targetProject = projectRepo.findById(projectId).orElseThrow(ObjectIdNotFoundException::new);
        anonUser.donate(amountToDonate, targetProject, donationManagementService);
    }
}

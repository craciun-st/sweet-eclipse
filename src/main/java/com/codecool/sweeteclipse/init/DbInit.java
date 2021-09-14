package com.codecool.sweeteclipse.init;

import com.codecool.sweeteclipse.model.*;
import com.codecool.sweeteclipse.model.user.User;
import com.codecool.sweeteclipse.model.user.UserRole;
import com.codecool.sweeteclipse.repository.*;
import com.codecool.sweeteclipse.service.DonationManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class DbInit implements CommandLineRunner {

    private ImageDataRepository imageRepo;
    private ProjectRepository projectRepo;
    private ProjectTagRepository tagRepo;
    private DonationRepository donationRepo;
    private UserRepository userRepo;

    private DonationManagementService donationManagement;
    private PasswordEncoder passwordEncoder;



    @Autowired
    public DbInit(
            ImageDataRepository imageRepo,
            ProjectRepository projectRepo,
            ProjectTagRepository tagRepo,
            DonationRepository donationRepo,
            UserRepository userRepo,
            DonationManagementService donationManagement,
            PasswordEncoder passwordEncoder
    ) {
        this.imageRepo = imageRepo;
        this.projectRepo = projectRepo;
        this.tagRepo = tagRepo;
        this.donationRepo = donationRepo;
        this.userRepo = userRepo;
        this.donationManagement = donationManagement;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        ImageData image1 = new ImageData("/image_0001.png", null);
        ImageData image2 = new ImageData("/image_0002.png", null);
        ImageData image3 = new ImageData("/image_0003.png", null);
        ProjectTag tag1 = new ProjectTag("pets");
        ProjectTag tag2 = new ProjectTag("environment");
        ProjectTag tag3 = new ProjectTag("community");
        Project strayDogProject = new Project(
                "Save the stray dogs!",
                "Collaborate to find a shelter for these misunderstood beings",
                1000
        );
        Project riverCleanupProject = new Project(
                "Help fund the biggest river cleanup effort!",
                "The river X has too many junk floating in it! We need your help to finance a cleanup operation.",
                2000
        );

        User admin = new User(
                1L,
                "Admin",
                "admin@sweeteclipse.com",
                null,
                Set.of(UserRole.ADMIN, UserRole.USER),
                new LinkedList<Donation>()
        );
        admin.setPassword(passwordEncoder.encode("987"));

        User anon = new User(
                2L,
                "Anonymous",
                "_@test.test",
                "",
                Set.of(UserRole.PLACEHOLDER_ANONYMOUS),
                new LinkedList<Donation>()
        );

        User johnDoe = new User("John Doe", "john_doe@test.test", null);
        johnDoe.setPassword(passwordEncoder.encode("123"));


        strayDogProject.addImage(image1);
        strayDogProject.addImage(image2);
        riverCleanupProject.addImage(image3);

        strayDogProject.addTag(tag1);
        riverCleanupProject.addTag(tag2);
        riverCleanupProject.addTag(tag3);


        strayDogProject.setCurrentFunds(45.75);
        riverCleanupProject.setCurrentFunds(507.48);

        strayDogProject.setNrDonors(8);
        riverCleanupProject.setNrDonors(89);



        projectRepo.saveAll(List.of(strayDogProject, riverCleanupProject));
        imageRepo.saveAll(List.of(image1, image2, image3));
        tagRepo.saveAll(List.of(tag1, tag2));
        userRepo.save(admin);
        userRepo.save(anon);
        userRepo.save(johnDoe);

        // this saves donations to donationRepo
        anon.donate(1, riverCleanupProject, donationManagement);
        johnDoe.donate(4.25, strayDogProject, donationManagement);
        anon.donate(2, riverCleanupProject, donationManagement);





    }
}

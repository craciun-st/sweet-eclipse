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
        ImageData image1 = new ImageData(
                "https://imagestoragetest.s3.eu-central-1.amazonaws.com/project_03Iz3iPs6kA0FpkJ/d61d86f9-6241-4b9f-97be-8eda54a0c3c5.png",
                null
        );
        ImageData image2 = new ImageData(
                "https://imagestoragetest.s3.eu-central-1.amazonaws.com/project_03Iz3iPs6kA0FpkJ/8c789d59-171e-4906-8e68-dbdd54c69f99.png",
                null
        );
        ImageData image3 = new ImageData(
                "https://imagestoragetest.s3.eu-central-1.amazonaws.com/project_4bOENjbbaRKmp7Vi/f980d427-1990-4114-a7f3-821b799f4d4d.png",
                null
        );
        ImageData image4 = new ImageData(
                "https://imagestoragetest.s3.eu-central-1.amazonaws.com/project_sOvdQ25AE4G3lOnY/c1fd684c-26a6-4e93-8ba2-81227d4ebe0a.png",
                null
        );
        ImageData image5 = new ImageData(
                "https://imagestoragetest.s3.eu-central-1.amazonaws.com/project_ZV9nOVNeqqVzsQuk/51cb5a82-a84c-42b6-90b2-d03ba245b8c8.png",
                null
        );
        ImageData image6 = new ImageData(
                "https://imagestoragetest.s3.eu-central-1.amazonaws.com/project_1fsCK3Yoa4frikw2/fa29922c-bbdb-4aa5-ab4f-8dc9909d307d.png",
                null
        );
        ImageData image7 = new ImageData(
                "https://imagestoragetest.s3.eu-central-1.amazonaws.com/project_9iUfPtmwmpymh5hi/c4d09a26-1d4b-4f1b-b6f3-d5900d6cd9bb.png",
                null
        );
        ImageData image8 = new ImageData(
                "https://imagestoragetest.s3.eu-central-1.amazonaws.com/project_lN0RNFAtGwvKA1Jl/55516b1e-7bd0-4379-9444-4df28e49288b.png",
                null
        );
        ImageData image9 = new ImageData(
                "https://imagestoragetest.s3.eu-central-1.amazonaws.com/project_RAntsU0HPYkV9J79/37e07616-31a5-40d7-9c15-eeea5402a377.png",
                null
        );
        ProjectTag tag1 = new ProjectTag("pets");
        ProjectTag tag2 = new ProjectTag("environment");
        ProjectTag tag3 = new ProjectTag("community");
        ProjectTag medicineTag = new ProjectTag("healthcare");
        ProjectTag pandemicRelief = new ProjectTag("pandemic relief");
        ProjectTag recyclingTag = new ProjectTag("recycling");
        ProjectTag artTag = new ProjectTag("art");
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
        Project apprenticeFairProject = new Project(
                "Help organize a creative space for young apprentices",
                "We want to organize a fair where young apprentices can display their talents",
                1200
        );
        Project artistLegacyProject = new Project(
                "Assist us in saving the legacy of artist T.H. Lus",
                "While only a few of his works are known, there are many more out there to be found",
                750
        );
        Project heatingWoodProject = new Project(
                "Help us supply heating wood for the winter",
                "It may not seem like much, but every little bit counts. " +
                        "Families in rural communities that are struggling financially can greatly " +
                        "benefit from this small donation",
                500
        );
        Project medicalSuppliesProject = new Project(
                "Relieve strained medical units by donating for supplies!",
                "With the current pandemic, many medical units have struggled to keep up with the consumption rate" +
                        " of their supplies. Donate now so that those who save lives may continue doing so!",
                1500
        );
        Project itLabProject = new Project(
                "Sustain the future of education!",
                "Help us raise funds for setting up an IT lab in our school. " +
                        "The funds will not only go towards equipment, but also towards hiring bright young teachers",
                2000
        );
        Project recycleTransportProject = new Project(
                "Promote recycling by helping us set up a new transport service!",
                "We want to encourage people to recycle more. But we know that transport of recyclables to centers " +
                        "is often difficult. Help us set up a transport " +
                        "service which can service the entire community.",
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
        apprenticeFairProject.addImage(image4);
        artistLegacyProject.addImage(image5);
        heatingWoodProject.addImage(image6);
        medicalSuppliesProject.addImage(image7);
        itLabProject.addImage(image8);
        recycleTransportProject.addImage(image9);

        strayDogProject.addTag(tag1);
        riverCleanupProject.addTag(tag2);
        riverCleanupProject.addTag(tag3);
        artistLegacyProject.addTag(artTag);
        medicalSuppliesProject.addTag(medicineTag);
        medicalSuppliesProject.addTag(pandemicRelief);
        recycleTransportProject.addTag(recyclingTag);


        strayDogProject.setCurrentFunds(45.75);
        riverCleanupProject.setCurrentFunds(507.48);
        apprenticeFairProject.setCurrentFunds(1151.57);
        artistLegacyProject.setCurrentFunds(383.85);
        heatingWoodProject.setCurrentFunds(343.84);
        medicalSuppliesProject.setCurrentFunds(1389.74);
        itLabProject.setCurrentFunds(577.80);
        recycleTransportProject.setCurrentFunds(1633.66);

        strayDogProject.setNrDonors(8);
        riverCleanupProject.setNrDonors(89);
        apprenticeFairProject.setNrDonors(202);
        artistLegacyProject.setNrDonors(67);
        heatingWoodProject.setNrDonors(60);
        medicalSuppliesProject.setNrDonors(243);
        itLabProject.setNrDonors(101);
        recycleTransportProject.setNrDonors(286);



        projectRepo.saveAll(List.of(
                strayDogProject,
                recycleTransportProject,
                medicalSuppliesProject,
                apprenticeFairProject,
                itLabProject,
                artistLegacyProject,
                heatingWoodProject,
                riverCleanupProject
        ));
        imageRepo.saveAll(List.of(
                image1, image2, image3, image4, image5, image6, image7, image8, image9
        ));
        tagRepo.saveAll(List.of(tag1, tag2, tag3, medicineTag, pandemicRelief, recyclingTag, artTag));
        userRepo.save(admin);
        userRepo.save(anon);
        userRepo.save(johnDoe);

        // this saves donations to donationRepo
        anon.donate(1, riverCleanupProject, donationManagement);
        johnDoe.donate(4.25, strayDogProject, donationManagement);
        anon.donate(2, riverCleanupProject, donationManagement);





    }
}

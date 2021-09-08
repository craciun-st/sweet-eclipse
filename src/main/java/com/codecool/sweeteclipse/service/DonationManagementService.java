package com.codecool.sweeteclipse.service;

import com.codecool.sweeteclipse.model.Donation;
import com.codecool.sweeteclipse.model.Project;
import com.codecool.sweeteclipse.model.User;
import com.codecool.sweeteclipse.repository.DonationRepository;
import com.codecool.sweeteclipse.repository.ProjectRepository;
import com.codecool.sweeteclipse.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class DonationManagementService {

    private Logger logger = LoggerFactory.getLogger(DonationManagementService.class);

    private DonationRepository donationRepo;
    private UserRepository userRepo;
    private ProjectRepository projectRepo;

    @Autowired
    public DonationManagementService(
            DonationRepository donationRepo,
            UserRepository userRepo,
            ProjectRepository projectRepo
    ) {
        this.donationRepo = donationRepo;
        this.userRepo = userRepo;
        this.projectRepo = projectRepo;
    }



    public void registerDonation(@NotNull Donation donation) {
        User donor = donation.getDonor();
        Project project = donation.getProject();
        boolean donatedBefore = donationRepo.existsByDonorAndProject(donor, project);
        try {
            //TODO handle edge cases and throw errors accordingly
            donor.addDonation(donation);
            project.addDonation(donation, donatedBefore);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return;
        }
        donationRepo.save(donation);
        userRepo.save(donor);
        projectRepo.save(project);
    }
}

package com.codecool.sweeteclipse.repository;

import com.codecool.sweeteclipse.model.Donation;
import com.codecool.sweeteclipse.model.Project;
import com.codecool.sweeteclipse.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    Integer countAllByDonorAndProject(User donor, Project project);

    Boolean existsByDonorAndProject(User donor, Project project);
}

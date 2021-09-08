package com.codecool.sweeteclipse.repository;

import com.codecool.sweeteclipse.model.Donation;
import com.codecool.sweeteclipse.model.Project;
import com.codecool.sweeteclipse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

}

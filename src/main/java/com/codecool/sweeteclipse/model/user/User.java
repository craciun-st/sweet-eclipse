package com.codecool.sweeteclipse.model.user;

import com.codecool.sweeteclipse.model.Donation;
import com.codecool.sweeteclipse.model.Project;
import com.codecool.sweeteclipse.service.DonationManagementService;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class User extends AbstractAncestorOfUser {



    @Email
    private String email;

    @JsonIgnore
    private String password;



    @OneToMany(mappedBy = "donor", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Donation> donations;


    // Constructors
    public User() {
        super();
        this.email = "null@null.null";
        this.donations = new LinkedList<>();
    }


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = new HashSet<>(List.of(UserRole.USER)); // this is super()
        this.donations = new LinkedList<>();
    }

    public User(Long id, String name, String email, String password, Set<UserRole> roles, List<Donation> donations) {
        super(id, name, roles);
        this.email = email;
        this.password = password;
        this.donations = donations;
    }



    // Getters and setters



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    // custom functions

    public void addDonation(Donation donation) {
        if (donation.getDonor() == null || donation.getDonor().equals(this)) {
            if (donation.getDonor() == null) {
                donation.setDonor(this);
            }
            this.donations.add(donation);
        }
    }

    public void removeDonationById(long id) {
        Donation foundDonation = donations.stream()
                .filter(donation -> donation.getId() == id)
                .findFirst()
                .orElseThrow(
                        () -> {
                            throw new RuntimeException(
                                    "Could not find donation with id:" + id + " for User:" + this.name
                            );
                        }
                );
        this.donations.remove(foundDonation);
    }


    public void donate(
            double amount,
            @NotNull Project project,
            DonationManagementService donationManagement
    ) {
        if (amount > 0) {
            Donation newDonation = new Donation(null, this, amount, project);
            donationManagement.registerDonation(newDonation);
        }
    }


}

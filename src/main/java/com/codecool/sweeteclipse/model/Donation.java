package com.codecool.sweeteclipse.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private User donor;

    @Positive
    @NotNull
    private Double amount;

    @ManyToOne
    private Project project;

    // Constructors


    public Donation() {
        amount = 1e-16;
    }

    public Donation(Long id, User donor, Double amount, Project project) {
        this.id = id;
        this.donor = donor;
        this.amount = amount;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getDonor() {
        return donor;
    }

    public void setDonor(User donor) {
        this.donor = donor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

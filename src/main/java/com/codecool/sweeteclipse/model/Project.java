package com.codecool.sweeteclipse.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    private String title;

    private String description;

    private Double currentFunds;
    private Double fundingGoal;

    private Integer nrDonors;

    @JsonManagedReference
    @OneToMany(mappedBy = "depictedProject", cascade = CascadeType.ALL)
    private List<ImageData> images;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_tag",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private Set<ProjectTag> tags;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Donation> donations;


    public Project() {
        this.status = ProjectStatus.MISSING_INFO;
        this.images = Collections.emptyList();
        this.tags = Collections.emptySet();
    }

    // Constructors
    public Project(
            Long id,
            ProjectStatus status,
            String title,
            String description,
            Double fundingGoal,
            List<ImageData> images,
            Set<ProjectTag> tags,
            Set<Donation> donations
    ) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.currentFunds = 0.0;
        this.fundingGoal = fundingGoal;
        this.nrDonors = 0;
        this.images = images;
        this.tags = tags;
        this.donations = donations;
    }

    public Project(String title, String description, double fundingGoal) {
        this.status = ProjectStatus.ACTIVE;
        this.title = title;
        this.description = description;
        this.fundingGoal = fundingGoal;
        this.images = new ArrayList<>();
        this.tags = new HashSet<>();
        this.donations = new HashSet<>();
    }



    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNrDonors() {
        return nrDonors;
    }

    public void setNrDonors(Integer nrDonors) {
        this.nrDonors = nrDonors;
    }

    public Double getCurrentFunds() {
        return currentFunds;
    }

    public void setCurrentFunds(Double currentFunds) {
        this.currentFunds = currentFunds;
    }

    public Double getFundingGoal() {
        return fundingGoal;
    }

    public void setFundingGoal(Double fundingGoal) {
        this.fundingGoal = fundingGoal;
    }

    public List<ImageData> getImages() {
        return new ArrayList<>(images);
    }

    public void setImages(List<ImageData> images) {
        this.images = images;
    }

    public Set<ProjectTag> getTags() {
        return new HashSet<>(tags);
    }

    public void setTags(Set<ProjectTag> tags) {
        this.tags = tags;
    }


    //New custom functions added below...

    public void addImage(ImageData imageData) {
        imageData.setDepictedProject(this);
        this.images.add(imageData);
    }

    public void removeImage(ImageData imageData) {
        this.images.remove(imageData);
        imageData.setDepictedProject(null);
    }

    public void addTag(ProjectTag tag) {
        tag.addProject(this);
        this.tags.add(tag);
    }

    public void removeTag(ProjectTag tag) {
        this.tags.remove(tag);
        tag.removeProject(this);
    }

    public void addDonation(@NotNull Donation donation) {
        if (donation.getProject() == null || donation.getProject().equals(this)) {
            if (donation.getProject() == null) {
                donation.setProject(this);
            }
            if ( this.donations.add(donation) ) {
                this.currentFunds += donation.getAmount();
            }
        }
    }

    public void addDonation(@NotNull Donation donation, boolean donatedBefore) {
        if (donation.getProject() == null || donation.getProject().equals(this)) {
            if (donation.getProject() == null) {
                donation.setProject(this);
            }
            if ( this.donations.add(donation) ) {
                if (!donatedBefore) { this.nrDonors += 1; }
                this.currentFunds += donation.getAmount();
            }
        }
    }

    public void removeDonation(@NotNull Donation donation) {
        if (donation.getProject().equals(this)) {
            if ( this.donations.remove(donation) ) {
                this.currentFunds -= donation.getAmount();
            }
        }
    }

    public void removeDonationById(long id) {
        Donation foundDonation = donations.stream()
                .filter(donation -> donation.getId() == id)
                .findFirst()
                .orElseThrow(
                        () -> {
                            throw new RuntimeException(
                                    "Could not find donation with id:" + id + " for Project:" + this.title
                            );
                        }
                );
        this.donations.remove(foundDonation);
    }
}

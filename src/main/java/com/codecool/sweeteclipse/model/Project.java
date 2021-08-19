package com.codecool.sweeteclipse.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ProjectStatus status;

    private String title;

    private String description;

    private Double fundingGoal;

    @OneToMany(mappedBy = "depictedProject", cascade = CascadeType.ALL)
    private List<ImageData> images;

    @ManyToMany
    @JoinTable(
            name = "project_tag",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private Set<ProjectTag> tags;

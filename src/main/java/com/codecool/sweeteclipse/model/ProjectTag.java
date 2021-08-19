package com.codecool.sweeteclipse.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProjectTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Project> projects;

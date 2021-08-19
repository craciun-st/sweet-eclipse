package com.codecool.sweeteclipse.model;

import javax.persistence.*;

@Entity
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uri;

    @ManyToOne
    @JoinColumn(name="project_id")  // value inside this column can be null (for now)
    private Project depictedProject;


package com.codecool.sweeteclipse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "image")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uri;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="project_id")  // value inside this column can be null (for now)
    private Project depictedProject;

    public ImageData() {
    }

    public ImageData(Long id, String uri, Project depictedProject) {
        this.id = id;
        this.uri = uri;
        this.depictedProject = depictedProject;
    }

    public ImageData(String uri, Project forProject) {
        this.uri = uri;
        this.depictedProject = forProject;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public Project getDepictedProject() {
        return depictedProject;
    }

    public void setDepictedProject(Project depictedProject) {
        this.depictedProject = depictedProject;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}

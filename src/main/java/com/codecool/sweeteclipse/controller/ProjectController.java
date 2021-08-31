package com.codecool.sweeteclipse.controller;

import com.codecool.sweeteclipse.model.Project;
import com.codecool.sweeteclipse.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
public class ProjectController {

    private ProjectRepository projectRepo;

    @Autowired
    public ProjectController(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Autowired


    @GetMapping("/api/projects")
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }
}

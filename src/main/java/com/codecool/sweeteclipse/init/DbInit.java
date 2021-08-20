package com.codecool.sweeteclipse.init;

import com.codecool.sweeteclipse.model.ImageData;
import com.codecool.sweeteclipse.model.Project;
import com.codecool.sweeteclipse.model.ProjectTag;
import com.codecool.sweeteclipse.repository.ImageDataRepository;
import com.codecool.sweeteclipse.repository.ProjectRepository;
import com.codecool.sweeteclipse.repository.ProjectTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInit implements CommandLineRunner {

    private ImageDataRepository imageRepo;
    private ProjectRepository projectRepo;
    private ProjectTagRepository tagRepo;

    @Autowired
    public DbInit(ImageDataRepository imageRepo, ProjectRepository projectRepo, ProjectTagRepository tagRepo) {
        this.imageRepo = imageRepo;
        this.projectRepo = projectRepo;
        this.tagRepo = tagRepo;
    }

    @Override
    public void run(String... args) throws Exception {
//        ImageData image1 = new ImageData("/image_0001.png", null);
//        ImageData image2 = new ImageData("/image_0002.png", null);
//        ImageData image3 = new ImageData("/image_0003.png", null);
//        ProjectTag tag1 = new ProjectTag("pets");
//        ProjectTag tag2 = new ProjectTag("environment");
//        ProjectTag tag3 = new ProjectTag("community");
//        Project strayDogProject = new Project(
//                "Save the stray dogs!",
//                "Collaborate to find a shelter for these misunderstood beings",
//                1000
//        );
//        Project riverCleanupProject = new Project(
//                "Help fund the biggest river cleanup effort!",
//                "The river X has too many junk floating in it! We need your help to finance a cleanup operation.",
//                2000
//        );
//
//
//        strayDogProject.addImage(image1);
//        strayDogProject.addImage(image2);
//        riverCleanupProject.addImage(image3);
//
//        strayDogProject.addTag(tag1);
//        riverCleanupProject.addTag(tag2);
//        riverCleanupProject.addTag(tag3);
//
//        projectRepo.saveAll(List.of(strayDogProject, riverCleanupProject));
//        imageRepo.saveAll(List.of(image1, image2, image3));
//        tagRepo.saveAll(List.of(tag1, tag2));

    }
}

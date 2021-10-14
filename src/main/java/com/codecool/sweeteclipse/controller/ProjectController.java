package com.codecool.sweeteclipse.controller;

import com.codecool.sweeteclipse.controller.exceptions.GenericInternalServerError;
import com.codecool.sweeteclipse.controller.exceptions.ObjectIdNotFoundException;
import com.codecool.sweeteclipse.controller.exceptions.ThirdPartyServiceException;
import com.codecool.sweeteclipse.model.Project;
import com.codecool.sweeteclipse.repository.ProjectRepository;
import com.codecool.sweeteclipse.service.ImageService;
import com.codecool.sweeteclipse.service.ImageServiceFacade;
import com.codecool.sweeteclipse.service.exceptions.ImproperFileException;
import com.codecool.sweeteclipse.service.exceptions.TempStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RestController
public class ProjectController {

    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Value("${spring.servlet.multipart.max-file-size}")
    private String MAX_UPLOAD_SIZE;

    private ProjectRepository projectRepo;

    private ImageServiceFacade imageService;

    @Autowired
    public ProjectController(ProjectRepository projectRepo, ImageService imageService) {
        this.projectRepo = projectRepo;
        this.imageService = imageService;
    }





    @GetMapping("/api/projects")
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    @GetMapping("/api/project/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectRepo.findById(id).orElseThrow(ObjectIdNotFoundException::new);
    }
    /**
     * Test endpoint for checking AWS upload
     * (Note: Images are tightly coupled to projects, so no need to have a separate controller)
     *
     * @param multipartFile a (image) file from a multipart form data
     * @return (as JSON) a map with key 'uri' and value the AWS S3 URL of the uploaded file (if successful)
     * @throws ThirdPartyServiceException
     * @throws ImproperFileException
     * @throws GenericInternalServerError
     */
    @PostMapping(
            value = "/api/dev_test/images",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("file") MultipartFile multipartFile)
            throws ThirdPartyServiceException, ImproperFileException, GenericInternalServerError
    {
        String uriString = "";
        try {
            uriString = imageService.getUriStringAfterSavingOrElseThrow(multipartFile, "test_upload");
        } catch (TempStorageException exception) {
            logger.error(exception.getMessage());
            throw new GenericInternalServerError();
        } catch (IOException exception) {
            logger.warn(exception.getMessage());
            throw new ThirdPartyServiceException();
        }

        URI createdImageLink = URI.create(uriString);
        Map<String, String> response = new HashMap<>();
        response.put("uri", createdImageLink.toASCIIString());
        return ResponseEntity.created(createdImageLink).body(response);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Map<String, String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("file", "File size too large! Should be less than " + MAX_UPLOAD_SIZE);
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImproperFileException.class)
    public Map<String, String> handleImproperFileException(ImproperFileException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("file", ex.getMessage());
        return response;
    }


}

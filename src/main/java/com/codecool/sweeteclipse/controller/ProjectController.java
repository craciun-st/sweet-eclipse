package com.codecool.sweeteclipse.controller;

import com.codecool.sweeteclipse.controller.exceptions.ObjectIdNotFoundException;
import com.codecool.sweeteclipse.model.Project;
import com.codecool.sweeteclipse.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RestController
public class ProjectController {

    Logger logger = LoggerFactory.getLogger(ProjectController.class);

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
}

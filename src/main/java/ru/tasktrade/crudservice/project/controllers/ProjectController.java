package ru.tasktrade.crudservice.project.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tasktrade.dtojar.project.change.ChangeProjectDTO;
import ru.tasktrade.dtojar.project.input.CreateProjectDTO;
import ru.tasktrade.dtojar.project.output.OutputProjectDTO;
import ru.tasktrade.crudservice.project.services.ProjectService;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<OutputProjectDTO>> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<List<OutputProjectDTO>> getAllPaginatedProjects(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                          @RequestParam(name = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(projectService.getAllPaginatedProjects(page, size), HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<OutputProjectDTO> getProjectById(@PathVariable("projectId") Long projectId) {
        return new ResponseEntity<>(projectService.getProjectById(projectId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OutputProjectDTO> createProject(@RequestBody CreateProjectDTO createProjectDTO) {
        return new ResponseEntity<>(projectService.createProject(createProjectDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<OutputProjectDTO> updateProjectById(@PathVariable("projectId") Long projectId, @RequestBody ChangeProjectDTO changeProjectDTO) {
        return new ResponseEntity<>(projectService.updateProjectById(projectId, changeProjectDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<String> deleteProjectById(@PathVariable("projectId") Long projectId) {
        return new ResponseEntity<>(projectService.deleteProjectById(projectId), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OutputProjectDTO>> getProjectsByCustomerId(@PathVariable("customerId") Long customerId) {
        return new ResponseEntity<>(projectService.getProjectsByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/paginated/customer/{customerId}")
    public ResponseEntity<List<OutputProjectDTO>> getPaginatedProjectsByCustomerId(@PathVariable("customerId") Long customerId,
                                                                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                                                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(projectService.getPaginatedProjectsByCustomerId(customerId, page, size), HttpStatus.OK);
    }

    @GetMapping("/producer/{producerId}")
    public ResponseEntity<List<OutputProjectDTO>> getProjectsByProducerId(@PathVariable("producerId") Long producerId) {
        return new ResponseEntity<>(projectService.getProjectsByProducerId(producerId), HttpStatus.OK);
    }

    @GetMapping("/paginated/producer/{producerId}")
    public ResponseEntity<List<OutputProjectDTO>> getPaginatedProjectsByProducerId(@PathVariable("producerId") Long producerId,
                                                                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                                                                   @RequestParam(name = "size", defaultValue = "10") int size) {
        return new ResponseEntity<>(projectService.getPaginatedProjectsByProducerId(producerId, page, size), HttpStatus.OK);
    }

//    @PutMapping("/{projectId}/status")
//    public ResponseEntity<OutputProjectDTO> changeProjectStatus(@PathVariable Long projectId, @RequestParam("status") String status) {
//        if (Arrays.asList(Status.values()).contains(Status.valueOf(status))) {
//            return new ResponseEntity<>(projectService.changeProjectStatus(projectId, status), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/start/{projectId}/producer/{producerId}")
    public ResponseEntity<OutputProjectDTO> startProject(@PathVariable("projectId") Long projectId, @PathVariable("producerId") Long producerId) {
        return new ResponseEntity<>(projectService.startProject(projectId, producerId), HttpStatus.OK);
    }

    @PostMapping("/cancel/{projectId}")
    public ResponseEntity<OutputProjectDTO> cancelProject(@PathVariable("projectId") Long projectId) {
        return new ResponseEntity<>(projectService.cancelProject(projectId), HttpStatus.OK);
    }

    @PostMapping("/finish/{projectId}")
    public ResponseEntity<OutputProjectDTO> finishProject(@PathVariable("projectId") Long projectId) {
        return new ResponseEntity<>(projectService.finishProject(projectId), HttpStatus.OK);
    }

}

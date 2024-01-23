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
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<OutputProjectDTO>> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
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

}

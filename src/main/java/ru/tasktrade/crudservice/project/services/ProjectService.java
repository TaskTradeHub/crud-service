package ru.tasktrade.crudservice.project.services;

import ru.tasktrade.dtojar.project.change.ChangeProjectDTO;
import ru.tasktrade.dtojar.project.input.CreateProjectDTO;
import ru.tasktrade.dtojar.project.output.OutputProjectDTO;

import java.util.List;

public interface ProjectService {
    List<OutputProjectDTO> getAllProjects();

    OutputProjectDTO getProjectById(Long projectId);

    OutputProjectDTO createProject(CreateProjectDTO createProjectDTO);

    OutputProjectDTO updateProjectById(Long projectId, ChangeProjectDTO changeProjectDTO);

    String deleteProjectById(Long projectId);
}

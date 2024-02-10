package ru.tasktrade.crudservice.project.services;

import ru.tasktrade.crudservice.project.enums.Status;
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

    List<OutputProjectDTO> getAllPaginatedProjects(int page, int size);

    List<OutputProjectDTO> getProjectsByCustomerId(Long customerId);

    List<OutputProjectDTO> getPaginatedProjectsByCustomerId(Long customerId, int page, int size);

    List<OutputProjectDTO> getProjectsByProducerId(Long producerId);

//    OutputProjectDTO changeProjectStatus(Long projectId, String status);

    List<OutputProjectDTO> getPaginatedProjectsByProducerId(Long producerId, int page, int size);

    OutputProjectDTO startProject(Long projectId, Long producerId);

    OutputProjectDTO cancelProject(Long projectId);

    OutputProjectDTO finishProject(Long projectId);
}

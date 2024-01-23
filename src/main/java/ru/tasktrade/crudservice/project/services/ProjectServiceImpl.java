package ru.tasktrade.crudservice.project.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasktrade.crudservice.project.entities.Project;
import ru.tasktrade.crudservice.project.exceptions.ProjectNotFoundException;
import ru.tasktrade.crudservice.project.mappers.input.ProjectInputMapper;
import ru.tasktrade.crudservice.project.mappers.output.ProjectOutputMapper;
import ru.tasktrade.crudservice.project.repositories.ProjectRepository;
import ru.tasktrade.dtojar.project.change.ChangeProjectDTO;
import ru.tasktrade.dtojar.project.input.CreateProjectDTO;
import ru.tasktrade.dtojar.project.output.OutputProjectDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectOutputMapper projectOutputMapper;
    private final ProjectInputMapper projectInputMapper;

    @Override
    public List<OutputProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectOutputMapper::toDTO).toList();
    }

    @Override
    public OutputProjectDTO getProjectById(Long projectId) {

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new ProjectNotFoundException("There is no project with id: " + projectId)
        );

        return projectOutputMapper.toDTO(project);

    }

    @Override
    public OutputProjectDTO createProject(CreateProjectDTO createProjectDTO) {

        projectRepository.save(projectInputMapper.toProject(createProjectDTO));

        return projectOutputMapper.toDTO(projectInputMapper.toProject(createProjectDTO));
    }

    @Override
    public OutputProjectDTO updateProjectById(Long projectId, ChangeProjectDTO changeProjectDTO) {

        OutputProjectDTO projectDB = getProjectById(projectId);

        String name = changeProjectDTO.getName();
        String description = changeProjectDTO.getDescription();
        String category = changeProjectDTO.getCategory();
        Double budget = changeProjectDTO.getBudget();
        Integer timeframe = changeProjectDTO.getTimeframe();
        LocalDateTime endDate = changeProjectDTO.getEndDate();

        if (Objects.nonNull(name) && !"".equalsIgnoreCase(name)) {
            projectDB.setName(name);
        }

        if (Objects.nonNull(description) && !"".equalsIgnoreCase(description)) {
            projectDB.setDescription(description);
        }

        if (Objects.nonNull(category) && !"".equalsIgnoreCase(category)) {
            projectDB.setCategory(category);
        }

        if (Objects.nonNull(budget) && budget > 0) {
            projectDB.setBudget(budget);
        }

        if (Objects.nonNull(timeframe) && timeframe > 0) {
            projectDB.setTimeframe(timeframe);
        }

        if (Objects.nonNull(endDate)) {
            projectDB.setEndDate(endDate);
        }

        projectRepository.save(projectOutputMapper.toProject(projectDB));

        return projectDB;
    }

    @Override
    public String deleteProjectById(Long projectId) {

        if (projectRepository.findById(projectId).isEmpty()) {
            throw new ProjectNotFoundException("There is no project with id: " + projectId);
        }

        projectRepository.deleteById(projectId);

        return "successful deleted";

    }
}

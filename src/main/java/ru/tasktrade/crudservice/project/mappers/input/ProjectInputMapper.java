package ru.tasktrade.crudservice.project.mappers.input;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tasktrade.dtojar.project.input.CreateProjectDTO;
import ru.tasktrade.crudservice.project.entities.Project;

@Component
@RequiredArgsConstructor
public class ProjectInputMapper {

    public CreateProjectDTO toDTO(Project project) {
        return CreateProjectDTO.builder()
                .name(project.getName())
                .description(project.getDescription())
                .category(project.getCategory())
                .timeframe(project.getTimeframe())
                .budget(project.getBudget())
                .build();
    }

    public Project toProject(CreateProjectDTO createProjectDTO) {
        return Project.builder()
                .name(createProjectDTO.getName())
                .description(createProjectDTO.getDescription())
                .category(createProjectDTO.getCategory())
                .timeframe(createProjectDTO.getTimeframe())
                .budget(createProjectDTO.getBudget())
                .build();
    }

}

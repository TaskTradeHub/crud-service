package ru.tasktrade.crudservice.project.mappers.output;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tasktrade.crudservice.project.enums.Status;
import ru.tasktrade.dtojar.project.output.OutputProjectDTO;
import ru.tasktrade.crudservice.project.entities.Project;

@Component
@RequiredArgsConstructor
public class ProjectOutputMapper {

    public OutputProjectDTO toDTO(Project project) {
        return OutputProjectDTO.builder()
                .Id(project.getId())
                .budget(project.getBudget())
                .category(project.getCategory())
                .creationDate(project.getCreationDate())
                .customerId(project.getCustomerId())
                .description(project.getDescription())
                .endDate(project.getEndDate())
                .producerId(project.getProducerId())
                .startedDate(project.getStartedDate())
                .status(project.getStatus().name())
                .timeframe(project.getTimeframe())
                .build();
    }

    public Project toProject(OutputProjectDTO outputProjectDTO) {
        return Project.builder()
                .id(outputProjectDTO.getId())
                .budget(outputProjectDTO.getBudget())
                .category(outputProjectDTO.getCategory())
                .creationDate(outputProjectDTO.getCreationDate())
                .customerId(outputProjectDTO.getCustomerId())
                .description(outputProjectDTO.getDescription())
                .endDate(outputProjectDTO.getEndDate())
                .producerId(outputProjectDTO.getProducerId())
                .startedDate(outputProjectDTO.getStartedDate())
                .status(Status.valueOf(outputProjectDTO.getStatus()))
                .timeframe(outputProjectDTO.getTimeframe())
                .build();
    }

}


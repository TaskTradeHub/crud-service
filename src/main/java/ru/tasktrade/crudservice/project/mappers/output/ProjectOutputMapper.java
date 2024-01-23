package ru.tasktrade.crudservice.project.mappers.output;

import org.mapstruct.Mapper;
import ru.tasktrade.dtojar.project.output.OutputProjectDTO;
import ru.tasktrade.crudservice.project.entities.Project;

@Mapper(componentModel = "spring")
public interface ProjectOutputMapper {

    OutputProjectDTO toDTO(Project project);

    Project toProject(OutputProjectDTO outputProjectDTO);

}

package ru.tasktrade.crudservice.project.mappers.input;

import org.mapstruct.Mapper;
import ru.tasktrade.dtojar.project.input.CreateProjectDTO;
import ru.tasktrade.crudservice.project.entities.Project;

@Mapper(componentModel = "spring")
public interface ProjectInputMapper {

    CreateProjectDTO toDTO(Project project);

    Project toProject(CreateProjectDTO createProjectDTO);

}

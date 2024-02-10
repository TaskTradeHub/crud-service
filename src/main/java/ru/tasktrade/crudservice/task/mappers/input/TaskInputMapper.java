package ru.tasktrade.crudservice.task.mappers.input;

import org.mapstruct.Mapper;
import ru.tasktrade.crudservice.task.entities.Task;
import ru.tasktrade.dtojar.task.input.CreateTaskDTO;

@Mapper(componentModel = "spring")
public interface TaskInputMapper {
    CreateTaskDTO toDTO(Task task);

    Task toTask(CreateTaskDTO createTaskDTO);
}

package ru.tasktrade.crudservice.task.mappers.output;

import org.mapstruct.Mapper;
import ru.tasktrade.crudservice.task.entities.Task;
import ru.tasktrade.dtojar.task.output.OutputTaskDTO;

@Mapper(componentModel = "spring")
public interface TaskOutMapper {

    OutputTaskDTO toDTO(Task task);
    Task toTask(OutputTaskDTO outputTaskDTO);
}

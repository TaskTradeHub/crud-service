package ru.tasktrade.crudservice.task.services;

import ru.tasktrade.dtojar.task.change.ChangeTaskDTO;
import ru.tasktrade.dtojar.task.input.CreateTaskDTO;
import ru.tasktrade.dtojar.task.output.OutputTaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    OutputTaskDTO createTask(CreateTaskDTO createTaskDTO);
    OutputTaskDTO updateTask(ChangeTaskDTO changeTaskDTO, Long taskId);
    String deleteTask(Long id);
    OutputTaskDTO getTaskById(Long id);
    List<OutputTaskDTO> getAllTasksByProjectId(Long id);
}

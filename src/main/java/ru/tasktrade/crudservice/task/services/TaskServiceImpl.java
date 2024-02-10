package ru.tasktrade.crudservice.task.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tasktrade.crudservice.project.entities.Project;
import ru.tasktrade.crudservice.project.exceptions.ProjectNotFoundException;
import ru.tasktrade.crudservice.task.entities.Task;
import ru.tasktrade.crudservice.task.exceptions.TaskNotFoundException;
import ru.tasktrade.crudservice.task.mappers.input.TaskInputMapper;
import ru.tasktrade.crudservice.task.mappers.output.TaskOutMapper;
import ru.tasktrade.crudservice.task.repositories.TaskRepository;
import ru.tasktrade.dtojar.task.change.ChangeTaskDTO;
import ru.tasktrade.dtojar.task.input.CreateTaskDTO;
import ru.tasktrade.dtojar.task.output.OutputTaskDTO;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private TaskRepository taskRepository;
    private TaskInputMapper taskInputMapper;
    private TaskOutMapper taskOutMapper;
    @Override
    public OutputTaskDTO createTask(CreateTaskDTO createTaskDTO) {
        taskRepository.save(taskInputMapper.toTask(createTaskDTO));
        return taskOutMapper.toDTO(taskInputMapper.toTask(createTaskDTO));
    }

    @Override
    public OutputTaskDTO updateTask(ChangeTaskDTO changeTaskDTO, Long id) {
        OutputTaskDTO task = getTaskById(id);

        String name = changeTaskDTO.getName();
        String description = changeTaskDTO.getDescription();

        if (Objects.nonNull(name) && !"".equalsIgnoreCase(name)) {
            task.setName(name);
        }

        if (Objects.nonNull(description) && !"".equalsIgnoreCase(description)) {
            task.setDescription(description);
        }

        taskRepository.save(taskOutMapper.toTask(task));
        return task;
    }

    @Override
    public String deleteTask(Long id) {
        if (taskRepository.findById(id).isEmpty()) {
            throw new TaskNotFoundException("No task with id " + id);
        }
        taskRepository.deleteById(id);
        return "Success";

    }

    @Override
    public OutputTaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ProjectNotFoundException("No task with id " + id)
        );
        return taskOutMapper.toDTO(task);
    }

    @Override
    public List<OutputTaskDTO> getAllTasksByProjectId(Long id) {
        return null;
    }
}

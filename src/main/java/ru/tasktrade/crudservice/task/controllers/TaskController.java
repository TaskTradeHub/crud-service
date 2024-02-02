package ru.tasktrade.crudservice.task.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tasktrade.crudservice.task.entities.Task;
import ru.tasktrade.crudservice.task.services.TaskServiceImpl;
import ru.tasktrade.dtojar.task.change.ChangeTaskDTO;
import ru.tasktrade.dtojar.task.input.CreateTaskDTO;
import ru.tasktrade.dtojar.task.output.OutputTaskDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private TaskServiceImpl taskService;

    @PostMapping
    public ResponseEntity<OutputTaskDTO> createTask(@RequestBody CreateTaskDTO createTaskDTO) {
        return new ResponseEntity<>(taskService.createTask(createTaskDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputTaskDTO> getTask(@PathVariable("id") Long id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        return new ResponseEntity<>(taskService.deleteTask(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutputTaskDTO> changeTask(@RequestBody ChangeTaskDTO changeTaskDTO, @PathVariable Long id) {
        return new ResponseEntity<>(taskService.updateTask(changeTaskDTO, id), HttpStatus.OK);
    }
}

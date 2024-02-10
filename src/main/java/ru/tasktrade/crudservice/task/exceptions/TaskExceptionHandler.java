package ru.tasktrade.crudservice.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.tasktrade.crudservice.project.exceptions.ProjectException;
import ru.tasktrade.crudservice.project.exceptions.ProjectNotFoundException;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(value = {TaskNotFoundException.class})
    public ResponseEntity<Object> handleTaskNotFoundException(TaskNotFoundException taskNotFoundException) {

        TaskException taskException = new TaskException(
                taskNotFoundException.getMessage(),
                taskNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(taskException, taskException.getHttpStatus());

    }
}

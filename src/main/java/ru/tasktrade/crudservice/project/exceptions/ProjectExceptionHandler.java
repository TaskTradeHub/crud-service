package ru.tasktrade.crudservice.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectExceptionHandler {

    @ExceptionHandler(value = {ProjectNotFoundException.class})
    public ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException projectNotFoundException) {

        ProjectException projectException = new ProjectException(
                projectNotFoundException.getMessage(),
                projectNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(projectException, projectException.getHttpStatus());

    }

}

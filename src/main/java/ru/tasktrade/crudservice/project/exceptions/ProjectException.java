package ru.tasktrade.crudservice.project.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ProjectException {

    private final String message;

    private final Throwable throwable;

    private final HttpStatus httpStatus;

}

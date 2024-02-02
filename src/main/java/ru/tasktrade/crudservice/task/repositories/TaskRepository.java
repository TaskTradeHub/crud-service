package ru.tasktrade.crudservice.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tasktrade.crudservice.task.entities.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTasksByUserId(Long id);
    List<Task> findTasksByProjectId(Long id);
}

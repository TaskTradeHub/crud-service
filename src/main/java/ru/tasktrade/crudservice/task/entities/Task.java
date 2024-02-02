package ru.tasktrade.crudservice.task.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tasktrade.crudservice.project.entities.Project;
import ru.tasktrade.crudservice.task.enums.Status;

import java.util.Stack;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "task")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(name = "project")
    @ManyToOne
    Project project;
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

}

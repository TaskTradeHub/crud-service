package ru.tasktrade.crudservice.project.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.tasktrade.crudservice.project.entities.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findProjectsByCustomerId(Long customerId);

    List<Project> findProjectsByProducerId(Long producerId);

    List<Project> findProjectsByCustomerId(Long customerId, Pageable pageable);

    List<Project> findProjectsByProducerId(Long producerId, Pageable pageable);


}

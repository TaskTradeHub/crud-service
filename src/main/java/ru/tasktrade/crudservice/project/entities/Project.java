package ru.tasktrade.crudservice.project.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import ru.tasktrade.crudservice.project.enums.Status;
import ru.tasktrade.dtojar.user.output.OutputUserDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "budget")
    private Double budget;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "timeframe")
    private Integer timeframe;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "startedDate")
    private LocalDateTime startedDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "producer_id")
    private Long producerId;

    @Transient
    private List<OutputUserDTO> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Project project = (Project) o;
        return id != null && Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @PrePersist
    public void setDefaultStatus() {

        if (this.status == null) {
            this.status = Status.WAITING; // установка дефолтного значения
        }

        if (this.creationDate == null) {
            this.creationDate = LocalDateTime.now(ZoneId.of("Europe/Saratov")); // установка дефолтного значения
        }

        if (this.budget == null) {
            this.budget = 0.0; // установка дефолтного значения
        }

        if (this.timeframe == null) {
            this.timeframe = 0; // установка дефолтного значения
        }

    }

}

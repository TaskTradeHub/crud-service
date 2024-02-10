package ru.tasktrade.crudservice.project.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tasktrade.crudservice.project.entities.Project;
import ru.tasktrade.crudservice.project.enums.Status;
import ru.tasktrade.crudservice.project.exceptions.ProjectNotFoundException;
import ru.tasktrade.crudservice.project.mappers.input.ProjectInputMapper;
import ru.tasktrade.crudservice.project.mappers.output.ProjectOutputMapper;
import ru.tasktrade.crudservice.project.repositories.ProjectRepository;
import ru.tasktrade.dtojar.project.change.ChangeProjectDTO;
import ru.tasktrade.dtojar.project.input.CreateProjectDTO;
import ru.tasktrade.dtojar.project.output.OutputProjectDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectOutputMapper projectOutputMapper;
    private final ProjectInputMapper projectInputMapper;
    private final WebClient webUserClient;

    @Override
    public List<OutputProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectOutputMapper::toDTO).toList();
    }

    @Override
    public List<OutputProjectDTO> getAllPaginatedProjects(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return projectRepository.findAll(pageable).stream()
                .map(projectOutputMapper::toDTO).toList();
    }

    @Override
    public List<OutputProjectDTO> getProjectsByCustomerId(Long customerId) {
        return projectRepository.findProjectsByCustomerId(customerId).stream()
                .map(projectOutputMapper::toDTO).toList();
    }

    @Override
    public List<OutputProjectDTO> getPaginatedProjectsByCustomerId(Long customerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return projectRepository.findProjectsByCustomerId(customerId, pageable).stream()
                .map(projectOutputMapper::toDTO).toList();
    }

    @Override
    public List<OutputProjectDTO> getProjectsByProducerId(Long producerId) {
        return projectRepository.findProjectsByProducerId(producerId).stream()
                .map(projectOutputMapper::toDTO).toList();
    }

    @Override
    public List<OutputProjectDTO> getPaginatedProjectsByProducerId(Long producerId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return projectRepository.findProjectsByProducerId(producerId, pageable).stream()
                .map(projectOutputMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public OutputProjectDTO startProject(Long projectId, Long producerId) {

//        OutputProjectDTO projectDTO = getProjectById(projectId);
//        UserDTO producer = getUserById(producerId);
//
//        LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("Europe/Saratov"));
//
//        projectDTO.setStartedDate(currentDateTime);
//        changeProjectStatus(projectId, Status.WAITING);
//        projectDTO.setProducerId(producerId);
//
//        if (projectDTO.getTimeframe() != null && projectDTO.getTimeframe() > 0 && projectDTO.getEndDate() == null) {
//            projectDTO.setEndDate(currentDateTime.plusDays(projectDTO.getTimeframe()));
//        }
//
//        producer.setAccountStatus(AccountStatus.WORKING);
//
//        userService.updateUserById(producerId, producer); //TODO переделать с помощью webClient
//        userService.addUserProjects(projectDTO.getCustomerId(), projectId);
//        userService.addUserProjects(producerId, projectId);
//
//        //TODO установить предложенную исполнителем стоимость работы
//        //project.setBudget();
//
//        projectRepository.save(projectOutputMapper.toProject(projectDTO));
//
//        return projectDTO;

        return null;
    }

    @Override
    @Transactional
    public OutputProjectDTO cancelProject(Long projectId) {
        return null;
    }

    @Override
    @Transactional
    public OutputProjectDTO finishProject(Long projectId) {

//        OutputProjectDTO projectDTO = getProjectById(projectId);
//
//        UserDTO producer = getUserById(projectDTO.getProducerId());
//        UserDTO customer = getUserById(projectDTO.getCustomerId());
//
//        projectDTO.setEndDate(LocalDateTime.now(ZoneId.of("Europe/Saratov")));
//        changeProjectStatus(projectId, Status.FINISHED);
//
//        updateProducerAccount(producer, projectDTO);
//        updateCustomerBalance(customer, projectDTO);
//
//        projectRepository.save(projectOutputMapper.toProject(projectDTO));
//
//        userService.updateUserById(projectId, producer); //TODO переделать с помощью webClient
//        userService.updateUserById(customer.getId(), customer);
//
//        return projectDTO;

        return null;

    }

    @Override
    public OutputProjectDTO getProjectById(Long projectId) {

        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new ProjectNotFoundException("There is no project with id: " + projectId)
        );

        return projectOutputMapper.toDTO(project);

    }

    @Override
    public OutputProjectDTO createProject(CreateProjectDTO createProjectDTO) {

        Project project = projectInputMapper.toProject(createProjectDTO);
        projectRepository.save(project);

        return projectOutputMapper.toDTO(project);
    }

    @Override
    public OutputProjectDTO updateProjectById(Long projectId, ChangeProjectDTO changeProjectDTO) {

        OutputProjectDTO projectDB = getProjectById(projectId);

        String name = changeProjectDTO.getName();
        String description = changeProjectDTO.getDescription();
        String category = changeProjectDTO.getCategory();
        Double budget = changeProjectDTO.getBudget();
        Integer timeframe = changeProjectDTO.getTimeframe();
        LocalDateTime endDate = changeProjectDTO.getEndDate();

        if (Objects.nonNull(name) && !"".equalsIgnoreCase(name)) {
            projectDB.setName(name);
        }

        if (Objects.nonNull(description) && !"".equalsIgnoreCase(description)) {
            projectDB.setDescription(description);
        }

        if (Objects.nonNull(category) && !"".equalsIgnoreCase(category)) {
            projectDB.setCategory(category);
        }

        if (Objects.nonNull(budget) && budget > 0) {
            projectDB.setBudget(budget);
        }

        if (Objects.nonNull(timeframe) && timeframe > 0) {
            projectDB.setTimeframe(timeframe);
        }

        if (Objects.nonNull(endDate)) {
            projectDB.setEndDate(endDate);
        }

        projectRepository.save(projectOutputMapper.toProject(projectDB));

        return projectDB;
    }

    @Override
    public String deleteProjectById(Long projectId) {

        if (projectRepository.findById(projectId).isEmpty()) {
            throw new ProjectNotFoundException("There is no project with id: " + projectId);
        }

        projectRepository.deleteById(projectId);

        return "successful deleted";

    }

    private void changeProjectStatus(Long projectId, Status status) {

        if (Arrays.asList(Status.values()).contains(status)) {
            OutputProjectDTO projectDB = getProjectById(projectId);
            projectDB.setStatus(status.name());
            projectRepository.save(projectOutputMapper.toProject(projectDB));
        } else {
            throw new RuntimeException("There is no status with name:" + status);
        }

    }

//    private void updateProducerAccount(UserDTO producer, OutputProjectDTO project) {
//        producer.setAccountStatus(AccountStatus.FREE);
//        producer.setCompletedProjects(producer.getCompletedProjects() + 1);
//        producer.setCurrentRating(producer.getCurrentRating() + 100);
//        producer.setBalance(producer.getBalance() + (project.getBudget() * 0.9));
//    }
//
//    private void updateCustomerBalance(UserDTO customer, OutputProjectDTO project) {
//        customer.setBalance(customer.getBalance() - project.getBudget());
//    }
//
//    private UserDTO getUserById(Long userId) {
//        return webUserClient
//                .get()
//                .uri(String.valueOf(userId))
//                .retrieve()
//                .bodyToMono(UserDTO.class)
//                .block();
//    }

}

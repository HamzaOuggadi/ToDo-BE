package net.hamzaouggadi.todobe.service.impl;

import lombok.RequiredArgsConstructor;
import net.hamzaouggadi.todobe.dtos.TaskDTO;
import net.hamzaouggadi.todobe.entities.Task;
import net.hamzaouggadi.todobe.enums.Status;
import net.hamzaouggadi.todobe.exceptions.AppUserException;
import net.hamzaouggadi.todobe.exceptions.TaskException;
import net.hamzaouggadi.todobe.mappers.TaskMapper;
import net.hamzaouggadi.todobe.repository.TaskRepository;
import net.hamzaouggadi.todobe.service.AppUserService;
import net.hamzaouggadi.todobe.service.TaskService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private final AppUserService appUserService;

    private final MessageSource messageSource;


    @Override
    public TaskDTO getTaskById(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            return taskMapper.toTaskDTO(taskOptional.get());
        } else {
            throw new TaskException(
                    messageSource.getMessage(
                            "no.task.found.id",
                            new Object[]{taskId},
                            Locale.getDefault()
                    ),
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<TaskDTO> getAllTasksByUserEmail(String email) {
        if (appUserService.checkIfUserExistsByEmail(email)) {
            List<Task> tasks = taskRepository.findAllByToDoListAppUserEmailIgnoreCase(email.toLowerCase());
            if (!tasks.isEmpty()) {
                List<TaskDTO> taskDTOS = new ArrayList<>();
                tasks.forEach(task -> taskDTOS.add(taskMapper.toTaskDTO(task)));
                return taskDTOS;
            } else {
                throw new TaskException(
                        messageSource.getMessage(
                                "no.task.found",
                                new Object[]{},
                                Locale.getDefault()
                        ),
                        HttpStatus.NOT_FOUND
                );
            }
        } else {
            throw new AppUserException();
        }
    }

    @Override
    public List<TaskDTO> getAllTasksByTodoListId(Long todoListId) {
        List<Task> tasks = taskRepository.findAllByToDoListId(todoListId);
        if (!tasks.isEmpty()) {
            List<TaskDTO> taskDTOS = new ArrayList<>();
            tasks.forEach(task -> taskDTOS.add(taskMapper.toTaskDTO(task)));
            return taskDTOS;
        } else {
            throw new TaskException(
                    messageSource.getMessage(
                            "no.task.found",
                            new Object[]{},
                            Locale.getDefault()
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void createTask(TaskDTO taskDTO) {
        Task task = taskMapper.toTask(taskDTO);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(TaskDTO taskDTO) {
        Task task = taskMapper.toTask(taskDTO);
        taskRepository.save(task);
    }

    @Override
    public void setTaskToDoneById(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setStatus(Status.DONE);
        } else {
            throw new TaskException(
                    messageSource.getMessage(
                            "no.task.found",
                            new Object[]{},
                            Locale.getDefault()
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}

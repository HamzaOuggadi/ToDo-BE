package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.dtos.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO getTaskById(Long taskId);
    List<TaskDTO> getAllTasksByUserEmail(String email);
    List<TaskDTO> getAllTasksByTodoListId(Long todoListId);
    void createTask(TaskDTO taskDTO);
    void updateTask(TaskDTO taskDTO);
    void setTaskToDoneById(Long taskId);
    void deleteTask(Long taskId);

}

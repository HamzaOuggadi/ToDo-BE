package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.entities.Task;

import java.util.List;

public interface TaskService {

    Task getTaskByCode(String code);
    Task getTaskById(Long id);
    List<Task> getTasksByUserEmail(String email);
    List<Task> getTasksByTodoListId(Long todoListId);
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(String code);
}

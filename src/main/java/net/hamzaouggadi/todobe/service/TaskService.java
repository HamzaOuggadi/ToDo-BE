package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.entities.Task;

public interface TaskService {

    Task getTaskByCode(String code);
    Task getTaskById(Long id);
    Task createTask(Task task);
    Task updateTask(Task task);
    void deleteTask(String code);
}

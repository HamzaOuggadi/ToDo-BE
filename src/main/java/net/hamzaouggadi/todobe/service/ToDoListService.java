package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.dtos.ToDoListDTO;

import java.util.List;

public interface ToDoListService {

    ToDoListDTO getTodoListById(Long todoListId);
    List<ToDoListDTO> getTodoListsByUserEmail(String email);
    void createTodoList(ToDoListDTO toDoListDTO);
    void updateTodoList(ToDoListDTO toDoListDTO);
    void deleteTodoList(Long todoListId);

}

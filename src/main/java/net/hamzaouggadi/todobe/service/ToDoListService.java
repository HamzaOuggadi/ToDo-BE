package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.entities.ToDoList;

import java.util.List;

public interface ToDoListService {

    ToDoList getTodoListByCode(String code);
    ToDoList getTodoListById(Long id);
    List<ToDoList> getTodoListsByUserEmail(String email);
    ToDoList createTodoList(ToDoList toDoList);
    ToDoList updateTodoList(ToDoList toDoList);
    void deleteTodoList(String code);
}

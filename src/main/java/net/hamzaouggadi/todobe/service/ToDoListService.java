package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.entities.ToDoList;

public interface ToDoListService {

    ToDoList getTodoListByCode(String code);
    ToDoList getTodoListById(Long id);
    ToDoList createTodoList(ToDoList toDoList);
    ToDoList updateTodoList(ToDoList toDoList);
    void deleteTodoList(String code);
}

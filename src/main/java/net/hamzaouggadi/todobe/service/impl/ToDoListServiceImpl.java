package net.hamzaouggadi.todobe.service.impl;

import lombok.RequiredArgsConstructor;
import net.hamzaouggadi.todobe.dtos.ToDoListDTO;
import net.hamzaouggadi.todobe.entities.ToDoList;
import net.hamzaouggadi.todobe.mappers.ToDoListMapper;
import net.hamzaouggadi.todobe.repository.ToDoListRepository;
import net.hamzaouggadi.todobe.service.ToDoListService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ToDoListServiceImpl implements ToDoListService {

    private final ToDoListRepository toDoListRepository;
    private final ToDoListMapper toDoListMapper;
    private final MessageSource messageSource;


    @Override
    public ToDoListDTO getTodoListById(Long todoListId) {
        return toDoListMapper.toToDoListDTO(toDoListRepository.findById(todoListId).orElseThrow());
    }

    @Override
    public List<ToDoListDTO> getTodoListsByUserEmail(String email) {
        List<ToDoList> toDoLists = toDoListRepository.findAllByAppUserEmail(email);
        List<ToDoListDTO> toDoListDTOS = new ArrayList<>();
        toDoLists.forEach(toDoList -> toDoListDTOS.add(toDoListMapper.toToDoListDTO(toDoList)));
        return toDoListDTOS;
    }

    @Override
    public void createTodoList(ToDoListDTO toDoListDTO) {
        ToDoList toDoList = toDoListMapper.toToDoList(toDoListDTO);
        toDoListRepository.save(toDoList);
    }

    @Override
    public void updateTodoList(ToDoListDTO toDoListDTO) {
        ToDoList toDoList = toDoListMapper.toToDoList(toDoListDTO);
        toDoListRepository.save(toDoList);
    }

    @Override
    public void deleteTodoList(Long todoListId) {
        toDoListRepository.deleteById(todoListId);
    }
}

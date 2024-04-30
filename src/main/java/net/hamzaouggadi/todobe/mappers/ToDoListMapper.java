package net.hamzaouggadi.todobe.mappers;


import net.hamzaouggadi.todobe.dtos.ToDoListDTO;
import net.hamzaouggadi.todobe.entities.ToDoList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface ToDoListMapper {

    @Mapping(source = "appUser.email", target = "userEmail")
    ToDoListDTO toToDoListDTO(ToDoList toDoList);
    @Mapping(source = "userEmail", target = "appUser.email")
    ToDoList toToDoList(ToDoListDTO toDoListDTO);
}

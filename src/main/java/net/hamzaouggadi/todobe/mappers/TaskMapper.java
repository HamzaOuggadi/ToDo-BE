package net.hamzaouggadi.todobe.mappers;


import net.hamzaouggadi.todobe.dtos.TaskDTO;
import net.hamzaouggadi.todobe.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "toDoList.code", target = "toDoListCode")
    TaskDTO toTaskDTO(Task task);
    @Mapping(source = "toDoListCode", target = "toDoList.code")
    Task toTask(TaskDTO taskDTO);
}

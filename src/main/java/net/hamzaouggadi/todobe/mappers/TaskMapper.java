package net.hamzaouggadi.todobe.mappers;


import net.hamzaouggadi.todobe.dtos.TaskDTO;
import net.hamzaouggadi.todobe.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(source = "toDoList.id", target = "toDoListId")
    TaskDTO toTaskDTO(Task task);
    @Mapping(source = "toDoListId", target = "toDoList.id")
    Task toTask(TaskDTO taskDTO);
}

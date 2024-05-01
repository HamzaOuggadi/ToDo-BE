package net.hamzaouggadi.todobe.dtos;

import net.hamzaouggadi.todobe.enums.Status;

import java.util.List;

public record ToDoListDTO(String id,
                          String name,
                          Status status,
                          List<TaskDTO> taskDTOS,
                          String userEmail) {
}

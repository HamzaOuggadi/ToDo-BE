package net.hamzaouggadi.todobe.dtos;

import net.hamzaouggadi.todobe.enums.Status;

import java.time.LocalDateTime;

public record TaskDTO(String code,
                      String title,
                      String description,
                      Status status,
                      LocalDateTime createdAt,
                      LocalDateTime dueDate,
                      String toDoListCode) {
}

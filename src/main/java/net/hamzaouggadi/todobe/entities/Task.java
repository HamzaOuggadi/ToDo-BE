package net.hamzaouggadi.todobe.entities;

import jakarta.persistence.*;
import lombok.*;
import net.hamzaouggadi.todobe.enums.Status;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime dueDate;
    @ManyToOne
    private ToDoList toDoList;
}

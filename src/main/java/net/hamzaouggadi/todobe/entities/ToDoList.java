package net.hamzaouggadi.todobe.entities;

import jakarta.persistence.*;
import lombok.*;
import net.hamzaouggadi.todobe.enums.Status;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ToDoList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private Status status;
    @OneToMany(mappedBy = "toDoList")
    private List<Task> tasks;
    @ManyToOne
    private AppUser appUser;
}

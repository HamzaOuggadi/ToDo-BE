package net.hamzaouggadi.todobe.entities;

import jakarta.persistence.*;
import lombok.*;
import net.hamzaouggadi.todobe.enums.AppUserType;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime registrationDate;
    private String profilePicture;
    @Enumerated(EnumType.STRING)
    private AppUserType appUserType;
    @OneToMany(mappedBy = "appUser")
    private List<ToDoList> toDoLists;
    @OneToMany(mappedBy = "appUser")
    private List<Post> posts;
}

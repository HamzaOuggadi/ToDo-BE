package net.hamzaouggadi.todobe.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private String content;
    private String pictureURL;
    private LocalDateTime publishedAt;
    @ManyToOne
    private AppUser appUser;
}

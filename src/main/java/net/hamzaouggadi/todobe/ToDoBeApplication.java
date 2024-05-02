package net.hamzaouggadi.todobe;

import com.github.javafaker.Faker;
import net.hamzaouggadi.todobe.dtos.PostDTO;
import net.hamzaouggadi.todobe.entities.AppUser;
import net.hamzaouggadi.todobe.entities.Post;
import net.hamzaouggadi.todobe.entities.Task;
import net.hamzaouggadi.todobe.entities.ToDoList;
import net.hamzaouggadi.todobe.enums.AppUserType;
import net.hamzaouggadi.todobe.enums.Status;
import net.hamzaouggadi.todobe.mappers.PostMapper;
import net.hamzaouggadi.todobe.repository.AppUserRepository;
import net.hamzaouggadi.todobe.repository.PostRepository;
import net.hamzaouggadi.todobe.repository.TaskRepository;
import net.hamzaouggadi.todobe.repository.ToDoListRepository;
import net.hamzaouggadi.todobe.service.PostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class ToDoBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoBeApplication.class, args);
    }


    @Bean
    CommandLineRunner start(PostRepository postRepository,
                            AppUserRepository appUserRepository,
                            PostService postService,
                            PostMapper postMapper,
                            TaskRepository taskRepository,
                            ToDoListRepository toDoListRepository) {
        return args -> {
            Faker faker = new Faker();
            Random random = new Random();

            AppUser user = AppUser.builder()
                    .email("hamza@email.com")
                    .appUserType(AppUserType.ADMIN)
                    .password("1234")
                    .registrationDate(LocalDateTime.now())
                    .build();

            appUserRepository.save(user);

            for (int i = 0; i < 10; i++) {
                Post post = Post.builder()
                        .appUser(user)
                        .title(faker.lorem().sentence())
                        .content(faker.lorem().paragraph(30))
                        .publishedAt(LocalDateTime.now())
                        .build();

                postRepository.save(post);
            }

            ToDoList toDoList = ToDoList.builder()
                    .appUser(user)
                    .name("Learn Game Dev")
                    .status(Status.IN_PROGRESS)
                    .build();

            toDoListRepository.save(toDoList);

            for (int i = 0; i < 10; i++) {
                Task task = Task.builder()
                        .title(faker.lorem().sentence())
                        .description(faker.lorem().paragraph(3))
                        .status(random.nextBoolean() ? Status.TO_DO : Status.DONE)
                        .createdAt(LocalDateTime.now())
                        .dueDate(LocalDateTime.now().plusDays(10))
                        .toDoList(toDoList)
                        .build();
                taskRepository.save(task);
            }
        };
    }

}

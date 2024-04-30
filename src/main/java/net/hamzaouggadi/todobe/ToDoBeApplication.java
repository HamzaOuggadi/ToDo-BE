package net.hamzaouggadi.todobe;

import com.github.javafaker.Faker;
import net.hamzaouggadi.todobe.entities.Post;
import net.hamzaouggadi.todobe.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class ToDoBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoBeApplication.class, args);
    }


    @Bean
    CommandLineRunner start(PostRepository postRepository) {
        return args -> {
            Faker faker = new Faker();

            Post post = Post.builder()
                    .title(faker.lorem().paragraph())
                    .code(UUID.randomUUID().toString())
                    .content(faker.lorem().paragraph(30))
                    .publishedAt(LocalDateTime.now())
                    .build();

            postRepository.save(post);
        };
    }

}

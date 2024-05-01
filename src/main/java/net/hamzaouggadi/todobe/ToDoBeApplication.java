package net.hamzaouggadi.todobe;

import com.github.javafaker.Faker;
import net.hamzaouggadi.todobe.dtos.PostDTO;
import net.hamzaouggadi.todobe.entities.AppUser;
import net.hamzaouggadi.todobe.entities.Post;
import net.hamzaouggadi.todobe.enums.AppUserType;
import net.hamzaouggadi.todobe.mappers.PostMapper;
import net.hamzaouggadi.todobe.repository.AppUserRepository;
import net.hamzaouggadi.todobe.repository.PostRepository;
import net.hamzaouggadi.todobe.service.PostService;
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
    CommandLineRunner start(PostRepository postRepository,
                            AppUserRepository appUserRepository,
                            PostService postService,
                            PostMapper postMapper) {
        return args -> {
            Faker faker = new Faker();

            AppUser user = AppUser.builder()
                    .email("hamza@email.com")
                    .appUserType(AppUserType.ADMIN)
                    .password("1234")
                    .registrationDate(LocalDateTime.now())
                    .build();

            appUserRepository.save(user);

            Post post = Post.builder()
                    .appUser(user)
                    .title(faker.lorem().paragraph())
                    .content(faker.lorem().paragraph(30))
                    .publishedAt(LocalDateTime.now())
                    .build();





            postRepository.save(post);

            PostDTO postDTO = postMapper.toPostDTO(postRepository.findById(1L).orElseThrow());

            System.out.println("DTO id type : " + postDTO.id().getClass() + "value : " + postDTO.id());

            Post post2 = postMapper.toPost(postDTO);

            System.out.println("Post 2 id type : " + post.getId().getClass() + "value : " + post.getId());

            System.out.println(postService.getAllPostsByUserEmail("hamza@email.com").get(0).content());
        };
    }

}

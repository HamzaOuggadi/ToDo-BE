package net.hamzaouggadi.todobe.service.impl;

import lombok.RequiredArgsConstructor;
import net.hamzaouggadi.todobe.dtos.PostDTO;
import net.hamzaouggadi.todobe.entities.AppUser;
import net.hamzaouggadi.todobe.entities.Post;
import net.hamzaouggadi.todobe.exceptions.AppUserException;
import net.hamzaouggadi.todobe.exceptions.PostException;
import net.hamzaouggadi.todobe.mappers.PostMapper;
import net.hamzaouggadi.todobe.repository.AppUserRepository;
import net.hamzaouggadi.todobe.repository.PostRepository;
import net.hamzaouggadi.todobe.service.AppUserService;
import net.hamzaouggadi.todobe.service.PostService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;
    private final MessageSource messageSource;

    @Override
    public List<PostDTO> getAllPostsByUserEmail(String email) {
        if (appUserService.checkIfUserExistsByEmail(email)) {
            List<Post> posts = postRepository.findAllByAppUserEmailIgnoreCase(email.toLowerCase());
            if (!posts.isEmpty()) {
                List<PostDTO> postDTOS = new ArrayList<>();
                posts.forEach(post -> postDTOS.add(postMapper.toPostDTO(post)));
                return postDTOS;
            } else {
                throw new PostException(
                        messageSource.getMessage(
                                "post.list.empty",
                                new Object[]{},
                                Locale.getDefault()
                        ),
                        HttpStatus.NO_CONTENT
                );
            }
        } else {
            throw new AppUserException(
                    messageSource.getMessage(
                            "no.user.found",
                            new Object[]{email},
                            Locale.getDefault()
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostDTO> postDTOS = new ArrayList<>();
        postRepository.findAll().forEach(post -> postDTOS.add(postMapper.toPostDTO(post)));
        return postDTOS;
    }

    @Override
    public void createPost(PostDTO postDTO) {
        Post post = postMapper.toPost(postDTO);
        post.setPublishedAt(LocalDateTime.now());
        post.setAppUser(appUserRepository.findByEmailIgnoreCase(postDTO.writerEmail()).orElseThrow(() -> new AppUserException(
                messageSource.getMessage(
                        "no.user.found",
                        new Object[]{postDTO.writerEmail()},
                        Locale.getDefault()
                ),
                HttpStatus.NOT_FOUND))
        );
        postRepository.save(post);
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        AppUser appUser = appUserRepository.findByEmailIgnoreCase(postDTO.writerEmail()).orElseThrow();
        Post post = postMapper.toPost(postDTO);
        post.setAppUser(appUser);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}

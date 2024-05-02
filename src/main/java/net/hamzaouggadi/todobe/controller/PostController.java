package net.hamzaouggadi.todobe.controller;


import lombok.RequiredArgsConstructor;
import net.hamzaouggadi.todobe.dtos.PostDTO;
import net.hamzaouggadi.todobe.service.PostService;
import net.hamzaouggadi.todobe.utils.GenericMessage;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MessageSource messageSource;


    @GetMapping("/")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/by-user-email/{email}")
    public ResponseEntity<List<PostDTO>> getAllPostByUserEmail(@PathVariable String email) {
        return ResponseEntity.ok(postService.getAllPostsByUserEmail(email));
    }


    @PostMapping("/create-post")
    public ResponseEntity<GenericMessage> createPost(@RequestBody PostDTO postDTO) {
        postService.createPost(postDTO);
        GenericMessage message = new GenericMessage(
                messageSource.getMessage(
                        "post.created.success",
                        new Object[]{},
                        Locale.getDefault()
                ),
                HttpStatus.CREATED
        );
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }


    @PutMapping("/update-post")
    public ResponseEntity<GenericMessage> updatePost(@RequestBody PostDTO postDTO) {
        postService.updatePost(postDTO);
        GenericMessage message = new GenericMessage(
                messageSource.getMessage(
                        "post.update.success",
                        new Object[]{},
                        Locale.getDefault()
                ),
                HttpStatus.OK
        );
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }

    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<GenericMessage> deletePostById(@PathVariable Long postId) {
        postService.deletePost(postId);
        GenericMessage message = new GenericMessage(
                messageSource.getMessage(
                        "post.deleted.success",
                        new Object[]{},
                        Locale.getDefault()
                ),
                HttpStatus.OK
        );
        return ResponseEntity.status(message.getHttpStatus()).body(message);
    }
}

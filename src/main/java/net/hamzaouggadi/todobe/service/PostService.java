package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.dtos.PostDTO;
import net.hamzaouggadi.todobe.entities.Post;

import java.util.List;

public interface PostService {

    //List<Post> getPostsByTitle(String title);
    List<PostDTO> getAllPostsByUserEmail(String email);
    List<PostDTO> getAllPosts();
    void createPost(PostDTO post);
    void updatePost(PostDTO post);
    void deletePost(Long postId);

}

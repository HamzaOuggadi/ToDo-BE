package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.entities.Post;

import java.util.List;

public interface PostService {

    Post getPostByTitle(String title);
    Post getPostByUserEmail(String email);
    List<Post> getAllPosts();
    Post createPost(Post post);
    Post updatePost(Post post);
    void deletePost(Post post);
}

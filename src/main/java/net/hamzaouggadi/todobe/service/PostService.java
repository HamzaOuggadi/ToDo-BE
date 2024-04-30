package net.hamzaouggadi.todobe.service;

import net.hamzaouggadi.todobe.entities.Post;

public interface PostService {

    Post getPostByTitle(String title);
    Post createPost(Post post);
    Post updatePost(Post post);
    void deletePost(Post post);
}

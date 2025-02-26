package com.jinuspace.jinu.domain.post.controller;

import com.jinuspace.jinu.domain.post.entity.Post;
import com.jinuspace.jinu.domain.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{boardName}")
    public List<Post> getPostsByBoard(@PathVariable String boardName) {
        log.info("getPostsByBoard boardName:{}", boardName);
        return postService.getPostsByBoard(boardName);
    }

    @PostMapping("/{boardName}")
    public void createPost(@PathVariable String boardName, @RequestBody Post post) {
        log.info("createPost boardName:{} post:{}", boardName, post);
        postService.createPost(boardName, post);
    }

    @GetMapping("/detail/{id}")
    public Optional<Post> getPost(@PathVariable Long id) {
        log.info("getPost id:{}", id);
        return postService.getPostById(id);
    }

}
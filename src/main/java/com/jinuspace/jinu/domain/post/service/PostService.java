package com.jinuspace.jinu.domain.post.service;

import com.jinuspace.jinu.domain.board.entity.Board;
import com.jinuspace.jinu.domain.board.repository.BoardRepository;
import com.jinuspace.jinu.domain.post.entity.Post;
import com.jinuspace.jinu.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BoardRepository boardRepository;

    public List<Post> getPostsByBoard(String boardName) {
        Board board = boardRepository.findByName(boardName);
        return postRepository.findByBoard(board);
    }

    public void createPost(String boardName, Post post) {
        Board board = boardRepository.findByName(boardName);
        post.setBoard(board);
        postRepository.save(post);
    }

    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }
}

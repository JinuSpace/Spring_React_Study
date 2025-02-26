package com.jinuspace.jinu.domain.post.repository;

import com.jinuspace.jinu.domain.board.entity.Board;
import com.jinuspace.jinu.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByBoard(Board board); // 특정 게시판의 게시글 조회
}
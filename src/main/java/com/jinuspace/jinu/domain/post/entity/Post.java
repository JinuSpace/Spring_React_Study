package com.jinuspace.jinu.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jinuspace.jinu.domain.board.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts") // 게시글 테이블
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String author;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false) // 어떤 게시판에 속하는지
    @JsonBackReference
    private Board board;
}

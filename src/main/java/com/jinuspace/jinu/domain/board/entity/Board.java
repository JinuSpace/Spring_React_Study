package com.jinuspace.jinu.domain.board.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.jinuspace.jinu.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "boards") // 게시판 테이블
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // 게시판 이름 (예: 자유게시판, 공지사항)

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Post> posts; // 해당 게시판에 속한 게시글 목록
}

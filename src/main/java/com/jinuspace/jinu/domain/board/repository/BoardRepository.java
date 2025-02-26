package com.jinuspace.jinu.domain.board.repository;

import com.jinuspace.jinu.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByName(String name); // 게시판 이름으로 찾기
}

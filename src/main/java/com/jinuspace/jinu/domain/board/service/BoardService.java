package com.jinuspace.jinu.domain.board.service;

import com.jinuspace.jinu.domain.board.entity.Board;
import com.jinuspace.jinu.domain.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardByName(String name) {
        return boardRepository.findByName(name);
    }
}
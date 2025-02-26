package com.jinuspace.jinu.domain.board.Controller;

import com.jinuspace.jinu.domain.board.entity.Board;
import com.jinuspace.jinu.domain.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public List<Board> getAllBoards() {
        log.info("getAllBoards");
        return boardService.getAllBoards();
    }
}

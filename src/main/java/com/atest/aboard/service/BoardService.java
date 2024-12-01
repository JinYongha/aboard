package com.atest.aboard.service;

import com.atest.aboard.domain.Board;
import com.atest.aboard.mapper.BoardMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    private final BoardMapper boardMapper;
    @Autowired

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    // 전체 목록 반환
    public List<Board> getAllBoards() {
        return boardMapper.getAllBoards();
    }
    // 전체 목록 반환
    public List<Board> getBoardList() {
        return boardMapper.getAllBoards();
    }
    // 페이징된 목록 반환
    public List<Board> getBoardList(int page, int size) {
        PageHelper.startPage(page, size);  // PageHelper로 페이징 처리
        return boardMapper.getAllBoards();
    }
    public Board getBoardById(int bno) {
        return boardMapper.getBoardById(bno);
    }

    public void insertBoard(Board board) {
        boardMapper.insertBoard(board);
    }

    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }

    public void deleteBoard(int bno) {
        boardMapper.deleteBoard(bno);
    }


}

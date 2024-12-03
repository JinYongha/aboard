package com.atest.aboard.controller;

import com.atest.aboard.domain.Board;
import com.atest.aboard.service.BoardService;
import com.github.pagehelper.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.HashMap; // HashMap도 추가

@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/aboard")
    public String aboardPage(Model model) {
        // 필요한 데이터를 모델에 추가
        model.addAttribute("username", "홍길동");
        model.addAttribute("position", "개발자");
        return "aboard"; // 타임리프 템플릿 aboard.html 로 이동
    }


    // 페이징된 게시글 목록 반환
    @GetMapping("/list/json")
    @ResponseBody
    public ResponseEntity<Page<Board>> getBoardList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        // PageHelper를 사용하여 페이징 처리된 데이터 반환
        List<Board> boards = boardService.getBoardList(page, size);
        Page<Board> pageResult = (Page<Board>) boards;
        return ResponseEntity.ok(pageResult); // 페이징된 결과를 JSON으로 반환
    }

    @GetMapping("/list/page")
    public ResponseEntity<Map<String, Object>> getPagedBoardList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        // PageHelper를 사용하여 페이징 처리된 데이터 가져오기
        List<Board> boards = boardService.getBoardList(page, size);

        // 총 페이지 수와 현재 페이지를 계산
        Page<Board> pageResult = (Page<Board>) boards;

        // JSON 응답 객체 만들기
        Map<String, Object> response = new HashMap<>();
        response.put("boards", boards);
        response.put("currentPage", pageResult.getPageNum());
        response.put("totalPages", pageResult.getPages());
        response.put("pageSize", pageResult.getPageSize());

        return ResponseEntity.ok(response); // JSON 형식으로 반환
    }

    @GetMapping("/all")
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards); // 전체 데이터를 JSON으로 반환
    }
    //thymleap 템플릿을 사용하는 경우
//    @GetMapping("/view/{bno}")
//    public String view(@PathVariable int bno, Model model) {
//        System.out.println("bno:=======> " + bno);
//        Board board = boardService.getBoardById(bno);
//        if (board == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
//        }
//
//        model.addAttribute("board", board);
//        return "board/view";
//    }

    //JSON형태로 데이타를 받아오는 방법 으로 구현
    @GetMapping("/view/{bno}")
    @ResponseBody  // 추가: JSON 응답을 반환하도록 처리
    public ResponseEntity<Board> view(@PathVariable int bno) {
//        System.out.println("bno:=======> " + bno);
        Board board = boardService.getBoardById(bno);
        if (board == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
        }
        return ResponseEntity.ok(board); // Board 객체를 JSON 형식으로 반환
    }

    @GetMapping("/write")
    public String writeForm() {
        return "board/write";
    }

    @PostMapping("/write")
    public String write(Board board) {
        boardService.insertBoard(board);
        return "redirect:/board/list";
    }

    @PostMapping("/delete/{bno}")
    public String delete(@PathVariable int bno) {
        boardService.deleteBoard(bno);
        return "redirect:/board/aboard";
    }

    // 수정 화면으로 이동
    @GetMapping("/modify/{bno}")
    public String modifyForm(@PathVariable int bno, Model model) {
        Board board = boardService.getBoardById(bno);
        model.addAttribute("board", board);
        return "board/modify"; // 수정 폼 템플릿
    }

    // 수정 처리
    @PostMapping("/modify/{bno}")
    public String modify(@PathVariable int bno, Board board) {
        board.setBno(bno); // URL에서 받은 bno를 Board 객체에 설정
        boardService.updateBoard(board); // 수정 요청 처리
        return "redirect:/board/view/" + bno; // 수정 후 상세 보기로 이동
    }

    @GetMapping("/tasks")
    @ResponseBody
    public ResponseEntity<List<String>> getTasks() {
        List<String> tasks = List.of("업무 1", "업무 2", "업무 3"); // 실제 데이터는 서비스에서 가져옵니다.
        return ResponseEntity.ok(tasks);
    }



}

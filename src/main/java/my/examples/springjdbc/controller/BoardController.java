package my.examples.springjdbc.controller;

import my.examples.springjdbc.dto.Board;
import my.examples.springjdbc.dto.User;
import my.examples.springjdbc.service.BoardService;
import my.examples.springjdbc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BoardController {
    BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String main(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "search", required = false, defaultValue = "") String option,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            Model model) {
        List<Board> boards = boardService.selectAllBoards(page);

        model.addAttribute("boards", boards);
        return "/board/list";
    }

    @GetMapping("/view")
    public String view(
            @RequestParam(name = "id") long id,
            Model model) {
        Board board = boardService.selectBoard(id);
        boardService.updateReadCount(id);
        model.addAttribute("board", board);
        return "/board/view";
    }

    @GetMapping("/modify")
    public String modify(
            @RequestParam(name = "id") long id,
            Model model) {
        Board board = boardService.selectBoard(id);
        model.addAttribute("board", board);
        return "/board/modify";
    }

    @GetMapping("/write")
    public String write(){
        return "/board/write";
    }

    @PostMapping("/write")
    public String writeAction(@RequestParam(name="title") String title,
                              @RequestParam(name="content") String content,
                                                            Model model,
                                                            HttpSession session) {
        User user = (User)session.getAttribute("logininfo");
        Board board = new Board(title,user.getEmail(),user.getNickname(),content);
        long id = boardService.addBoard(board);
        boardService.updateId(id);
        model.addAttribute("message","글이 등록되었습니다.");

        return "redirect:list";
    }

    @GetMapping("/rewrite")
    public String rewrite(@RequestParam(name="id") long id,
                          Model model,
                          HttpSession session){
        Board board = boardService.selectBoard(id);
        model.addAttribute("board", board);
        return "/board/rewriteform";
    }

    @PostMapping("/rewrite")
    public String rewriteAction(@RequestParam(name="title") String title,
                                @RequestParam(name="content") String content,
                                @RequestParam(name="id") long id,
                                HttpSession session){
        User user = (User)session.getAttribute("logininfo");
        Board oboard = boardService.selectBoard(id);
        Board board = new Board(title,user.getEmail(),user.getNickname(),content);
        board.setGroup_no(oboard.getGroup_no());
        board.setGroup_depth(oboard.getGroup_depth());
        board.setGroup_seq(oboard.getGroup_seq());

        boardService.reAddBoard(board);

        return "redirect:list";
    }
}
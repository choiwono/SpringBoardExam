package my.examples.springjdbc.controller;

import my.examples.springjdbc.dto.Board;
import my.examples.springjdbc.dto.Criteria;
import my.examples.springjdbc.dto.PageMaker;
import my.examples.springjdbc.dto.User;
import my.examples.springjdbc.service.BoardService;
import my.examples.springjdbc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String main(@ModelAttribute("cri") Criteria cri,
                       @RequestParam(name="page", required = false, defaultValue = "1") int page,
                       @RequestParam(name="search", required = false) String search,
                       @RequestParam(name="keyword", required = false) String keyword,
                       Model model) {
        int totalNum = 0;
        List<Board> boards = null;
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);

        if(!StringUtils.isEmpty(search)) {
            pageMaker.setSearch(search);
            pageMaker.setKeyword(keyword);
            totalNum = boardService.selectSearchCount(pageMaker);
            boards = boardService.selectSearchBoards(pageMaker);
        } else {
            totalNum = boardService.selectAllCount();
            boards = boardService.selectAllBoards(cri);
        }

        pageMaker.setTotalCount(totalNum);

        model.addAttribute("page", page);
        model.addAttribute("pageMaker", pageMaker);
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

    @PostMapping("/modify")
    public String modifyAction(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content,
                                        HttpSession session,
                                        Model model) {
        User user = (User)session.getAttribute("logininfo");
        Board board = new Board(title,user.getEmail(),user.getNickname(),content,id);
        boardService.updateBoard(board);
        model.addAttribute("board", board);
        return "redirect:list";
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

    @GetMapping("/delete")
    public String delete(@RequestParam(name="id") long id) {
        boardService.deleteBoard(id);
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
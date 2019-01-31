package my.examples.springjdbc.service;

import my.examples.springjdbc.dto.Board;
import my.examples.springjdbc.dto.Criteria;

import java.util.List;

public interface BoardService {
    //List<Board> selectAllBoards(int page);
    Board selectBoard(Long id);
    List<Board> searchBoards(String option,String keyword);
    int selectAllCount();
    long selectSearchCount(String subject,String keyword);
    long getTotalPage(int boardCount, int list);
    long addBoard(Board board);
    long deleteBoard(Long id);
    long updateId(Long id);
    long updateReadCount(long id);
    long updateReBoard(Board board);
    long reAddBoard(Board board);
    long updateBoard(Board board);
    List<Board> selectAllBoards(Criteria cri);
}

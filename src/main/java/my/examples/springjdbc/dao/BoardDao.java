package my.examples.springjdbc.dao;

import my.examples.springjdbc.dto.Board;
import my.examples.springjdbc.dto.Criteria;
import my.examples.springjdbc.dto.PageMaker;

import java.util.List;

public interface BoardDao {
    // 게시글 전체조회
    // 게시글 1개 조회
    // 검색
    // 페이징
    //List<Board> selectAllBoards(int start,int limit);
    Board selectBoard(Long id);
    List<Board> searchBoards(String option,String keyword);
    int selectAllCount();
    long getTotalPage(int boardCount, int list);
    long addBoard(Board board);
    long updateBoard(Long id,String title,String content);
    long deleteBoard(Long id);
    long updateId(Long id);
    void updateReadCount(long id);
    long updateReBoard(Board board);
    long addReBoard(Board board);
    long updateBoard(Board board);
    List<Board> selectAllBoards(Criteria cri);
    int selectSearchCount(PageMaker pageMaker);
    List<Board> selectSearchBoards(PageMaker pageMaker);
}
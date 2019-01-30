package my.examples.springjdbc.dao;

import my.examples.springjdbc.dto.Board;

import java.util.List;

public interface BoardDao {
    // 게시글 전체조회
    // 게시글 1개 조회
    // 검색
    // 페이징
    List<Board> selectAllBoards(int start,int limit);
    Board selectBoard(Long id);
    List<Board> searchBoards(String option,String keyword);
    int selectAllCount();
    long selectSearchCount(String subject,String keyword);
    long getTotalPage(int boardCount, int list);
    long addBoard(Board board);
    long updateBoard(Long id,String title,String content);
    long deleteBoard(Long id);
    long updateId(Long id);
    long updateReadCount(long id);
    long updateReBoard(Board board);
    long addReBoard(Board board);
}
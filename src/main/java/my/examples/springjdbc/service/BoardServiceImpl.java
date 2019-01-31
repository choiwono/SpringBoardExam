package my.examples.springjdbc.service;

import my.examples.springjdbc.dao.BoardDao;
import my.examples.springjdbc.dto.Board;
import my.examples.springjdbc.dto.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardDao boardDao;

    /*@Override
    @Transactional(readOnly = true)
    public List<Board> selectAllBoards(int page) {
        int start = page * 10 - 10;
        return boardDao.selectAllBoards(start,10);
    }*/

    @Override
    @Transactional(readOnly = true)
    public Board selectBoard(Long id) {
        return boardDao.selectBoard(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> searchBoards(String option, String keyword) {
        return boardDao.searchBoards(option,keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public int selectAllCount() {
        return boardDao.selectAllCount();
    }

    @Override
    @Transactional(readOnly = true)
    public long selectSearchCount(String subject, String keyword) {
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalPage(int boardCount, int list) {
        return boardDao.getTotalPage(boardCount,list);
    }

    @Override
    @Transactional
    public long addBoard(Board board) {
        return boardDao.addBoard(board);
    }

    @Override
    @Transactional
    public long deleteBoard(Long id) {
        return boardDao.deleteBoard(id);
    }

    @Override
    public long updateId(Long id) {
        return boardDao.updateId(id);
    }

    @Override
    @Transactional
    public long updateReadCount(long id) {
        return boardDao.updateReadCount(id);
    }

    @Override
    @Transactional
    public long updateReBoard(Board board) {
        return boardDao.updateReBoard(board);
    }

    @Override
    public long reAddBoard(Board board) {
        return boardDao.addReBoard(board);
    }

    @Override
    @Transactional
    public long updateBoard(Board board) {
        return boardDao.updateBoard(board);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Board> selectAllBoards(Criteria cri) {
        return boardDao.selectAllBoards(cri);
    }
}

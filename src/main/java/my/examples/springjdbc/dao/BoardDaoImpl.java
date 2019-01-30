package my.examples.springjdbc.dao;

import my.examples.springjdbc.dto.Board;
import my.examples.springjdbc.dto.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import static my.examples.springjdbc.dao.BoardDaoSqls.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao{

    private SimpleJdbcInsert simpleJdbcInsert;
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<Board> rowMapper = BeanPropertyRowMapper.newInstance(Board.class);

    public BoardDaoImpl(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("Board")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Board> selectAllBoards(int start,int limit) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("start", start);
        paramMap.put("limit", limit);
        return jdbc.query(SELECT_BOARDS, paramMap,rowMapper);
    }

    @Override
    public Board selectBoard(Long id) {
        Board board = null;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        try {
            board = jdbc.queryForObject(SELECT_BOARD_BY_ID, paramMap, rowMapper);
        }catch(EmptyResultDataAccessException da){
            return null;
        }
        return board;
    }

    @Override
    public List<Board> searchBoards(String option, String keyword) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("keyword", "%"+keyword+"%");
        paramMap.put("start", 0);
        paramMap.put("limit", 5);
        return jdbc.query(SELECT_SEARCH_BY_TITLE,paramMap,rowMapper);
    }

    @Override
    public int selectAllCount() {
        Map emptyMap = Collections.emptyMap();
        int count = jdbc.queryForObject(SELECT_ALLBOARD_COUNT,emptyMap,Integer.class);
        return count;
    }

    @Override
    public long selectSearchCount(String subject, String keyword) {
        return 0;
    }

    @Override
    public long getTotalPage(int boardCount, int list) {
        int totalPage = 0;
        totalPage = boardCount / list;
        if(boardCount % list > 0) {
            totalPage++;
        }
        return totalPage;
    }

    @Override
    public long  addBoard(Board board) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("title", board.getTitle());
        paramMap.put("user_id", board.getUserID());
        paramMap.put("nickname", board.getNickname());
        paramMap.put("content", board.getContent());
        paramMap.put("regdate", board.getRegdate());
        paramMap.put("read_count", board.getRead_count());
        paramMap.put("group_no", board.getGroup_no());
        paramMap.put("group_depth", board.getGroup_depth());
        paramMap.put("group_seq", board.getGroup_seq());

        Number number = simpleJdbcInsert.executeAndReturnKey(paramMap);
        return number.longValue();
    }

    @Override
    public long updateBoard(Long id, String title, String content) {
        long count = 0;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        paramMap.put("title", title);
        paramMap.put("content", content);
        count = jdbc.update(UPDATE_BOARD, paramMap);
        return count;
    }

    @Override
    public long deleteBoard(Long id) {
        long result = 0;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        return jdbc.update(DELETE_BOARD, paramMap);
    }

    @Override
    public long updateId(Long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("group_no",id);
        paramMap.put("id",id);
        return jdbc.update(UPDATE_BOARD_BY_PARENT, paramMap);
    }

    @Override
    public long updateReadCount(long id) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id",id);
        return jdbc.update(UPDATE_BOARD_READCOUNT, paramMap);
    }

    @Override
    public long updateReBoard(Board board) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("groupNo",board.getGroup_no());
        paramMap.put("groupSeq",board.getGroup_seq());
        return jdbc.update(UPDATE_REBOARD, paramMap);
    }

    @Override
    public long addReBoard(Board board) {
        Map<String, Object> paramMap = new HashMap<>();

        paramMap.put("title", board.getTitle());
        paramMap.put("user_id", board.getUserID());
        paramMap.put("nickname", board.getNickname());
        paramMap.put("content", board.getContent());
        paramMap.put("regdate", board.getRegdate());
        paramMap.put("read_count", board.getRead_count());
        paramMap.put("group_no", board.getGroup_no());
        paramMap.put("group_depth", board.getGroup_depth()+1);
        paramMap.put("group_seq", board.getGroup_seq()+1);

        Number number = simpleJdbcInsert.executeAndReturnKey(paramMap);
        return number.longValue();
    }
}

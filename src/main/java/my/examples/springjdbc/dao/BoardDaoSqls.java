package my.examples.springjdbc.dao;

public class BoardDaoSqls {

    public static final String SELECT_BOARDS =
            "SELECT id,title,user_id,nickname,content,regdate,read_count,"+
            " group_no,group_seq,group_depth FROM board ORDER BY group_no DESC, group_seq ASC"+
            " limit :start,:limit";
    public static final String SELECT_BOARD_BY_ID =
            "SELECT id,title,user_id,nickname,content,regdate,group_no," +
            "group_seq,group_depth FROM board where id=:id";
    public static final String SELECT_ALLBOARD_COUNT =
            "SELECT COUNT(*) FROM board";
    public static final String SELECT_SEARCH_BY_TITLE =
            "SELECT id,title,user_id,nickname,content,regdate,read_count,"+
            "group_no,group_seq,group_depth FROM board"+
            "where title"+" LIKE :keyword"+" limit :start,:limit";
    public static final String UPDATE_BOARD =
            "UPDATE board SET title=:title,content=:content WHERE id=:id";
    public static final String DELETE_BOARD =
            "UPDATE board SET delete_yn='Y',title=:title,content=:content "
            +"WHERE id=:id";
    public static final String UPDATE_BOARD_BY_PARENT =
            "UPDATE board SET group_no=:group_no WHERE id=:id";
    public static final String UPDATE_BOARD_READCOUNT =
            "UPDATE board SET read_count=read_count+1 WHERE id=:id";
    public static final String UPDATE_REBOARD =
            "UPDATE board SET group_seq = group_seq + 1 WHERE group_no = :groupNo and group_seq > :groupSeq";
}

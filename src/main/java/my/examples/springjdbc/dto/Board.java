package my.examples.springjdbc.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
public class Board{
    private long id;
    private String title;
    private String user_id;
    private String nickname;
    private String content;
    private Date regdate;
    private int read_count;
    private int group_no;
    private int group_seq;
    private int group_depth;

    public Board() {
        regdate = new Date();
        read_count = 0;
        group_no = 0;
        group_seq = 0;
        group_depth = 0;
    }

    public Board(String title, String user_id,String nickname,String content) {
        this();
        this.title = title;
        this.user_id = user_id;
        this.nickname = nickname;
        this.content = content;
    }

    public Board(String title, String user_id,String nickname,String content,Long id) {
        this(title,user_id,nickname,content);
        this.id = id;
    }

    public Board(Long id,String title, String user_id,String nickname,String content,Date regdate,int read_count,int group_no, int group_seq, int group_depth) {
        this(title, user_id, nickname, content, id);
        this.read_count = read_count;
        this.group_no = group_no;
        this.group_seq = group_seq;
        this.group_depth = group_depth;
        this.regdate = regdate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserID() {
        return user_id;
    }

    public void setUserID(String userID) {
        this.user_id = userID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public int getRead_count() {
        return read_count;
    }

    public void setRead_count(int read_count) {
        this.read_count = read_count;
    }

    public int getGroup_no() {
        return group_no;
    }

    public void setGroup_no(int group_no) {
        this.group_no = group_no;
    }

    public int getGroup_seq() {
        return group_seq;
    }

    public void setGroup_seq(int group_seq) {
        this.group_seq = group_seq;
    }

    public int getGroup_depth() {
        return group_depth;
    }

    public void setGroup_depth(int group_depth) {
        this.group_depth = group_depth;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userID='" + user_id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", content='" + content + '\'' +
                ", regdate=" + regdate +
                ", read_count=" + read_count +
                ", group_no=" + group_no +
                ", group_seq=" + group_seq +
                ", group_depth=" + group_depth +
                '}';
    }
}
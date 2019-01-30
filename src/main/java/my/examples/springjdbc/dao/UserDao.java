package my.examples.springjdbc.dao;

import my.examples.springjdbc.dto.User;

import java.util.List;

public interface UserDao {
    public User selectUserByEmail(String email);
    public Long addUser(User user); // 생성된 id(Long type)를 리턴한다.
    public void updateUser(Long id, String name, String nickname);
    List<User> selectByPage(int start, int limit);
}

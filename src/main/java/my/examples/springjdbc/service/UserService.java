package my.examples.springjdbc.service;

import my.examples.springjdbc.dto.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);
    public User getUserByEmail(String email);
    List<User> getUsers(int page);
}

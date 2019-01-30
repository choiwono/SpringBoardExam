package my.examples.springjdbc.service;

import my.examples.springjdbc.dao.UserDao;
import my.examples.springjdbc.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 선언, 컴포넌트 스캔으로 아래 객체는 모두 스프링이 관리한다.
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    /*public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }*/

    @Override
    @Transactional
    public User addUser(User user) {
        Long id = userDao.addUser(user);
        user.setId(id);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userDao.selectUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers(int page) {
        int start = page * 3 - 3;
        return userDao.selectByPage(start, 3);
    }
}

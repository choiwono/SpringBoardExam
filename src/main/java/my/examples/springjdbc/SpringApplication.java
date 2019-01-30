package my.examples.springjdbc;

import my.examples.springjdbc.config.ApplicationConfig;
import my.examples.springjdbc.dao.BoardDao;
import my.examples.springjdbc.dao.UserDao;
import my.examples.springjdbc.dao.UserDaoImpl;
import my.examples.springjdbc.dto.Board;
import my.examples.springjdbc.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SpringApplication {
    public static void main(String[] args){
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ApplicationConfig.class);

        BoardDao boardDao = applicationContext.getBean(BoardDao.class);
        Long result = boardDao.updateBoard(54L,"제목수정","내용수정");
        System.out.println(result);
    }
}

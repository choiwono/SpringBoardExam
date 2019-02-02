package my.examples.springjdbc.dao;

import my.examples.springjdbc.dto.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.examples.springjdbc.dao.UserDaoSqls.*;

// 컴포넌트스캔 - 해당객체가 Bean으로 등록된다.
@Repository
public class UserDaoImpl implements UserDao{
	private SimpleJdbcInsert simpleJdbcInsert;
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);

	// 스프링 컨테이너는 인스턴스를 생성하려고 생성자를 호출한다.
	// 생성자를 호출하는데, DataSource를 주입한다. (생성자 주입)
	public UserDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("user")
				.usingGeneratedKeyColumns("id");
	}

	@Override
	public User selectUserByEmail(String email) {
		User user = null;
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("email", email);
		try {
			user = jdbc.queryForObject(SELECT_USER_BY_EMAIL, paramMap, rowMapper);
		}catch(EmptyResultDataAccessException da){
			return null;
		}
		return user;
	}

	@Override
	public Long addUser(User user) {
		PasswordEncoder passwordEncoder =
				PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodepasswd = passwordEncoder.encode(user.getPasswd());

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", user.getName());
		paramMap.put("nickname", user.getNickname());
		paramMap.put("email", user.getEmail());
		paramMap.put("passwd", encodepasswd);
		paramMap.put("regdate", user.getRegdate());
		Number number = simpleJdbcInsert.executeAndReturnKey(paramMap);
		return number.longValue();
	}

	@Override
	public void updateUser(Long id, String name, String nickname) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("name", name);
		paramMap.put("nickname", nickname);
		jdbc.update(UPDATE_USER, paramMap);
	}

    @Override
    public List<User> selectByPage(int start, int limit) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("start", start);
		paramMap.put("limit", limit);
		return jdbc.query(SELECT_USERS, paramMap, rowMapper);
    }

	@Override
	public int updatePasswordByEmail(User user) {
		Map<String, Object> paramMap = new HashMap<>();
		PasswordEncoder passwordEncoder =
				PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodepasswd = passwordEncoder.encode(user.getPasswd());
		paramMap.put("password",encodepasswd);
		paramMap.put("email",user.getEmail());
		return jdbc.update(UPDATE_PASSWORD_BY_EMAIL, paramMap);
	}
}
package my.examples.springjdbc.dao;

public class UserDaoSqls {
	public static final String SELECT_USERS =
			"SELECT id, name, nickname, email, passwd, regdate FROM user limit :start, :limit";

	public static final String SELECT_USER_BY_EMAIL =
			"SELECT id, name, nickname, email, passwd, regdate FROM user WHERE email = :email";

	public static final String UPDATE_USER =
			"UPDATE user SET name = :name, nickname = :nickname WHERE id = :id";
    public static final String UPDATE_PASSWORD_BY_EMAIL =
			"UPDATE user SET passwd =:password WHERE email=:email";
}

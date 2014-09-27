package sss.yyao.dao;

import sss.yyao.pojo.User;

/**
 * user表的DAO
 * @author Administrator
 *
 */
public interface UserDao {
	/**
	 * 通过id来获取User对象
	 * @param id
	 * @return
	 */
	public User findUserById(String id);
	/**
	 * 将User对象持久化存储
	 * @param user
	 * @return true:存储成功 false:存储失败
	 */
	public boolean saveUser(User user);
}

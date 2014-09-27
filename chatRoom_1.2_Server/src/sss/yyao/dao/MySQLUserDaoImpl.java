package sss.yyao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sss.yyao.dao.util.MySQLDaoUtils;
import sss.yyao.dao.util.OracleDaoUtils;
import sss.yyao.pojo.User;

public class MySQLUserDaoImpl implements UserDao {

	@Override
	public User findUserById(String id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = MySQLDaoUtils.getConnection();
			String sql = "select id,password,name from chatroom_user where id=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if(rs.next()){
				User user = new User();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDaoUtils.close(con, pst, rs);
		}
		return null;
	}

	@Override
	public boolean saveUser(User user) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = MySQLDaoUtils.getConnection();
			String sql = "insert into chatroom_user(id,password,name) values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getId());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getName());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDaoUtils.close(con, pst);
		}
		return false;
	}

}

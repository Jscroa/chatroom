package sss.yyao.pojo;

import java.io.Serializable;

/**
 * 登陆信息
 * 包含
 * id&password
 * @author Administrator
 *
 */
public class Login implements Serializable {
	private String id;
	private String password;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Login(String id, String password) {
		this.id = id;
		this.password = password;
	}
	public Login() {
	}
	@Override
	public String toString() {
		return "Login [" + id + ", " + password + "]";
	}
}

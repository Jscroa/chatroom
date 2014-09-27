package sss.yyao.pojo;

import java.io.Serializable;

/**
 * 注册信息
 * 包含
 * id&password&name
 * @author Administrator
 *
 */
public class Register implements Serializable {
	private String id;
	private String passowrd;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassowrd() {
		return passowrd;
	}
	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Register(String id, String passowrd, String name) {
		this.id = id;
		this.passowrd = passowrd;
		this.name = name;
	}
	public Register() {
	}
	@Override
	public String toString() {
		return "Register [" + id + ", " + passowrd + ", "
				+ name + "]";
	}
}

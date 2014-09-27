package sss.yyao.pojo;

import java.io.Serializable;

/**
 * Socket传输的数据类型
 * 包含
 * (sign&(register|login|chat))|isSuccess
 * @author Administrator
 *
 */
public class Info implements Serializable {
	private String sign;
	
	private Register register = new Register();
	private Login login = new Login();
	private Chat chat = new Chat();
	
//	private Register register;
//	private Login login;
//	private Chat chat;
	
	
	
	private boolean isSuccess;
	public Info() {
		super();
	}
	
	/**
	 * 注册、登陆、聊天客户端发送消息给服务器后服务器会返回一个boolean值给客户端
	 * true表示发送成功
	 * flase表示发送失败
	 * @param sign
	 * @param isSuccess
	 */
	public Info(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * 聊天时客户端发送给服务器的
	 * @param sign
	 * @param chat
	 */
	public Info(String sign, Chat chat) {
		this.sign = sign;
		this.chat = chat;
	}
	
	/**
	 * 登陆时客户端发送给服务器的
	 * @param sign
	 * @param login
	 */
	public Info(String sign, Login login) {
		this.sign = sign;
		this.login = login;
	}
	
	/**
	 * 注册时客户端发送给服务器的
	 * @param sign
	 * @param register
	 */
	public Info(String sign, Register register) {
		this.sign = sign;
		this.register = register;
	}

	public String getSign() {
		return sign;
	}

	public Register getRegister() {
		return register;
	}

	public Login getLogin() {
		return login;
	}

	public Chat getChat() {
		return chat;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	@Override
	public String toString() {
		return "Info [\n    " + sign + ", \n    " + register.toString() + ", \n    "
				+ login.toString() + ", \n    " + chat.toString() + ", \n    " + isSuccess + "\n]";
	}

}

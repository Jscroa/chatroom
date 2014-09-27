package sss.yyao.pojo;

import java.io.Serializable;

/**
 * 聊天信息
 * 包含
 * said
 * @author Administrator
 *
 */
public class Chat implements Serializable {
	private String said;

	public String getSaid() {
		return said;
	}

	public void setSaid(String said) {
		this.said = said;
	}

	public Chat(String said) {
		this.said = said;
	}

	public Chat() {
	}

	@Override
	public String toString() {
		return "Chat [" + said + "]";
	}
}

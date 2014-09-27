package sss.yyao.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerUtil {

	/**
	 * 
	 * @param name
	 * @param said
	 * @return
	 */
	public static String createMsg(String name, String said) {
		String time = new SimpleDateFormat("yyyy-MM-DD hh:mm:ss").format(new Date());
		String msg = name+"  "+time+"  说：\n    "+said+"\n\n";
		return msg;
	}

	/**
	 * 
	 * @param string
	 * @param passowrd
	 * @param name
	 * @return
	 */
	public static boolean register(String string, String passowrd,
			 String name) {
		return false;
	}

}

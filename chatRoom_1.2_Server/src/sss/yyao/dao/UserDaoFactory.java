package sss.yyao.dao;

import java.io.IOException;
import java.util.Properties;

/**
 * DAO工厂。根据配文件生成DAO对象
 * @author Administrator
 *
 */
public class UserDaoFactory {

	private static Properties pro = new Properties();
	
	/**
	 * 加载配置文件
	 */
	static{
		try {
			pro.load(UserDaoFactory.class.getClassLoader().getResourceAsStream(
					"sss/yyao/resource/dao_cfg.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据配置文件的daoname字段来决定实例化哪个DAO实现类
	 * @return
	 */
	public static UserDao getDao(){
		String daoname = pro.getProperty("daoname");
		UserDao dao = null;
		if(daoname.equals("oracle")){
			dao = new OracleUserDaoImpl();
		}else if(daoname.equals("mysql")){
			dao = new MySQLUserDaoImpl();
		}
		return dao;
	}
	
}

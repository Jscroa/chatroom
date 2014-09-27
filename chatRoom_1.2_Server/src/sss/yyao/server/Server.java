package sss.yyao.server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import sss.yyao.dao.UserDao;
import sss.yyao.dao.UserDaoFactory;
import sss.yyao.pojo.Info;
import sss.yyao.pojo.Login;
import sss.yyao.pojo.Register;
import sss.yyao.pojo.User;
import sss.yyao.server.panel.ServerPanel;
import sss.yyao.server.util.ServerUtil;

/**
 * 服务器
 * @author Administrator
 *
 */
public class Server {
	
	private Object obj = new Object();

	private ServerSocket serverSocket;
	/**
	 * 线程池
	 * 指配接收客户端消息的任务
	 */
	private static ExecutorService threadPool;
	/**
	 * 用来存放所有在线用户的输出流
	 */
	private List<ObjectOutputStream> allOut;
	/**
	 * 用来存放当前所有在线用户
	 */
	private Map<String, String> onLineUser;
	/**
	 * 消息队列
	 */
	private BlockingDeque<String> msgQueue;
	/**
	 * 数据的持久化访问对象
	 */
	private UserDao dao;



	public Server() {
		try {
			/*
			 * 初始化服务器端口
			 */
			insertDocument("正在初始化端口...\n", Color.BLUE);
			serverSocket = new ServerSocket(8989);
			insertDocument("初始化端口成功！\n", Color.BLUE);
			ServerPanel.bt_startServer.setText("服务器已经启动");
			ServerPanel.bt_startServer.setEnabled(false);

			// 初始化线程池
			threadPool = Executors.newCachedThreadPool();
			// 初始化输出流集合
			allOut = new ArrayList<ObjectOutputStream>();
			// 初始化在线用户集合
			onLineUser = new HashMap<String, String>();
			// 初始化消息队列
			msgQueue = new LinkedBlockingDeque<String>();
			// 初始化DAO对象
			dao = UserDaoFactory.getDao();

			/*
			 * 启动服务器
			 */
			StartHandler startHandler = new StartHandler();
			Thread startThread = new Thread(startHandler);
			startThread.start();
		} catch (IOException e) {
			insertDocument("端口已被占用，初始化端口失败！\n", Color.RED);
		}
	}




	/**
	 * 文本域中加入某种颜色的字符串
	 * @param text 要加入的字符串
	 * @param textColor 字体颜色
	 */
	private void insertDocument(String text, Color textColor) {
		SimpleAttributeSet set = new SimpleAttributeSet();
		StyleConstants.setForeground(set, textColor);
		StyleConstants.setFontSize(set, 13);
		Document doc = ServerPanel.ta_mainTextPane.getStyledDocument();
		try {
			doc.insertString(doc.getLength(), text, set);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}




	/**
	 * 消息处理
	 */
	private static void handlerEvent() {

		ServerPanel.bt_startServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 创建服务器
				Server server = new Server();
			}
		});

	}




	/**
	 * 启动服务器的任务
	 * @author Administrator
	 *
	 */
	private class StartHandler implements Runnable {

		@Override
		public void run() {
			try {
				Runnable sendHandler = new SendMessageToAllClientHandler();
				Thread sendThread = new Thread(sendHandler);
				sendThread.start();
				while (true) {
					Socket socket = serverSocket.accept();
					String clientHost = socket.getInetAddress().getHostAddress();
					insertDocument("IP为 " + clientHost + " 的客户端连入！\n",
							Color.BLUE);
					Runnable getMessageHandler = new GetMessageFromClientHandler(
							socket);
					threadPool.execute(getMessageHandler);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}




	/**
	 * 将客户端的输出流放入共享集合中
	 * @param oos
	 */
	public synchronized void addOut(ObjectOutputStream oos) {
		allOut.add(oos);
	}

	/**
	 * 将客户端的输出流从共享集合中删除
	 * @param oos
	 */
	public synchronized void removeOut(ObjectOutputStream oos) {
		allOut.remove(oos);
	}

	/**
	 * 遍历输出流集合，发送消息
	 * @param message
	 */
	public synchronized void sendMessageToAllClient(String message) {
		for (ObjectOutputStream oos : allOut) {
			try {
				oos.writeObject(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 转发消息的任务
	 * @author Administrator
	 *
	 */
	private class SendMessageToAllClientHandler implements Runnable {

		@Override
		public void run() {
			String message = null;
			while (true) {
				if (msgQueue.size() > 0) {
					message = msgQueue.poll();
					sendMessageToAllClient(message);
				} else {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}




	/**
	 * 注册方法。
	 * 从数据库中找出ID是否存在
	 * 若存在，提示该用户已经注册过
	 * 若不存在，将id,passowrd,name放入数据库中，注册成功，
	 * 将id,name放入onLineUser集合中
	 * 提示name上线，刷新在线人数
	 * @param id
	 * @param passowrd
	 * @param name
	 * @return true:注册成功 false:注册失败
	 */
	public boolean register(String id, String password, String name) {
		synchronized (obj) {
			User user = new User(id, password, name);
			return dao.saveUser(user);
		}
	}

	/**
	 * 登录方法。
	 * 从数据库中找出该ID对应的密码
	 * ID找不到，提示没有此账号
	 * 密码不匹配，提示账号或密码输入错误
	 * 若都能找到，则登陆成功，
	 * 找出该id对应的name，
	 * 将id,name放入onLineUser集合中
	 * 并提示name上线，刷新在线人数
	 * @param id
	 * @param passowrd
	 * @return name
	 */
	public String login(String id, String passowrd) {
		synchronized (obj) {
			User user = dao.findUserById(id);
			String name = null;
			if (passowrd.equals(user.getPassword())) {
				name = user.getName();
			}
			return name;
		}

	}

	/**
	 * 根据ID找出name
	 * 从数据库中找出该ID对应的name
	 * @param id
	 * @return 找到的name
	 */
	public String findNameById(String id) {
		String name = null;
		name = onLineUser.get(id);
		return name;
	}

	/**
	 * 检查此id是否在线
	 * @param id
	 * @return true在线 false不在线
	 */
	public boolean checkOnline(String id) {
		for (String id1 : onLineUser.keySet()) {
			if (id.equals(id1)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 从客户端接收消息的任务。
	 * 循环接收客户端发送的消息，转换成Info对象
	 * 根据Info的标志sign判断该客户端执行的是什么操作
	 * sign="register": 
	 * 	注册，将注册结果返回 true:成功 false:失败
	 * sign="login": 
	 * 	登陆，将登陆结果返回 true:成功 false:失败
	 * sign="chat": 
	 * 	聊天，将取到的信息拼接成一个字符串，该字符串中包括name,time,said
	 * 		将该字符串放入消息队列
	 * @author Administrator
	 *
	 */
	private class GetMessageFromClientHandler implements Runnable {

		private Socket socket;

		public GetMessageFromClientHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			ObjectInputStream ois = null;
			ObjectOutputStream oos = null;
			String id = null;
			String name = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				oos = new ObjectOutputStream(socket.getOutputStream());
				while (true) {
					Info info = (Info) ois.readObject();
					String sign = info.getSign();
					// 当用户发来的是注册或登陆请求时
					while (sign.equals("register") || sign.equals("login")) {
						boolean success = false;
						if (sign.equals("register")) {
							Register register = info.getRegister();
							id = register.getId();
							String password = register.getPassowrd();
							name = register.getName();
							if (success = register(id, password, name)) {// 注册成功！
								onLineUser.put(id, name);
								addOut(oos);
								insertDocument(id + " " + name + " 注册成功！\n",
										Color.RED);
								ServerPanel.lb_count.setText(onLineUser.size()
										+ "");
								ServerPanel.ta_user.setText(onLineUser.toString());
							}
						} else if (sign.equals("login")) {
							Login login = info.getLogin();
							id = login.getId();
							String password = login.getPassword();
							if (!checkOnline(id)) {
								if ((name = login(id, password)) != null) {// 登陆成功！
									onLineUser.put(id, name);
									name = findNameById(id);
									success = true;
									addOut(oos);
									insertDocument(id + " " + name + " 已上线！\n",
											Color.RED);
									ServerPanel.lb_count.setText(onLineUser
											.size() + "");
								}
							}
						}
//						insertDocument(info.toString()+"\n", Color.GRAY);
						sign = "";
						info = new Info(success);
						oos.writeObject(info);
					}
					//用户发来的是聊天消息
					while (sign.equals("chat")) {
						if ((name = onLineUser.get(id)) != null) {// 在onLineUser集合中找出id对应的name
							String said = info.getChat().getSaid();
							String msg = ServerUtil.createMsg(name, said);
							insertDocument(msg, Color.BLACK);
							msgQueue.offer(msg, 5, TimeUnit.SECONDS);
						}
						sign = "";
					}
				}
			} catch (IOException e) {
				onLineUser.remove(id);
				removeOut(oos);
				ServerPanel.lb_count.setText(onLineUser.size() + "");
				insertDocument("有一个客户端失去连接...\n", Color.RED);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				try {
					if (name != null) {
						insertDocument(name + " 下线了！\n", Color.RED);
					}
					if (oos != null) {
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}




	/**
	 * =================== MAIN ===================
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("服务器");
		frame.add(new ServerPanel());
		frame.setBounds(200, 200, 620, 440);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		handlerEvent();
	}

}

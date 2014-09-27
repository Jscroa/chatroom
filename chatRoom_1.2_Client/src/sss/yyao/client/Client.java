package sss.yyao.client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sss.yyao.client.panel.ChatPanel;
import sss.yyao.client.panel.ConnectPanel;
import sss.yyao.client.panel.LoginPanel;
import sss.yyao.client.panel.RegisterPanel;
import sss.yyao.pojo.Chat;
import sss.yyao.pojo.Info;
import sss.yyao.pojo.Login;
import sss.yyao.pojo.Register;

/**
 * 客户端
 * @author Administrator
 *
 */
public class Client {

	private Socket socket;
	/**
	 * 卡片布局
	 */
	private static CardLayout card;
	/**
	 * 卡片布局的底片
	 */
	private static JPanel contentPane;
	/**
	 * 连接面板
	 */
	private static ConnectPanel connectPanel;
	/**
	 * 登陆面板
	 */
	private static LoginPanel loginPanel;
	/**
	 * 注册面板
	 */
	private static RegisterPanel registerPanel;
	/**
	 * 聊天面板
	 */
	private static ChatPanel chatPanel;
	/**
	 * 客户端的输出流
	 */
	private static ObjectOutputStream oos = null;
	/**
	 * 客户端的输入流
	 */
	private static ObjectInputStream ois = null;
	/**
	 * 接收消息的任务
	 */
	private static Runnable getMsgFromServer;



	/**
	 * 构造方法
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public Client(String ip) throws UnknownHostException, IOException {
		// 初始化socket
		socket = new Socket(ip, 8989);
		// 初始化输出流
		oos = new ObjectOutputStream(socket.getOutputStream());
		// 初始化输入流
		ois = new ObjectInputStream(socket.getInputStream());
		// 初始化接收消息的任务
		getMsgFromServer = new GetMessageFromServerHandler(ois);
	}




	/**
	 * 事件处理
	 */
	private static void handlerEvent() {

		/*
		 * 连接面板的连接按钮
		 */
		ConnectPanel.bt_connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ip = ConnectPanel.tf_IP.getText();
				if (!ip.equals("")) {
					try {
						Client client = new Client(ip);
						card.show(contentPane, "loginPanel");
					} catch (UnknownHostException e) {
						JOptionPane.showMessageDialog(contentPane,
								"输入的IP地址有误，请重新输入！");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(contentPane,
								"服务器未开启，或者您输入的IP地址有误，请重新输入！");
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "地址栏不能为空！");
				}
			}
		});

		/*
		 * 连接面板的清空按钮
		 */
		ConnectPanel.bt_empty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConnectPanel.tf_IP.setText("");
			}
		});

		/*
		 * 登陆面板的登陆按钮
		 */
		LoginPanel.bt_login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = LoginPanel.tf_ID.getText();
				String password = LoginPanel.tf_password.getText();
				if (!id.equals("") && !password.equals("")) {
					Login login = new Login(id, password);
					Info info = new Info("login", login);
					try {
						oos.writeObject(info);
						if (((Info) ois.readObject()).isSuccess()) {// 登陆成功！
							/*
							 * 启动接收服务器消息的任务线程
							 */
							Thread thread = new Thread(getMsgFromServer);
							thread.start();
							JOptionPane.showMessageDialog(contentPane, "登陆成功！");// ////////////////////////////////////////////////
							card.show(contentPane, "chatPanel");
						} else {
							JOptionPane.showMessageDialog(contentPane, "登陆失败！");// ////////////////////////////////////////////////
						}
					} catch (IOException e) {
						JOptionPane
								.showMessageDialog(contentPane, "断开连接，登陆失败！");
						// e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "用户名或密码不能为空！");
				}
			}
		});

		/*
		 * 登陆面板的注册按钮
		 */
		LoginPanel.bt_register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane, "registerPanel");
				LoginPanel.tf_ID.setText("");
				LoginPanel.tf_password.setText("");
			}
		});

		/*
		 * 注册面板的注册按钮
		 */
		RegisterPanel.bt_register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = RegisterPanel.tf_ID.getText();
				String password = RegisterPanel.tf_password.getText();
				String repassword = RegisterPanel.tf_repassword.getText();
				String name = RegisterPanel.tf_name.getText();
				if (!id.equals("") && !password.equals("")
						&& !repassword.equals("") && !name.equals("")) {
					if (password.equals(repassword)) {
						Register register = new Register(id, password, name);
						Info info = new Info("register", register);
						try {
							oos.writeObject(info);
							if (((Info) ois.readObject()).isSuccess()) {// 注册成功！
								/*
								 * 启动接收服务器消息的任务线程
								 */
								Thread thread = new Thread(getMsgFromServer);
								thread.start();
								JOptionPane.showMessageDialog(contentPane,
										"注册成功！");
								card.show(contentPane, "chatPanel");
							} else {
								JOptionPane.showMessageDialog(contentPane,
										"注册失败！");
							}
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(contentPane,
								"两次输入的密码不一致，请重新输入！");
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "注册信息不能为空！");
				}
			}
		});

		/*
		 * 注册面板的取消按钮
		 */
		RegisterPanel.bt_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane, "loginPanel");
				RegisterPanel.tf_ID.setText("");
				RegisterPanel.tf_password.setText("");
				RegisterPanel.tf_repassword.setText("");
				RegisterPanel.tf_name.setText("");
			}
		});

		/*
		 * 聊天面板的发送按钮
		 */
		ChatPanel.bt_send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String said = ChatPanel.ta_send.getText();
				Chat chat = new Chat(said);
				Info info = new Info("chat", chat);
				ChatPanel.ta_send.setText("");
				try {
					oos.writeObject(info);
//					if (((Info) ois.readObject()).isSuccess()) {
//
//					} else {
//						JOptionPane.showMessageDialog(contentPane,
//								"您已下线，请重新登陆！");
//					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}




	/**
	 * 接收服务器发来消息的任务
	 * @author Administrator
	 *
	 */
	private class GetMessageFromServerHandler implements Runnable {

		private ObjectInputStream ois;

		public GetMessageFromServerHandler(ObjectInputStream ois) {
			this.ois = ois;
		}

		@Override
		public void run() {
			try {
				while (true) {
					String said = (String) ois.readObject();
					ChatPanel.ta_chat.append(said);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}




	/**
	 * =================== MAIN ===================
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("客户端");
		card = new CardLayout(0, 0);
		contentPane = new JPanel();
		connectPanel = new ConnectPanel();
		loginPanel = new LoginPanel();
		registerPanel = new RegisterPanel();
		chatPanel = new ChatPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// 设置卡片布局
		contentPane.setLayout(card);
		// 向卡片布局中加入面板
		contentPane.add(connectPanel, "connectPanel");
		contentPane.add(loginPanel, "loginPanel");
		contentPane.add(registerPanel, "registerPanel");
		contentPane.add(chatPanel, "chatPanel");
		frame.setContentPane(contentPane);
		frame.setBounds(200, 200, 620, 440);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		handlerEvent();
	}




}

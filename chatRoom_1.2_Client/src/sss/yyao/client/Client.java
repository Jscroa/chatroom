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
 * �ͻ���
 * @author Administrator
 *
 */
public class Client {

	private Socket socket;
	/**
	 * ��Ƭ����
	 */
	private static CardLayout card;
	/**
	 * ��Ƭ���ֵĵ�Ƭ
	 */
	private static JPanel contentPane;
	/**
	 * �������
	 */
	private static ConnectPanel connectPanel;
	/**
	 * ��½���
	 */
	private static LoginPanel loginPanel;
	/**
	 * ע�����
	 */
	private static RegisterPanel registerPanel;
	/**
	 * �������
	 */
	private static ChatPanel chatPanel;
	/**
	 * �ͻ��˵������
	 */
	private static ObjectOutputStream oos = null;
	/**
	 * �ͻ��˵�������
	 */
	private static ObjectInputStream ois = null;
	/**
	 * ������Ϣ������
	 */
	private static Runnable getMsgFromServer;



	/**
	 * ���췽��
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public Client(String ip) throws UnknownHostException, IOException {
		// ��ʼ��socket
		socket = new Socket(ip, 8989);
		// ��ʼ�������
		oos = new ObjectOutputStream(socket.getOutputStream());
		// ��ʼ��������
		ois = new ObjectInputStream(socket.getInputStream());
		// ��ʼ��������Ϣ������
		getMsgFromServer = new GetMessageFromServerHandler(ois);
	}




	/**
	 * �¼�����
	 */
	private static void handlerEvent() {

		/*
		 * �����������Ӱ�ť
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
								"�����IP��ַ�������������룡");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(contentPane,
								"������δ�����������������IP��ַ�������������룡");
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "��ַ������Ϊ�գ�");
				}
			}
		});

		/*
		 * ����������հ�ť
		 */
		ConnectPanel.bt_empty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConnectPanel.tf_IP.setText("");
			}
		});

		/*
		 * ��½���ĵ�½��ť
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
						if (((Info) ois.readObject()).isSuccess()) {// ��½�ɹ���
							/*
							 * �������շ�������Ϣ�������߳�
							 */
							Thread thread = new Thread(getMsgFromServer);
							thread.start();
							JOptionPane.showMessageDialog(contentPane, "��½�ɹ���");// ////////////////////////////////////////////////
							card.show(contentPane, "chatPanel");
						} else {
							JOptionPane.showMessageDialog(contentPane, "��½ʧ�ܣ�");// ////////////////////////////////////////////////
						}
					} catch (IOException e) {
						JOptionPane
								.showMessageDialog(contentPane, "�Ͽ����ӣ���½ʧ�ܣ�");
						// e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "�û��������벻��Ϊ�գ�");
				}
			}
		});

		/*
		 * ��½����ע�ᰴť
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
		 * ע������ע�ᰴť
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
							if (((Info) ois.readObject()).isSuccess()) {// ע��ɹ���
								/*
								 * �������շ�������Ϣ�������߳�
								 */
								Thread thread = new Thread(getMsgFromServer);
								thread.start();
								JOptionPane.showMessageDialog(contentPane,
										"ע��ɹ���");
								card.show(contentPane, "chatPanel");
							} else {
								JOptionPane.showMessageDialog(contentPane,
										"ע��ʧ�ܣ�");
							}
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(contentPane,
								"������������벻һ�£����������룡");
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "ע����Ϣ����Ϊ�գ�");
				}
			}
		});

		/*
		 * ע������ȡ����ť
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
		 * �������ķ��Ͱ�ť
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
//								"�������ߣ������µ�½��");
//					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}




	/**
	 * ���շ�����������Ϣ������
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
		JFrame frame = new JFrame("�ͻ���");
		card = new CardLayout(0, 0);
		contentPane = new JPanel();
		connectPanel = new ConnectPanel();
		loginPanel = new LoginPanel();
		registerPanel = new RegisterPanel();
		chatPanel = new ChatPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// ���ÿ�Ƭ����
		contentPane.setLayout(card);
		// ��Ƭ�����м������
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

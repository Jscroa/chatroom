package sss.yyao.server.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

/**
 * 服务器端面板
 * @author Administrator
 *
 */
public class ServerPanel extends JPanel {
	/**
	 * 主文本框，记录所有信息
	 * 如：客户端的登入登出、聊天信息等
	 */
	public static JTextPane ta_mainTextPane;
	/**
	 * 启动服务器的按钮
	 */
	public static JButton bt_startServer;
	/**
	 * 记录当前在线人数
	 */
	public static JLabel lb_count;
	/**
	 * 在线人员列表
	 */
	public static JTextArea ta_user;

	/**
	 * Create the panel.
	 */
	public ServerPanel() {
		setLayout(null);
		
		ta_mainTextPane = new JTextPane();
		ta_mainTextPane.setEditable(false);
		ta_mainTextPane.setFont(new Font("微软雅黑", Font.PLAIN, 13));
//		ta_mainTextPane.setLineWrap(true);
		JScrollPane jScrollPane = new JScrollPane(ta_mainTextPane);
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setBounds(10, 10, 358, 380);
		add(jScrollPane);
		
		bt_startServer = new JButton("启动服务器");//启动服务器
		bt_startServer.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		bt_startServer.setBounds(378, 337, 212, 53);
		add(bt_startServer);
		
		JLabel lblNewLabel = new JLabel("当前在线人数");//当前在线人数
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblNewLabel.setBounds(378, 10, 212, 30);
		add(lblNewLabel);
		
		lb_count = new JLabel("0");
		lb_count.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lb_count.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_count.setBounds(388, 50, 85, 30);
		add(lb_count);
		
		JLabel label = new JLabel("人");//人
		label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label.setBounds(483, 50, 19, 30);
		add(label);
		
		ta_user = new JTextArea();
		JScrollPane jScrollPane2 = new JScrollPane(ta_user);
		jScrollPane2.setBounds(378, 100, 212, 227);
		add(jScrollPane2);

	}
}

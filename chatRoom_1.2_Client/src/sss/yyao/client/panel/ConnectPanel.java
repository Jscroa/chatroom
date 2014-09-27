package sss.yyao.client.panel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 连接面板
 * @author Administrator
 *
 */
public class ConnectPanel extends JPanel {
	/**
	 * 输入IP地址的文本框
	 */
	public static JTextField tf_IP;
	/**
	 * 连接按钮
	 */
	public static JButton bt_connect;
	/**
	 * 清空按钮
	 */
	public static JButton bt_empty;

	/**
	 * Create the panel.
	 */
	public ConnectPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(137, 173, 54, 15);
		add(lblNewLabel);
		
		tf_IP = new JTextField();
		tf_IP.setToolTipText("");
		tf_IP.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tf_IP.setBounds(292, 170, 175, 21);
		add(tf_IP);
		tf_IP.setColumns(10);
		
		bt_connect = new JButton("连接");//连接
		bt_connect.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bt_connect.setBounds(137, 264, 93, 23);
		add(bt_connect);
		
		bt_empty = new JButton("清空");//清空
		bt_empty.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		bt_empty.setBounds(374, 264, 93, 23);
		add(bt_empty);
		
		JLabel label = new JLabel("连  接");//连接
		label.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 34, 580, 48);
		add(label);

	}
}

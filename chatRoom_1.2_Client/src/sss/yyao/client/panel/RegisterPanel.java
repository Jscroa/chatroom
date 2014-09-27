package sss.yyao.client.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 注册面板
 * @author Administrator
 *
 */
public class RegisterPanel extends JPanel {
	/**
	 * 输入ID的文本框
	 */
	public static JTextField tf_ID;
	/**
	 * 输入密码的文本框
	 */
	public static JTextField tf_password;
	/**
	 * 确认密码的文本框
	 */
	public static JTextField tf_repassword;
	/**
	 * 输入昵称的文本框
	 */
	public static JTextField tf_name;
	/**
	 * 注册提交按钮
	 */
	public static JButton bt_register;
	/**
	 * 取消按钮
	 */
	public static JButton bt_cancel;

	/**
	 * Create the panel.
	 */
	public RegisterPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("账号");//账号
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(167, 112, 54, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码");//密码
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(167, 152, 54, 15);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("确认密码");//确认密码
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(167, 192, 54, 15);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("昵称");//昵称
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(167, 232, 54, 15);
		add(lblNewLabel_3);
		
		JLabel label = new JLabel("注  册");//注册
		label.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 34, 580, 48);
		add(label);
		
		tf_ID = new JTextField();
		tf_ID.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tf_ID.setHorizontalAlignment(SwingConstants.LEFT);
		tf_ID.setBounds(275, 109, 149, 21);
		add(tf_ID);
		tf_ID.setColumns(10);
		
		tf_password = new JTextField();
		tf_password.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tf_password.setHorizontalAlignment(SwingConstants.LEFT);
		tf_password.setColumns(10);
		tf_password.setBounds(275, 149, 149, 21);
		add(tf_password);
		
		tf_repassword = new JTextField();
		tf_repassword.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tf_repassword.setHorizontalAlignment(SwingConstants.LEFT);
		tf_repassword.setColumns(10);
		tf_repassword.setBounds(275, 189, 149, 21);
		add(tf_repassword);
		
		tf_name = new JTextField();
		tf_name.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		tf_name.setHorizontalAlignment(SwingConstants.LEFT);
		tf_name.setColumns(10);
		tf_name.setBounds(275, 229, 149, 21);
		add(tf_name);
		
		bt_register = new JButton("确认提交");//确认提交
		bt_register.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		bt_register.setBounds(167, 285, 93, 23);
		add(bt_register);
		
		bt_cancel = new JButton("取消");//取消
		bt_cancel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		bt_cancel.setBounds(331, 285, 93, 23);
		add(bt_cancel);

	}
}

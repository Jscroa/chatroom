package sss.yyao.client.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * µÇÂ½Ãæ°å
 * @author Administrator
 *
 */
public class LoginPanel extends JPanel {
	/**
	 * ÊäÈëIDµÄÎÄ±¾¿ò
	 */
	public static JTextField tf_ID;
	/**
	 * ÊäÈëÃÜÂëµÄÎÄ±¾¿ò
	 */
	public static JTextField tf_password;
	/**
	 * µÇÂ½°´Å¥
	 */
	public static JButton bt_login;
	/**
	 * ×¢²á°´Å¥
	 */
	public static JButton bt_register;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ÕËºÅ");//ÕËºÅ
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(167, 152, 54, 15);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ÃÜÂë");//ÃÜÂë
		lblNewLabel_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(167, 192, 54, 15);
		add(lblNewLabel_1);
		
		JLabel label = new JLabel("µÇ  Â½");//µÇÂ½
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 28));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 34, 580, 48);
		add(label);
		
		tf_ID = new JTextField();
		tf_ID.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		tf_ID.setHorizontalAlignment(SwingConstants.LEFT);
		tf_ID.setBounds(275, 149, 149, 21);
		add(tf_ID);
		tf_ID.setColumns(10);
		
		tf_password = new JTextField();
		tf_password.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		tf_password.setHorizontalAlignment(SwingConstants.LEFT);
		tf_password.setBounds(275, 189, 149, 21);
		add(tf_password);
		tf_password.setColumns(10);
		
		bt_login = new JButton("µÇÂ½");//µÇÂ½
		bt_login.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		bt_login.setBounds(167, 285, 93, 23);
		add(bt_login);
		
		bt_register = new JButton("×¢²á");//×¢²á
		bt_register.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		bt_register.setBounds(331, 285, 93, 23);
		add(bt_register);

	}

}

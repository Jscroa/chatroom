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
 * �������
 * @author Administrator
 *
 */
public class ConnectPanel extends JPanel {
	/**
	 * ����IP��ַ���ı���
	 */
	public static JTextField tf_IP;
	/**
	 * ���Ӱ�ť
	 */
	public static JButton bt_connect;
	/**
	 * ��հ�ť
	 */
	public static JButton bt_empty;

	/**
	 * Create the panel.
	 */
	public ConnectPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP");
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		lblNewLabel.setBounds(137, 173, 54, 15);
		add(lblNewLabel);
		
		tf_IP = new JTextField();
		tf_IP.setToolTipText("");
		tf_IP.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		tf_IP.setBounds(292, 170, 175, 21);
		add(tf_IP);
		tf_IP.setColumns(10);
		
		bt_connect = new JButton("����");//����
		bt_connect.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bt_connect.setBounds(137, 264, 93, 23);
		add(bt_connect);
		
		bt_empty = new JButton("���");//���
		bt_empty.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		bt_empty.setBounds(374, 264, 93, 23);
		add(bt_empty);
		
		JLabel label = new JLabel("��  ��");//����
		label.setFont(new Font("΢���ź�", Font.PLAIN, 28));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 34, 580, 48);
		add(label);

	}
}

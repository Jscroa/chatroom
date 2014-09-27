package sss.yyao.client.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * ÁÄÌìÃæ°å
 * 
 * @author Administrator
 * 
 */
public class ChatPanel extends JPanel {

	/**
	 * ÁÄÌì¿ò
	 */
	public static JTextArea ta_chat;
	/**
	 * ÊäÈëµÄÎÄ±¾¿ò
	 */
	public static JTextArea ta_send;
	/**
	 * ·¢ËÍ°´Å¥
	 */
	public static JButton bt_send;

	/**
	 * Create the panel.
	 */
	public ChatPanel() {
		setLayout(null);

		ta_chat = new JTextArea();
		ta_chat.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		ta_chat.setEditable(false);
		ta_chat.setLineWrap(true);
		JScrollPane jScrollPane = new JScrollPane(ta_chat);
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setBounds(10, 10, 400, 300);
		add(jScrollPane);

		ta_send = new JTextArea();
		ta_send.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		ta_send.setLineWrap(true);
		JScrollPane jScrollPane2 = new JScrollPane(ta_send);
		jScrollPane2
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane2.setBounds(10, 320, 325, 70);
		add(jScrollPane2);

		bt_send = new JButton("·¢ËÍ");// ·¢ËÍ
		bt_send.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
		bt_send.setBounds(335, 320, 75, 70);
		add(bt_send);

	}
}

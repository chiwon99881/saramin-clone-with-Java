package Job;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class withdrawal extends JDialog implements ActionListener, KeyListener	{

	private helpP h;
	private JTextField iwt_with,pwt_with;
	private JLabel iwl_with,pwl_with;
	private JButton b_with;
	//private String id_DB,pw_DB;
	private ResultSet rs1,rs2;
	private Statement st1,st2;
	private String sql1,sql2;
	private Connection conn;
	
	public withdrawal(helpP h) {
		super(h,true);
		this.h = h;		
		setTitle("회원탈퇴");
		setSize(200, 190);
		setLayout(new FlowLayout());
		setLocationRelativeTo(h);
		
		iwl_with = new JLabel("ID");
		iwl_with.setForeground(Color.BLACK);
		iwl_with.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 15));
		
		iwt_with = new JTextField(15);
		iwt_with.addKeyListener(this);
		
		pwl_with = new JLabel("PASSWORD");
		pwl_with.setForeground(Color.BLACK);
		pwl_with.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 15));
		
		pwt_with = new JTextField(15);
		pwt_with.addKeyListener(this);
		
		b_with = new JButton("Go");
		b_with.addKeyListener(this);
		b_with.addActionListener(this);
		b_with.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 15));
		b_with.setBackground(Color.WHITE);
		b_with.setForeground(new Color(38, 171, 255));
		
		add(iwl_with);
		add(iwt_with);
		add(pwl_with);
		add(pwt_with);
		add(b_with);
		
		setVisible(true);
		iwt_with.requestFocus();
		pwt_with.requestFocus();
		b_with.requestFocus();
		
	}

	public void exp() {
		
		if(!(iwt_with.getText().isEmpty() || pwt_with.getText().isEmpty())) 
		{				
			if(iwt_with.getText().trim().equals(DB.id_DB.trim()) && pwt_with.getText().trim().equals(DB.pw_DB.trim())) 
			{
				int res = JOptionPane.showConfirmDialog(this, "회원탈퇴 하시겠습니까?","잠깐!",JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION) {
			
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						conn = DriverManager.getConnection(
								"jdbc:oracle:thin:@127.0.0.1:1521:xe",
								"Job",
								"Job");

						st1 = conn.createStatement();
						st2 = conn.createStatement();
						
						sql1 = "DELETE FROM USER_INFO WHERE ID='"+iwt_with.getText().trim()+"'";
						sql2 = "DELETE FROM RESUME";
						
						rs1 = st1.executeQuery(sql1);
						rs2 = st2.executeQuery(sql2);

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					int res2 = JOptionPane.showConfirmDialog(this, "계정이 삭제되었습니다 감사합니다.","정상",JOptionPane.CLOSED_OPTION);
					if(res2 == JOptionPane.OK_OPTION) 
						{
							System.exit(0);
						}
					
				}
			}else {
				JOptionPane.showMessageDialog(this, "아이디 혹은 비밀번호가 틀립니다.");
				iwt_with.setText("");
				pwt_with.setText("");
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "아이디 혹은 비밀번호를 입력하세요");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o == b_with) {
			DB.db_StartF();
			exp();
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		
		if(i == e.VK_ENTER) {
			DB.db_StartF();
			exp();
		}
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

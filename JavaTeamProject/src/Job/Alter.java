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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Alter extends JDialog implements KeyListener, ActionListener{
	
	private helpP hh;
	static JTextField iwt_al,pwt_al;
	private JLabel iwl_al,pwl_al;
	private JButton b_al;

	
	public Alter(helpP hh) {
		super(hh,true);
		this.hh = hh;
		setTitle("정보변경");
		setSize(200, 190);
		setLayout(new FlowLayout());
		setLocationRelativeTo(hh);
		
		iwl_al = new JLabel("ID");
		iwl_al.setForeground(Color.BLACK);
		iwl_al.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 15));
		
		iwt_al = new JTextField(15);
		iwt_al.addKeyListener(this);
		
		pwl_al = new JLabel("PASSWORD");
		pwl_al.setForeground(Color.BLACK);
		pwl_al.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 15));
		
		pwt_al = new JTextField(15);
		pwt_al.addKeyListener(this);
		
		b_al = new JButton("Go");
		b_al.addKeyListener(this);
		b_al.addActionListener(this);
		b_al.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 15));
		b_al.setBackground(Color.WHITE);
		b_al.setForeground(new Color(38, 171, 255));
		
		add(iwl_al);
		add(iwt_al);
		add(pwl_al);
		add(pwt_al);
		add(b_al);
		
		setVisible(true);
		iwt_al.requestFocus();
		pwt_al.requestFocus();
		b_al.requestFocus();
	}


	public void exp() {
		
		DB.db_StartF();
		
		if(!(iwt_al.getText().isEmpty() || pwt_al.getText().isEmpty())) 
		{				
			if(iwt_al.getText().trim().equals(DB.id_DB.trim()) && pwt_al.getText().trim().equals(DB.pw_DB.trim())) 
			{
				new Alter_in();
				hh.setVisible(false);
			}else{
				JOptionPane.showMessageDialog(this, "아이디 혹은 비밀번호가 틀립니다.");
				iwt_al.setText("");
				pwt_al.setText("");
			}
			
		}else{
			JOptionPane.showMessageDialog(this, "아이디 혹은 비밀번호를 입력하세요");
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if(i == e.VK_ENTER) {
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == b_al) {
			exp();
		}
	}
}

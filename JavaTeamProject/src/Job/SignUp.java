package Job;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class SignUp extends JDialog implements ActionListener{
	private JTextField id,password,name,university;
	private JLabel idL,passL,nameL,uniL;
	private JButton signup,cancel;
	private StartF dia;
	
	public SignUp(StartF dia) {
		super(dia,true);
		this.dia = dia;
		setTitle("회원가입");
		setSize(400, 210);
		setLocation(300,300);
		setLayout(new GridLayout(5, 2,0,5));
		
		idL = new JLabel("ID(영문자만 가능)");
		idL.setBackground(new Color(38, 171, 255));
		idL.setForeground(Color.WHITE);
		idL.setBorder(new LineBorder(new Color(38, 171, 255),2));
		idL.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		idL.setOpaque(true);
		
		passL = new JLabel("PASSWORD(영문자만 가능)");
		passL.setBackground(new Color(38, 171, 255));
		passL.setForeground(Color.WHITE);
		passL.setBorder(new LineBorder(new Color(38, 171, 255),2));
		passL.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		passL.setOpaque(true);
		
		nameL = new JLabel("NAME");
		nameL.setBackground(new Color(38, 171, 255));
		nameL.setForeground(Color.WHITE);
		nameL.setBorder(new LineBorder(new Color(38, 171, 255),2));
		nameL.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		nameL.setOpaque(true);
		
		uniL = new JLabel("UNIVERSITY");
		uniL.setBackground(new Color(38, 171, 255));
		uniL.setForeground(Color.WHITE);
		uniL.setBorder(new LineBorder(new Color(38, 171, 255),2));
		uniL.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		uniL.setOpaque(true);
		
		id = new JTextField(15);
		password = new JTextField(15);
		name = new JTextField(15);
		university = new JTextField(15);
		
		signup = new JButton("SIGN UP");
		signup.setBackground(new Color(38, 171, 255));
		signup.setForeground(Color.WHITE);
		signup.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		signup.addActionListener(this);
		
		cancel = new JButton("CANCEL");
		cancel.setBackground(new Color(38, 171, 255));
		cancel.setForeground(Color.WHITE);
		cancel.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		cancel.addActionListener(this);
		
		add(idL);
		add(id);
		add(passL);
		add(password);
		add(nameL);
		add(name);
		add(uniL);
		add(university);
		add(signup);
		add(cancel);
		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		String sID = id.getText().trim();
		String sPW = password.getText().trim();
		String sNA = name.getText().trim();
		String sUI = university.getText().trim();
		
		if(o == signup) {
			
			DB.db_SignUp(sID, sPW, sNA, sUI);
		
			int res = JOptionPane.showConfirmDialog(this, "회원가입이 완료되었습니다.","회원가입",JOptionPane.CLOSED_OPTION);
			if(res == JOptionPane.OK_OPTION) {
				setVisible(false);
			}
		}
		if(o == cancel) {
			setVisible(false);
		}
	}
}

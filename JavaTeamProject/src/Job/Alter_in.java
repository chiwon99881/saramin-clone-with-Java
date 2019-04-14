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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Alter_in extends JFrame implements ActionListener{
	private JTextField id,password,name,university;
	private JLabel idL,passL,nameL,uniL;
	private JButton alter,cancel;
	private JPanel mainP;


	public Alter_in() {
		setTitle("회원정보 변경");
		setSize(400, 210);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainP = new JPanel();
		mainP.setLayout(new GridLayout(5, 2,0,5));
		add(mainP);
		
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
		
		DB.db_StartF();
		
		id = new JTextField(15);
		id.setText(DB.id_DB);
		id.setEditable(false);
		password = new JTextField(15);
		name = new JTextField(15);
		university = new JTextField(15);
		
		alter = new JButton("ALTER");
		alter.setBackground(new Color(38, 171, 255));
		alter.setForeground(Color.WHITE);
		alter.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		alter.addActionListener(this);
		
		cancel = new JButton("CANCEL");
		cancel.setBackground(new Color(38, 171, 255));
		cancel.setForeground(Color.WHITE);
		cancel.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		cancel.addActionListener(this);
		
		mainP.add(idL);
		mainP.add(id);
		mainP.add(passL);
		mainP.add(password);
		mainP.add(nameL);
		mainP.add(name);
		mainP.add(uniL);
		mainP.add(university);
		mainP.add(alter);
		mainP.add(cancel);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		
		String aID = id.getText().trim();
		String aPW = password.getText().trim();
		String aNA = name.getText().trim();
		String aUI = university.getText().trim();
		
		
		if(o == alter) {
		
			DB.db_Alterin(aID, aPW, aNA, aUI);
			
			int res = JOptionPane.showConfirmDialog(this, "변경이 정상적으로 완료되었습니다.","확인",JOptionPane.CLOSED_OPTION);
			if(res == JOptionPane.OK_OPTION) {
				setVisible(false);
				new helpP();
			}
		}
		if(o == cancel) {
			int res = JOptionPane.showConfirmDialog(this, "입력한 정보를 저장하지 않고 종료할까요?","잠깐!",JOptionPane.YES_NO_OPTION);
			if(res == JOptionPane.YES_OPTION) {
				setVisible(false);
				new helpP();
			}
		}
	}

}

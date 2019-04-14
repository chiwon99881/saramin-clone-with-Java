package Job;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class helpP extends JFrame implements ActionListener{
	private JPanel mainP,subP;
	private JLabel mainL;
	private JButton subB1,subB2,subB3;
	
	public helpP() {
		setTitle("고객센터");
		setSize(250,200);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		mainP = new JPanel();
		mainP.setLayout(new BorderLayout());
		mainP.setBorder(new LineBorder(new Color(38, 171, 255)));
		add(mainP);
		
		mainL = new JLabel("Help",SwingConstants.CENTER);
		mainL.setBackground(new Color(38, 171, 255));
		mainL.setOpaque(true);
		mainL.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 20));
		
		subP = new JPanel();
		subP.setLayout(new GridLayout(3,1,5,5));

		
		subB1 = new JButton("회원탈퇴");
		subB1.addActionListener(this);
		subB1.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 15));
		subB1.setBackground(Color.WHITE);
		subB1.setForeground(new Color(38, 171, 255));
		
		subB2 = new JButton("회원 정보 변경");
		subB2.addActionListener(this);
		subB2.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 15));
		subB2.setBackground(Color.WHITE);
		subB2.setForeground(new Color(38, 171, 255));
		
		subB3 = new JButton("이용문의 : 010-9966-3417");
		subB3.setEnabled(false);
		subB3.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 15));
		subB3.setBackground(Color.WHITE);
		
		subP.add(subB1);
		subP.add(subB2);
		subP.add(subB3);
		
		mainP.add(mainL,BorderLayout.NORTH);
		mainP.add(subP,BorderLayout.CENTER);
		
		
		
		setVisible(true);
	}
	public static void main(String[] args) {
		 new helpP();
	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == subB1) {
			withdrawal with = new withdrawal(this);
		}
		if(o == subB2) {
			Alter al = new Alter(this);
		}
	}
}

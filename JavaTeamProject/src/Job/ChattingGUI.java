package Job;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ChattingGUI extends JFrame implements MouseListener{
	private JPanel mainP,leftP,rightP,leftP_UP,leftP_DP,leftP_DP_over,leftP_DP_under,rightP_UP,rightP_DP;
	private JLabel personlistL,roomlistL,chattingL;
	private JTextArea ta;
	private JScrollPane sp;
	private JButton notesendB,createB,sendB,joinB;
	private JList<String> personlist,roomlist;
	private JTextField noteTF;
	
	
	public ChattingGUI() {
		setTitle("온라인 채팅");
		setSize(1000, 600);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		mainP = new JPanel();
		mainP.setLayout(new BorderLayout());
		add(mainP);

		leftP = new JPanel();
		leftP.setLayout(new BorderLayout());
		leftP.setBorder(new LineBorder(new Color(38, 171, 255),2));
		
		leftP_UP = new JPanel();
		leftP_UP.setPreferredSize(new Dimension(250, 280));
		leftP_UP.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		leftP_UP.setLayout(new BorderLayout());
		
		leftP_DP = new JPanel();
		leftP_DP.setPreferredSize(new Dimension(250, 200));
		leftP_DP.setLayout(new BorderLayout());
		
		leftP_DP_over = new JPanel();
		leftP_DP_over.setPreferredSize(new Dimension(100, 210));
		leftP_DP_over.setLayout(new BorderLayout());
		
		leftP_DP_under = new JPanel();
		leftP_DP_under.setPreferredSize(new Dimension(200, 210));
		
		rightP = new JPanel();
		rightP.setBorder(BorderFactory.createEmptyBorder(0, 8, 2, 8));
		rightP.setLayout(new BorderLayout());
		
		rightP_UP = new JPanel();
		rightP_UP.setPreferredSize(new Dimension(100, 510));
		rightP_UP.setBorder(new LineBorder(new Color(38,171,255),2));
		rightP_UP.setLayout(new BorderLayout());
		
		rightP_DP = new JPanel();
		
		mainP.add(leftP,BorderLayout.WEST);
		mainP.add(rightP,BorderLayout.CENTER);
		
		leftP.add(leftP_UP,BorderLayout.NORTH);
		leftP.add(leftP_DP,BorderLayout.CENTER);
		
		rightP.add(rightP_UP, BorderLayout.NORTH);
		rightP.add(rightP_DP, BorderLayout.CENTER);
		
		personlistL = new JLabel("접속자 목록",SwingConstants.CENTER);
		personlistL.setBackground(new Color(38, 171, 255));
		personlistL.setOpaque(true);
		personlistL.setFont(new Font("맑은 고딕", Font.BOLD,20));
		
		personlist = new JList<>();
		personlist.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		personlist.setBackground(Color.WHITE);
		
		notesendB = new JButton("쪽지 보내기");
		notesendB.addMouseListener(this);
		notesendB.setBackground(Color.WHITE);
		notesendB.setForeground(new Color(38, 171, 255));
		notesendB.setPreferredSize(new Dimension(100, 40));
		notesendB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		
		
		leftP_UP.add(personlistL,BorderLayout.NORTH);
		leftP_UP.add(personlist,BorderLayout.CENTER);
		leftP_UP.add(notesendB,BorderLayout.SOUTH);
		
		roomlistL = new JLabel("방 목록",SwingConstants.CENTER);
		roomlistL.setBackground(new Color(38, 171, 255));
		roomlistL.setOpaque(true);
		roomlistL.setFont(new Font("맑은 고딕", Font.BOLD,20));
		
		roomlist = new JList<>();
		roomlist.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		roomlist.setBackground(Color.WHITE);
		
		joinB = new JButton("방 참여");
		joinB.addMouseListener(this);
		joinB.setBackground(Color.WHITE);
		joinB.setForeground(new Color(38, 171, 255));
		joinB.setPreferredSize(new Dimension(119, 60));
		joinB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		
		createB = new JButton("방 만들기");
		createB.addMouseListener(this);
		createB.setBackground(Color.WHITE);
		createB.setForeground(new Color(38, 171, 255));
		createB.setPreferredSize(new Dimension(119, 60));
		createB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		
		chattingL = new JLabel("채팅창",SwingConstants.CENTER);
		chattingL.setBackground(new Color(38, 171, 255));
		chattingL.setOpaque(true);
		chattingL.setFont(new Font("맑은 고딕", Font.BOLD,20));
		
		ta = new JTextArea(10,15);
		ta.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		ta.setEditable(false);
		
		sp = new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		noteTF = new JTextField(56);
		noteTF.setPreferredSize(new Dimension(100, 30));
		
		
		sendB = new JButton("▲");
		sendB.addMouseListener(this);
		sendB.setBackground(Color.WHITE);
		sendB.setForeground(new Color(38, 171, 255));
		sendB.setPreferredSize(new Dimension(80, 30));
		sendB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		
		rightP_UP.add(chattingL,BorderLayout.NORTH);
		rightP_UP.add(sp,BorderLayout.CENTER);
		
		rightP_DP.add(noteTF);
		rightP_DP.add(sendB);
		
		leftP_DP.add(leftP_DP_over,BorderLayout.NORTH);
		leftP_DP.add(leftP_DP_under,BorderLayout.CENTER);
		
		leftP_DP_over.add(roomlistL,BorderLayout.NORTH);
		leftP_DP_over.add(roomlist,BorderLayout.CENTER);

		leftP_DP_under.add(joinB);
		leftP_DP_under.add(createB);
		
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

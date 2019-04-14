package Job;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Login extends JFrame implements MouseListener{
	private	JPanel indP,mainP,rmP,contP,menuP,btnP;
	private	JLabel indL1,contL1,menuL1;
	private JButton pickB,resumeB,myresumeB,helpB,chattingB;
	private	static JList<String> list;
	private static DefaultListModel<String> Delist;
	private	JTextArea pA;
	private	JScrollPane sp;

	public Login() {
		setTitle("");
		setSize(1230, 600);
		setLocation(200, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainP = new JPanel();
		mainP.setLayout(new BorderLayout());

		indP = new JPanel();
		indP.setPreferredSize(new Dimension(150, 0));
		indP.setBorder(new LineBorder(new Color(38, 171, 255),2));
		indP.setLayout(new BorderLayout());

		indL1 = new JLabel("Category",SwingConstants.CENTER);
		indL1.setBackground(new Color(38, 171, 255));
		indL1.setOpaque(true);
		indL1.setFont(new Font("맑은 고딕", Font.BOLD,20));

		Delist = new DefaultListModel<String>();
		Delist.addElement("서비스업");
		Delist.addElement("제조·화학");
		Delist.addElement("IT·웹·통신");
		Delist.addElement("은행·금융업");
		Delist.addElement("미디어·디자인");
		Delist.addElement("교육업");
		Delist.addElement("의료·제약·복지");
		Delist.addElement("판매·유통");
		Delist.addElement("건설업");
		Delist.addElement("기관·협회");

		list = new JList<String>(Delist);
		list.addMouseListener(this);
		list.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		list.setBackground(Color.WHITE);

		indP.add(indL1,BorderLayout.NORTH);
		indP.add(list,BorderLayout.CENTER);

		rmP = new JPanel();
		rmP.setBorder(BorderFactory.createEmptyBorder(0,8,5,8));
		rmP.setLayout(new BorderLayout());
		rmP.setBackground(Color.LIGHT_GRAY);

		contP = new JPanel();
		contP.setPreferredSize(new Dimension(450, 450));
		contP.setBorder(new LineBorder(new Color(38, 171, 255),2));
		contP.setLayout(new BorderLayout());

		contL1 = new JLabel("Content",SwingConstants.CENTER);
		contL1.setBackground(new Color(38, 171, 255));
		contL1.setOpaque(true);
		contL1.setFont(new Font("맑은 고딕", Font.BOLD, 20));


		pA = new JTextArea(10,20);
		pA.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		pA.setText("각 직종별로 원하시는 분야를 선택하세요.");
		pA.setEditable(false);

		sp = new JScrollPane(pA,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contP.add(contL1,BorderLayout.NORTH);
		contP.add(sp,BorderLayout.CENTER);

		menuP = new JPanel();
		menuP.setPreferredSize(new Dimension(100, 100));
		menuP.setBorder(new LineBorder(new Color(38, 171, 255),2));
		menuP.setLayout(new BorderLayout());

		menuL1 = new JLabel("Menu",SwingConstants.CENTER);
		menuL1.setBackground(new Color(38, 171, 255));
		menuL1.setOpaque(true);
		menuL1.setFont(new Font("맑은 고딕", Font.BOLD, 20));

		btnP = new JPanel();
		btnP.setBackground(Color.WHITE);
		btnP.setPreferredSize(new Dimension(90, 70));

		pickB = new JButton("지역별 검색");
		pickB.addMouseListener(this);
		pickB.setBackground(Color.WHITE);
		pickB.setForeground(new Color(38, 171, 255));
		pickB.setPreferredSize(new Dimension(110, 60));
		pickB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));

		resumeB = new JButton("이력서");
		resumeB.addMouseListener(this);
		resumeB.setBackground(Color.WHITE);
		resumeB.setForeground(new Color(38, 171, 255));
		resumeB.setPreferredSize(new Dimension(99, 60));
		resumeB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));

		myresumeB = new JButton("내 이력서");
		myresumeB.addMouseListener(this);
		myresumeB.setBackground(Color.WHITE);
		myresumeB.setForeground(new Color(38, 171, 255));
		myresumeB.setPreferredSize(new Dimension(99, 60));
		myresumeB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));

		helpB = new JButton("고객센터");
		helpB.addMouseListener(this);
		helpB.setBackground(Color.WHITE);
		helpB.setForeground(new Color(38, 171, 255));
		helpB.setPreferredSize(new Dimension(99, 60));
		helpB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));

		chattingB = new JButton("온라인 채팅");
		chattingB.addMouseListener(this);
		chattingB.setBackground(Color.WHITE);
		chattingB.setForeground(new Color(38, 171, 255));
		chattingB.setPreferredSize(new Dimension(110, 60));
		chattingB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
		
		
		btnP.add(pickB);
		btnP.add(resumeB);
		btnP.add(myresumeB);
		btnP.add(helpB);
		btnP.add(chattingB);

		menuP.add(menuL1,BorderLayout.NORTH);
		menuP.add(btnP,BorderLayout.SOUTH);

		add(mainP);

		rmP.add(contP,BorderLayout.CENTER);
		rmP.add(menuP,BorderLayout.SOUTH);
		mainP.add(indP,BorderLayout.WEST);
		mainP.add(rmP,BorderLayout.CENTER);

		setVisible(true);
	}

	private static String getTagvalue(String tag , Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node)nlList.item(0);
		if(nValue == null)
			return null;
		return nValue.getNodeValue();
	}

	public void parse(int front, int rear) {

		int page = 1;
		
		try {
			while(true) {

				String url = "http://api.saramin.co.kr/job-search?ind_cd="+front+"01+"+rear+"02&fields=keyword-code";

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(url);

				doc.getDocumentElement().normalize();
				//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
				NodeList nList = doc.getElementsByTagName("job");
		
				//System.out.println("page number :" + page);

				
				
				

				for(int temp = 0; temp<nList.getLength(); temp++) {

					Node nNode = nList.item(temp);
					if(nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element)nNode;

						pA.append("-----------------------------------------------------------------------------------"
								+ "-----------------------------------------------------------------------------------"
								+ "-----------------------------------------------------------------------------------"+"\n");
						pA.append(
								"해당 페이지 : " + getTagvalue("url",eElement) +"\n"+
								"회사 : " + getTagvalue("name",eElement) +"\n"+
								"제목 : " + getTagvalue("title",eElement) +"\n"+ 
								"위치 : " + getTagvalue("location", eElement) + "\n"+
								"고용형태 : " + getTagvalue("job-type", eElement) + "\n" + 
								"업 직종 : " + getTagvalue("industry", eElement)+"\n" +
								"경력 : " + getTagvalue("experience-level",eElement)+"\n" +
								"학력 : " + getTagvalue("required-education-level",eElement)+"\n" +
								"급여 : " + getTagvalue("salary",eElement)+"\n" );
					
					}
					
				}
				page++;
				if(page>1) {
					break;
				}
			}
		}catch(Exception e1) {
			e1.printStackTrace();
		}

	}
	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			list = (JList)e.getSource();
			int index = list.locationToIndex(e.getPoint());

			switch(index) {
			
			case 0:{
				pA.setText("");
				parse(108,109);
				parse(121,122);
				parse(108,115);
				break;
			}case 1:{
				pA.setText("");
				parse(211,212);
				parse(202,204);
				parse(215,216);
				break;
			}case 2:{
				pA.setText("");
				parse(313,314);
				parse(307,308);
				break;
			}case 3:{
				pA.setText("");
				parse(402,405);
				break;
			}case 4:{
				pA.setText("");
				parse(502,503);
				parse(505,506);
				break;
			}case 5:{
				pA.setText("");
				parse(601,602);
				parse(606,605);
				break;
			}case 6:{
				pA.setText("");
				parse(701,702);
				parse(703,704);
				break;
			}case 7:{
				pA.setText("");
				parse(801,802);
				parse(803,804);
				break;
			}case 8:{
				pA.setText("");
				parse(901,902);
				break;
			}case 9:{
				pA.setText("");
				parse(1001,1002);
				parse(1002,1003);
				break;
			}
			
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
		if(o == pickB) {
			pickB.setBackground(new Color(38, 171, 255));
			pickB.setForeground(Color.WHITE);
		}
		if(o == resumeB) {
			resumeB.setBackground(new Color(38, 171, 255));
			resumeB.setForeground(Color.WHITE);
		}
		if(o == myresumeB) {
			myresumeB.setBackground(new Color(38, 171, 255));
			myresumeB.setForeground(Color.WHITE);
		}
		if(o == helpB) {
			helpB.setBackground(new Color(38, 171, 255));
			helpB.setForeground(Color.WHITE);
		}
		if(o == chattingB) {
			chattingB.setBackground(new Color(38,171,255));
			chattingB.setForeground(Color.WHITE);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if(o == pickB) {
			pickB.setBackground(Color.WHITE);
			pickB.setForeground(new Color(38, 171, 255));
		}
		if(o == resumeB) {
			resumeB.setBackground(Color.WHITE);
			resumeB.setForeground(new Color(38, 171, 255));
		}
		if(o == myresumeB) {
			myresumeB.setBackground(Color.WHITE);
			myresumeB.setForeground(new Color(38, 171, 255));
		}
		if(o == helpB) {
			helpB.setBackground(Color.WHITE);
			
			helpB.setForeground(new Color(38, 171, 255));
		}
		if(o == chattingB) {
			chattingB.setBackground(Color.WHITE);
			chattingB.setForeground(new Color(38,171,255));
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		if(o == helpB){
			new helpP();
		}
		if(o == resumeB) {
			new Resume();
		}
		if(o == myresumeB) {
			new MyResume();
		}
		if(o == pickB) {
			new pickP();
		}
		if(o == chattingB) {
			new EnterChattingFrame();
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}

package Job;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
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


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class pickP extends	JFrame implements MouseListener{
	private JPanel mainP,regionP,rightP,contentP;
	private JLabel regionL,contentL;
	private JTextArea dis_ta;
	private JScrollPane dis_sc;
	private DefaultListModel<String> Delist;
	private JList<String> regionList;
	
	public pickP() {
		setTitle("지역별 검색");
		setLocation(200, 100);
		setSize(1200, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		mainP = new JPanel();
		mainP.setLayout(new BorderLayout());
		add(mainP);
		
		rightP = new JPanel();
		rightP.setLayout(new BorderLayout());
		
		
		regionP = new JPanel();
		regionP.setPreferredSize(new Dimension(150, 0));
		regionP.setBorder(new LineBorder(new Color(38, 171, 255),2));
		regionP.setLayout(new BorderLayout());

		regionL = new JLabel("인천",SwingConstants.CENTER);
		regionL.setBackground(new Color(38, 171, 255));
		regionL.setOpaque(true);
		regionL.setFont(new Font("맑은 고딕", Font.BOLD,20));

		contentP = new JPanel();
		contentP.setPreferredSize(new Dimension(150, 0));
		contentP.setBorder(new LineBorder(new Color(38, 171, 255),2));
		contentP.setLayout(new BorderLayout());
		
		contentL = new JLabel("Content",SwingConstants.CENTER);
		contentL.setBackground(new Color(38, 171, 255));
		contentL.setOpaque(true);
		contentL.setFont(new Font("맑은 고딕", Font.BOLD,20));
		
		dis_ta = new JTextArea(10,20);
		dis_ta.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		dis_ta.setText("지역을 선택하세요.");
		dis_ta.setEditable(false);

		dis_sc = new JScrollPane(dis_ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		contentP.add(contentL,BorderLayout.NORTH);
		contentP.add(dis_sc,BorderLayout.CENTER);
		
		Delist = new DefaultListModel<String>();
		Delist.addElement("강화군");
		Delist.addElement("계양구");
		Delist.addElement("남구");
		Delist.addElement("남동구");
		Delist.addElement("동구");
		Delist.addElement("부평구");
		Delist.addElement("서구");
		Delist.addElement("연수구");
		Delist.addElement("웅진군");
		Delist.addElement("중구");

		regionList = new JList<String>(Delist);
		regionList.addMouseListener(this);
		regionList.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		regionList.setBackground(Color.WHITE);

		regionP.add(regionL,BorderLayout.NORTH);
		regionP.add(regionList,BorderLayout.CENTER);
	
		rightP.add(contentP,BorderLayout.CENTER);
		
		mainP.add(regionP,BorderLayout.WEST);
		mainP.add(rightP,BorderLayout.CENTER);
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
	
				String url = "http://api.saramin.co.kr/job-search?loc_cd="+front+"+"+rear+"&fields=keyword-code";
			
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

						dis_ta.append("-----------------------------------------------------------------------------------"
								+ "-----------------------------------------------------------------------------------"
								+ "-----------------------------------------------------------------------------------"+"\n");
						dis_ta.append(
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
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
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
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount() == 2) {
			regionList = (JList)e.getSource();
			int idx = regionList.locationToIndex(e.getPoint());
	
			switch(idx) {
			case 0:{
				dis_ta.setText("");
				parse(108010,108010);
				break;
			}case 1:{
				dis_ta.setText("");
				parse(108020,108020);
				break;
			}case 2:{
				dis_ta.setText("");
				parse(108030,108030);
				break;
			}case 3:{
				dis_ta.setText("");
				parse(108040,108040);
				break;
			}case 4:{
				dis_ta.setText("");
				parse(108050,108050);
				break;
			}case 5:{
				dis_ta.setText("");
				parse(108060,108060);
				break;
			}case 6:{
				dis_ta.setText("");
				parse(108070,108070);
				break;
			}case 7:{
				dis_ta.setText("");
				parse(108080,108080);
				break;
			}case 8: {
				dis_ta.setText("");
				parse(108090,108090);
				break;
			}case 9: {
				dis_ta.setText("");
				parse(108100,108100);
				break;
			}
			
			}
			
		}
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}

package Job;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class MyResume extends JFrame implements MouseListener, ActionListener{
	private JPanel mainP,norP,cenP,souP,nor_laP,nor_1P,sou_1P,sou_2P,sou_laP,sou_2P1,sou_2P2,cen_1P,cen_laP;
	private ImageIcon imgicon,change2;
	private JLabel imgL,cenL1,cenL2,cenL3,cenL4,cenL5,cenL6,cenL7,cenL8,cenL9,cen_1L1,souL1,souL2,souL3,souL4,souL5,souL6,sou_1L1,norL1,norL2,norL3,norL4,norL5,norL6,norL7,norL8,nor_1L1,nor_1L2;
	private JTextField centf1,centf2,centf3,centf4,centf5,centf6,centf7,centf8,centf9,soutf1,soutf2,soutf3,soutf4,soutf5,soutf6,nortf1,nortf2,nortf3,nortf4,nortf5,nortf6,nortf7,nortf8;
	private JButton saveB,closeB;
	private JMenu load;
	private JMenuItem image;
	private JMenuBar bar;
	private Image image1,change1;
	private ResultSet rs1;
	private Statement st1;
	private String sql1;
	private Connection conn;
	private String firname,surname,gend,addr,zip,birth,nation,phone,cp1,dt1,lev1,cp2,dt2,lev2,cp3,dt3,lev3,q1,q2,q3,q4,q5,q6,imgpath;
	
	
	public MyResume() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@127.0.0.1:1521:xe",
					"Job",
					"Job");

			st1 = conn.createStatement();

			sql1 = "select * from resume";

			rs1 = st1.executeQuery(sql1);

			while(rs1.next()) {
				firname = rs1.getString("FIRSTNAME");
				surname = rs1.getString("SURNAME");
				gend = rs1.getString("GENDER");
				addr = rs1.getString("ADDRESS");
				zip = rs1.getString("ZIPCODE");
				birth = rs1.getString("BIRTH");
				nation = rs1.getString("NATION");
				phone = rs1.getString("PHONE");
				cp1 = rs1.getString("CP1");
				dt1 = rs1.getString("DT1");
				lev1 = rs1.getString("LEV1");
				cp2 = rs1.getString("CP2");
				dt2 = rs1.getString("DT2");
				lev2 = rs1.getString("LEV2");
				cp3 = rs1.getString("CP3");
				dt3 = rs1.getString("DT3");
				lev3 = rs1.getString("LEV3");
				q1 = rs1.getString("Q1");
				q2 = rs1.getString("Q2");
				q3 = rs1.getString("Q3");
				q4 = rs1.getString("Q4");
				q5 = rs1.getString("Q5");
				q6 = rs1.getString("Q6");
				imgpath = rs1.getString("IMGPATH");
			}

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		
		setTitle("내 이력서");
		setSize(670,1040);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		mainP = new JPanel();
		mainP.setLayout(new BorderLayout());
		add(mainP);

		bar = new JMenuBar();
		setJMenuBar(bar);
		bar.setEnabled(false);

		load = new JMenu("load");
		bar.add(load);

		image = new JMenuItem("Image");
		image.addActionListener(this);
		load.add(image);

		norP = new JPanel();
		norP.setLayout(new BorderLayout());


		nor_laP = new JPanel();
		nor_laP.setLayout(new BorderLayout());

		nor_1L1 = new JLabel("              PERSONAL INFOMATION");
		nor_1L1.setPreferredSize(new Dimension(100, 100));
		nor_1L1.setFont(new Font("맑은 고딕", 1, 30));

		nor_laP.add(nor_1L1,BorderLayout.CENTER);

		imgicon = new ImageIcon(imgpath);
		image1 = imgicon.getImage();
		change1 = image1.getScaledInstance(300, 250, Image.SCALE_DEFAULT);
		change2= new ImageIcon(change1);
		imgL = new JLabel(change2);
		
		norP.add(nor_laP,BorderLayout.NORTH);
		norP.add(imgL,BorderLayout.EAST);

		nor_1P = new JPanel();
		nor_1P.setLayout(new GridLayout(8, 2,-100,10));

		norL1 = new JLabel("성");
		norL1.setFont(new Font("맑은 고딕", 10, 15));

		norL2 = new JLabel("이름");
		norL2.setFont(new Font("맑은 고딕", 10, 15));

		norL3 = new JLabel("성별");
		norL3.setFont(new Font("맑은 고딕", 10, 15));

		norL4 = new JLabel("주소");
		norL4.setFont(new Font("맑은 고딕", 10, 15));

		norL5 = new JLabel("우편번호");
		norL5.setFont(new Font("맑은 고딕", 10, 15));

		norL6 = new JLabel("생년월일");
		norL6.setFont(new Font("맑은 고딕", 10, 15));

		norL7 = new JLabel("국가");
		norL7.setFont(new Font("맑은 고딕", 10, 15));

		norL8 = new JLabel("전화번호");
		norL8.setFont(new Font("맑은 고딕", 10, 15));

		nortf1 = new JTextField(20);
		nortf1.setText(firname);
		nortf1.setEditable(false);
		
		nortf2 = new JTextField(20);
		nortf2.setText(surname);
		nortf2.setEditable(false);
		
		nortf3 = new JTextField(20);
		nortf3.setText(gend);
		nortf3.setEditable(false);
		
		nortf4 = new JTextField(20);
		nortf4.setText(addr);
		nortf4.setEditable(false);
		
		nortf5 = new JTextField(20);
		nortf5.setText(zip);
		nortf5.setEditable(false);
		
		nortf6 = new JTextField(20);
		nortf6.setText(birth);
		nortf6.setEditable(false);
		
		nortf7 = new JTextField(20);
		nortf7.setText(nation);
		nortf7.setEditable(false);
		
		nortf8 = new JTextField(20);
		nortf8.setText(phone);
		nortf8.setEditable(false);

		nor_1P.add(norL1);
		nor_1P.add(nortf1);
		nor_1P.add(norL2);
		nor_1P.add(nortf2);
		nor_1P.add(norL3);
		nor_1P.add(nortf3);
		nor_1P.add(norL4);
		nor_1P.add(nortf4);
		nor_1P.add(norL5);
		nor_1P.add(nortf5);
		nor_1P.add(norL6);
		nor_1P.add(nortf6);
		nor_1P.add(norL7);
		nor_1P.add(nortf7);
		nor_1P.add(norL8);
		nor_1P.add(nortf8);

		norP.add(nor_1P,BorderLayout.WEST);

		cenP = new JPanel();
		cenP.setLayout(new BorderLayout());

		cen_laP = new JPanel();
		cen_laP.setLayout(new BorderLayout());

		cen_1L1 = new JLabel("                  WORK EXPERIENCE");
		cen_1L1.setPreferredSize(new Dimension(100, 100));
		cen_1L1.setFont(new Font("맑은 고딕", 1, 30));

		cen_laP.add(cen_1L1,BorderLayout.CENTER);

		cenP.add(cen_laP,BorderLayout.NORTH);
		cen_1P = new JPanel();
		cen_1P.setLayout(new GridLayout(9, 2,-300,5));

		cenL1 = new JLabel("회사 이름");
		cenL1.setFont(new Font("맑은 고딕", 10, 15));

		cenL2 = new JLabel("기간");
		cenL2.setFont(new Font("맑은 고딕", 10, 15));

		cenL7 = new JLabel("직위");
		cenL7.setFont(new Font("맑은 고딕", 10, 15));

		cenL3 = new JLabel("회사 이름");
		cenL3.setFont(new Font("맑은 고딕", 10, 15));

		cenL4 = new JLabel("기간");
		cenL4.setFont(new Font("맑은 고딕", 10, 15));

		cenL8 = new JLabel("직위");
		cenL8.setFont(new Font("맑은 고딕", 10, 15));

		cenL5 = new JLabel("회사 이름");
		cenL5.setFont(new Font("맑은 고딕", 10, 15));

		cenL6 = new JLabel("기간");
		cenL6.setFont(new Font("맑은 고딕", 10, 15));

		cenL9 = new JLabel("직위");
		cenL9.setFont(new Font("맑은 고딕", 10, 15));

		centf1 = new JTextField(20);
		centf1.setText(cp1);
		centf1.setEditable(false);
		
		centf2 = new JTextField(20);
		centf2.setText(dt1);
		centf2.setEditable(false);
		
		centf7 = new JTextField(20);
		centf7.setText(lev1);
		centf7.setEditable(false);
		
		centf3 = new JTextField(20);
		centf3.setText(cp2);
		centf3.setEditable(false);
		
		centf4 = new JTextField(20);
		centf4.setText(dt2);
		centf4.setEditable(false);
		
		centf8 = new JTextField(20);
		centf8.setText(lev2);
		centf8.setEditable(false);
		
		centf5 = new JTextField(20);
		centf5.setText(cp3);
		centf5.setEditable(false);
		
		centf6 = new JTextField(20);
		centf6.setText(dt3);
		centf6.setEditable(false);
		
		centf9 = new JTextField(20);
		centf9.setText(lev3);
		centf9.setEditable(false);

		cen_1P.add(cenL1);
		cen_1P.add(centf1);		
		cen_1P.add(cenL2);
		cen_1P.add(centf2);
		cen_1P.add(cenL7);
		cen_1P.add(centf7);
		cen_1P.add(cenL3);
		cen_1P.add(centf3);
		cen_1P.add(cenL4);
		cen_1P.add(centf4);
		cen_1P.add(cenL8);
		cen_1P.add(centf8);
		cen_1P.add(cenL5);
		cen_1P.add(centf5);
		cen_1P.add(cenL6);
		cen_1P.add(centf6);
		cen_1P.add(cenL9);
		cen_1P.add(centf9);

		cenP.add(cen_1P,BorderLayout.CENTER);

		souP = new JPanel();
		souP.setLayout(new BorderLayout());

		sou_laP = new JPanel();
		sou_laP.setLayout(new BorderLayout());

		sou_1L1 = new JLabel("                     QULIFICATION");
		sou_1L1.setPreferredSize(new Dimension(100, 100));
		sou_1L1.setFont(new Font("맑은 고딕", 1, 30));

		sou_laP.add(sou_1L1,BorderLayout.CENTER);

		souP.add(sou_laP,BorderLayout.NORTH);

		sou_1P = new JPanel();
		sou_1P.setLayout(new GridLayout(6, 2,20,10));

		souL1 = new JLabel("자격증");
		souL1.setFont(new Font("맑은 고딕", 10, 15));

		souL2 = new JLabel("자격증");
		souL2.setFont(new Font("맑은 고딕", 10, 15));

		souL3 = new JLabel("자격증");
		souL3.setFont(new Font("맑은 고딕", 10, 15));

		souL4 = new JLabel("자격증");
		souL4.setFont(new Font("맑은 고딕", 10, 15));

		souL5 = new JLabel("자격증");
		souL5.setFont(new Font("맑은 고딕", 10, 15));

		souL6 = new JLabel("자격증");
		souL6.setFont(new Font("맑은 고딕", 10, 15));

		soutf1 = new JTextField(15);
		soutf1.setText(q1);
		soutf1.setEditable(false);
		
		soutf2 = new JTextField(15);
		soutf2.setText(q2);
		soutf2.setEditable(false);
		
		soutf3 = new JTextField(15);
		soutf3.setText(q3);
		soutf3.setEditable(false);
		
		soutf4 = new JTextField(15);
		soutf4.setText(q4);
		soutf4.setEditable(false);
		
		soutf5 = new JTextField(15);
		soutf5.setText(q5);
		soutf5.setEditable(false);
		
		soutf6 = new JTextField(15);
		soutf6.setText(q6);
		soutf6.setEditable(false);
		
		sou_1P.add(souL1);
		sou_1P.add(soutf1);

		sou_1P.add(souL2);
		sou_1P.add(soutf2);

		sou_1P.add(souL3);
		sou_1P.add(soutf3);

		sou_1P.add(souL4);
		sou_1P.add(soutf4);

		sou_1P.add(souL5);
		sou_1P.add(soutf5);

		sou_1P.add(souL6);
		sou_1P.add(soutf6);


		sou_2P = new JPanel();
		sou_2P.setLayout(new BorderLayout());

		sou_2P1 = new JPanel();
		sou_2P2 = new JPanel();

		sou_2P.add(sou_2P1,BorderLayout.CENTER);
		sou_2P.add(sou_2P2,BorderLayout.SOUTH);

		saveB = new JButton("저장");
		saveB.addActionListener(this);
		saveB.addMouseListener(this);
		saveB.setBackground(Color.WHITE);
		saveB.setForeground(new Color(38, 171, 255));
		saveB.setPreferredSize(new Dimension(99, 60));
		saveB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));

		closeB = new JButton("닫기");
		closeB.addActionListener(this);
		closeB.addMouseListener(this);
		closeB.setBackground(Color.WHITE);
		closeB.setForeground(new Color(38, 171, 255));
		closeB.setPreferredSize(new Dimension(99, 60));
		closeB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));

		sou_2P2.add(saveB);
		sou_2P2.add(closeB);

		souP.add(sou_1P,BorderLayout.WEST);
		souP.add(sou_2P,BorderLayout.EAST);

		mainP.add(norP,BorderLayout.NORTH);
		mainP.add(cenP,BorderLayout.CENTER);
		mainP.add(souP,BorderLayout.SOUTH);



		setResizable(false);
		setVisible(true);
	}

public static void main(String[] args) {
	new MyResume();
}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == closeB) {
			setVisible(false);
		}
		if(o == saveB) {
			Document document = new Document(PageSize.A4,30,30,30,30);
			
			try {
				PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("D:\\iTestText.pdf"));
				
				document.open();
				
				BaseFont fontB = BaseFont.createFont("HYGoThic-Medium","UniKS-UCS2-H",false);
				com.lowagie.text.Font font = new com.lowagie.text.Font(fontB,12);
				
				Paragraph titleP = new Paragraph("Resume",FontFactory.getFont(FontFactory.COURIER,40,Font.BOLD,new Color(0,0,0)));
				titleP.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(titleP);
				
				Paragraph line1 = new Paragraph("----------------------------------------------------------------------------------------",
						FontFactory.getFont(FontFactory.COURIER,20,Font.BOLD,new Color(0,0,0)));
				document.add(line1);
				
				Paragraph personalP = new Paragraph("PERSONAL INFORMATION",FontFactory.getFont(FontFactory.COURIER,25,Font.BOLD,new Color(0, 0, 0)));
				personalP.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(personalP);
				
				com.lowagie.text.Image image = com.lowagie.text.Image.getInstance(imgpath);
				image.setAlignment(image.TEXTWRAP);
				image.scaleAbsolute(120, 120);
				image.setAbsolutePosition(460,510);
				
				document.add(new Paragraph("성 : "+firname,font));
				document.add(image);
				document.add(new Paragraph("이름 : "+surname,font));
				document.add(new Paragraph("성별 : "+gend,font));
				document.add(new Paragraph("주소 : "+addr,font));
				document.add(new Paragraph("우편번호 : "+zip,font));
				document.add(new Paragraph("생년월일 : "+birth,font));
				document.add(new Paragraph("국가 : "+nation,font));
				document.add(new Paragraph("전화번호 : "+phone,font));
				
				Paragraph exP = new Paragraph("WORK EXPERIENCE",FontFactory.getFont(FontFactory.COURIER,25,Font.BOLD,new Color(0, 0, 0)));
				exP.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(exP);
				
				document.add(new Paragraph("회사 : "+cp1,font));
				document.add(new Paragraph("기간 : "+dt1,font));
				document.add(new Paragraph("직위 : "+lev1,font));
				document.add(new Paragraph("회사 : "+cp2,font));
				document.add(new Paragraph("기간 : "+dt2,font));
				document.add(new Paragraph("직위 : "+lev2,font));
				document.add(new Paragraph("회사 : "+cp3,font));
				document.add(new Paragraph("기간 : "+dt3,font));
				document.add(new Paragraph("직위 : "+lev3,font));
				
				Paragraph qulityP = new Paragraph("QULIFICATION",FontFactory.getFont(FontFactory.COURIER,25,Font.BOLD,new Color(0, 0, 0)));
				qulityP.setAlignment(Paragraph.ALIGN_CENTER);
				document.add(qulityP);
				
				document.add(new Paragraph("자격증 : "+q1,font));
				document.add(new Paragraph("자격증 : "+q2,font));
				document.add(new Paragraph("자격증 : "+q3,font));
				document.add(new Paragraph("자격증 : "+q4,font));
				document.add(new Paragraph("자격증 : "+q5,font));
				document.add(new Paragraph("자격증 : "+q6,font));
				
				Paragraph line2 = new Paragraph("----------------------------------------------------------------------------------------",
						FontFactory.getFont(FontFactory.COURIER,20,Font.BOLD,new Color(0,0,0)));
				document.add(line2);
				
				document.close();
		
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "저장 완료");
		}
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
		if(o == closeB) {
			closeB.setBackground(new Color(38, 171, 255));
			closeB.setForeground(Color.WHITE);
		}
		if(o == saveB) {
			saveB.setBackground(new Color(38, 171, 255));
			saveB.setForeground(Color.WHITE);
		}
	}



	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if(o == closeB) {
			closeB.setBackground(Color.WHITE);
			closeB.setForeground(new Color(38, 171, 255));
		}
		if(o == saveB) {
			saveB.setBackground(Color.WHITE);
			saveB.setForeground(new Color(38, 171, 255));
		}
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
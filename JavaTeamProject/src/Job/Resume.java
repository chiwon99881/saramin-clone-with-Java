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
import java.io.FileOutputStream;
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

public class Resume extends JFrame implements MouseListener, ActionListener{
	private JPanel mainP,norP,cenP,souP,nor_laP,nor_1P,sou_1P,sou_2P,sou_laP,sou_2P1,sou_2P2,cen_1P,cen_laP;
	private ImageIcon imgicon,change2;
	private JLabel imgL,cenL1,cenL2,cenL3,cenL4,cenL5,cenL6,cenL7,cenL8,cenL9,cen_1L1,souL1,souL2,souL3,souL4,souL5,souL6,sou_1L1,norL1,norL2,norL3,norL4,norL5,norL6,norL7,norL8,nor_1L1,nor_1L2;
	private JTextField centf1,centf2,centf3,centf4,centf5,centf6,centf7,centf8,centf9,soutf1,soutf2,soutf3,soutf4,soutf5,soutf6,nortf1,nortf2,nortf3,nortf4,nortf5,nortf6,nortf7,nortf8;
	private JButton clearB,saveB;
	private JMenu load;
	private JMenuItem image;
	private JMenuBar bar;
	private String filename = null;
	private Image image1,change1;
	private int x,y;
	
	
	
	public Resume() {
		setTitle("이력서");
		setSize(670,1040);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		mainP = new JPanel();
		mainP.setLayout(new BorderLayout());
		add(mainP);

		bar = new JMenuBar();
		setJMenuBar(bar);

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

		imgicon = new ImageIcon("images/face.jpg");
		image1 = imgicon.getImage();
		change1 = image1.getScaledInstance(300, 250, Image.SCALE_DEFAULT);
		change2= new ImageIcon(change1);
		imgL = new JLabel(change2);
		x = change2.getIconWidth();
		y = change2.getIconHeight();
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
		nortf2 = new JTextField(20);
		nortf3 = new JTextField(20);
		nortf4 = new JTextField(20);
		nortf5 = new JTextField(20);
		nortf6 = new JTextField(20);
		nortf7 = new JTextField(20);
		nortf8 = new JTextField(20);

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
		centf2 = new JTextField(20);
		centf3 = new JTextField(20);
		centf4 = new JTextField(20);
		centf5 = new JTextField(20);
		centf6 = new JTextField(20);
		centf7 = new JTextField(20);
		centf8 = new JTextField(20);
		centf9 = new JTextField(20);


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
		soutf2 = new JTextField(15);
		soutf3 = new JTextField(15);
		soutf4 = new JTextField(15);
		soutf5 = new JTextField(15);
		soutf6 = new JTextField(15);

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



		clearB = new JButton("리셋");
		clearB.addMouseListener(this);
		clearB.addActionListener(this);
		clearB.setBackground(Color.WHITE);
		clearB.setForeground(new Color(38, 171, 255));
		clearB.setPreferredSize(new Dimension(99, 60));
		clearB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));

		saveB = new JButton("저장");
		saveB.addMouseListener(this);
		saveB.addActionListener(this);
		saveB.setBackground(Color.WHITE);
		saveB.setForeground(new Color(38, 171, 255));
		saveB.setPreferredSize(new Dimension(99, 60));
		saveB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));

		sou_2P2.add(clearB);
		sou_2P2.add(saveB);

		souP.add(sou_1P,BorderLayout.WEST);
		souP.add(sou_2P,BorderLayout.EAST);

		mainP.add(norP,BorderLayout.NORTH);
		mainP.add(cenP,BorderLayout.CENTER);
		mainP.add(souP,BorderLayout.SOUTH);



		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Resume();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
		if(o == clearB) {
			clearB.setBackground(new Color(38, 171, 255));
			clearB.setForeground(Color.WHITE);
		}
		if(o == saveB) {
			saveB.setBackground(new Color(38, 171, 255));
			saveB.setForeground(Color.WHITE);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if(o == clearB) {
			clearB.setBackground(Color.WHITE);
			clearB.setForeground(new Color(38, 171, 255));
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
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o == image) {
			

			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(null);
			File f = chooser.getSelectedFile();

			filename =f.getAbsolutePath();
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT));  //SCLAE_DEFAULT => Use the default image-scaling algorithm.
			imgL.setIcon(imageIcon);

		}
		if(o == clearB) {
			nortf1.setText("");
			nortf2.setText("");
			nortf3.setText("");
			nortf4.setText("");
			nortf5.setText("");
			nortf6.setText("");
			nortf7.setText("");
			nortf8.setText("");
			
			centf1.setText("");
			centf2.setText("");
			centf3.setText("");
			centf4.setText("");
			centf5.setText("");
			centf6.setText("");
			centf7.setText("");
			centf8.setText("");
			centf9.setText("");
			
			soutf1.setText("");
			soutf2.setText("");
			soutf3.setText("");
			soutf4.setText("");
			soutf5.setText("");
			soutf6.setText("");
			
			imgL.setIcon(change2);
			
		}
		
		if(o == saveB) {
			
			
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:xe",
						"Job",
						"Job");

				String sur = nortf1.getText().trim();
				String fna = nortf2.getText().trim();
				String gen = nortf3.getText().trim();
				String addr = nortf4.getText().trim();
				String zip = nortf5.getText().trim();
				String bir = nortf6.getText().trim();
				String nat = nortf7.getText().trim();
				String ph = nortf8.getText().trim();
				
				String cp1 = centf1.getText().trim();
				String dt1 = centf2.getText().trim();
				String lv1 = centf3.getText().trim();
				String cp2 = centf4.getText().trim();
				String dt2 = centf5.getText().trim();
				String lv2 = centf6.getText().trim();
				String cp3 = centf7.getText().trim();
				String dt3 = centf8.getText().trim();
				String lv3 = centf9.getText().trim();
				
				String q1 = soutf1.getText().trim();
				String q2 = soutf2.getText().trim();
				String q3 = soutf3.getText().trim();
				String q4 = soutf4.getText().trim();
				String q5 = soutf5.getText().trim();
				String q6 = soutf6.getText().trim();
				String imgpath = filename;
			

				

			
				Statement st1 = conn.createStatement();


				String sql1 = "INSERT INTO resume(FIRSTNAME,SURNAME,GENDER,ADDRESS,ZIPCODE,BIRTH,NATION,PHONE,CP1,DT1,LEV1,CP2,DT2,LEV2,CP3,DT3,LEV3,Q1,Q2,Q3,Q4,Q5,Q6,IMGPATH) VALUES('"+sur+"','"+fna+"','"+gen+"','"+addr+"','"+zip+"','"+bir+"','"+nat+"','"+ph+"','"+cp1+"',"
						+ "'"+dt1+"','"+lv1+"','"+cp2+"','"+dt2+"','"+lv2+"','"+cp3+"','"+dt3+"','"+lv3+"','"+q1+"','"+q2+"','"+q3+"','"+q4+"','"+q5+"','"+q6+"','"+imgpath+"')";
				ResultSet rs1 = st1.executeQuery(sql1);
			

				int res = JOptionPane.showConfirmDialog(this, "저장되었습니다.","이력서",JOptionPane.CLOSED_OPTION);
			

			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e2) {	
				e2.printStackTrace();
			}

		}
	}

}


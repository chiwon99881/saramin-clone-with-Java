package Job;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import oracle.net.ns.SessionAtts;

public class StartF extends JFrame implements ActionListener, KeyListener{
	private	JPanel mainP;
	private ImageIcon job;
	private JButton login,signup;
	private JTextField id;
	private JPasswordField password;
	private JLabel label_id,label_password;
	private String id_DB,pw_DB;
	
	public StartF() {
		setTitle("구인구직");
		setSize(255, 400);
		setLocation(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		job = new ImageIcon("images/JOB.jpg");

		mainP = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(job.getImage(), 0, 0,d.width,d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		label_id = new JLabel("ID");
		label_id.setForeground(Color.BLACK);
		id = new JTextField(20);
		id.addKeyListener(this);

		label_password = new JLabel("PASSWORD");
		
		label_password.setForeground(Color.BLACK);
		password = new JPasswordField(20);
		password.addKeyListener(this);

		login = new JButton("LOGIN");
		login.setPreferredSize(new Dimension(50, 25));
		login.setBorder(new LineBorder(Color.BLACK));
		login.setBackground(Color.WHITE);
		login.setForeground(Color.BLACK);
		login.addActionListener(this);
		login.addKeyListener(this);

		signup = new JButton("SIGN UP");
		signup.setPreferredSize(new Dimension(50, 25));
		signup.setBorder(new LineBorder(Color.BLACK));
		signup.setBackground(Color.WHITE);
		signup.setForeground(Color.BLACK);
		signup.addActionListener(this);

		mainP.add(label_id);
		mainP.add(id);
		mainP.add(label_password);
		mainP.add(password);

		mainP.add(login);
		mainP.add(signup);

		add(mainP);
		setResizable(false);
		setVisible(true);
		login.requestFocus();
		password.requestFocus();
		id.requestFocus();

	}

	public static void main(String[] args) {
		new StartF();
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		Object o = e.getSource();
		if(o == signup) {
			SignUp sign= new SignUp(this);
		}
		
		if(o == login) {
			String iid = id.getText().trim();
			String ppw = password.getText().trim();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:xe",
						"Job",
						"Job");

				Statement st1 = conn.createStatement();

				String sql1 = "select * from USER_INFO where ID='"+iid+"' and PASSWORD='"+ppw+"'";

				ResultSet rs1 = st1.executeQuery(sql1);

/*				while(rs1.next()) {
					id_DB = rs1.getString("ID");
					pw_DB = rs1.getString("PASSWORD");
				}*/
				
					if(rs1.next()) {
						new Login();
						setVisible(false);
					}else{
						JOptionPane.showMessageDialog(this, "아이디 혹은 패스워드가 틀립니다.");
						id.setText("");
						password.setText("");
					}
				
				rs1.close();
				st1.close();
				conn.close();
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
	
		}

	}
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if(i== e.VK_ENTER) {
			String iid = id.getText().trim();
			String ppw = password.getText().trim();
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@127.0.0.1:1521:xe",
						"Job",
						"Job");

				Statement st1 = conn.createStatement();

				String sql1 = "select * from USER_INFO where ID='"+iid+"' and PASSWORD='"+ppw+"'";

				ResultSet rs1 = st1.executeQuery(sql1);

/*				while(rs1.next()) {
					id_DB = rs1.getString("ID");
					pw_DB = rs1.getString("PASSWORD");
				}*/
				
					if(rs1.next()) {
						new Login();
						setVisible(false);
					}else{
						JOptionPane.showMessageDialog(this, "아이디 혹은 패스워드가 틀립니다.");
						id.setText("");
						password.setText("");
					}
				
				rs1.close();
				st1.close();
				conn.close();
				
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
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

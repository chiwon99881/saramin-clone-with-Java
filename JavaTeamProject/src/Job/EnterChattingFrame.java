package Job;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.ImagingOpException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class EnterChattingFrame extends JFrame implements ActionListener, KeyListener{
   private JLabel ipL,portL,idL;
   private JTextField ipTF,portTF,idTF;
   private JButton enterB,cancelB;
   private JFrame Login_UI = new JFrame();
   
   
   private JPanel mainP,leftP,rightP,leftP_UP,leftP_DP,leftP_DP_over,leftP_DP_under,rightP_UP,rightP_DP;
   private JLabel personlistL,roomlistL,chattingL;
   private JTextArea ta;
   private JScrollPane sp;
   private JButton notesendB,createB,sendB,joinB;
   private JList<String> personlist,roomlist;
   private JTextField noteTF;
   
   private Socket clnt_sock;
   private String ip;
   private int port;
   private String id;
   private InputStream is;
   private OutputStream os;
   private DataInputStream dis;
   private DataOutputStream dos;
   
   Vector userlistV = new Vector();
   Vector roomlistV = new Vector();
   StringTokenizer st;
   
   private String My_Room; //내가 있는 방 이름
   
   private void Login() {

      Login_UI.setTitle("온라인 채팅");
      Login_UI.setSize(400, 200);
      Login_UI.setLocationRelativeTo(this);
      Login_UI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      Login_UI.setLayout(new GridLayout(4,2,0,5));
      
      ipL = new JLabel("Server IP ");
      ipL.setBackground(new Color(38, 171, 255));
      ipL.setForeground(Color.WHITE);
      ipL.setBorder(new LineBorder(new Color(38, 171, 255),2));
      ipL.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
      ipL.setOpaque(true);
      
      portL = new JLabel("Server PORT ");
      portL.setBackground(new Color(38, 171, 255));
      portL.setForeground(Color.WHITE);
      portL.setBorder(new LineBorder(new Color(38, 171, 255),2));
      portL.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
      portL.setOpaque(true);
      
      idL = new JLabel("성명: ");
      idL.setBackground(new Color(38, 171, 255));
      idL.setForeground(Color.WHITE);
      idL.setBorder(new LineBorder(new Color(38, 171, 255),2));
      idL.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
      idL.setOpaque(true);
      
      
      
      ipTF = new JTextField(15);
      portTF = new JTextField(15);
      
      idTF = new JTextField(15);
      
      enterB = new JButton("ENTER");
      enterB.addActionListener(this);
      enterB.setBackground(new Color(38, 171, 255));
      enterB.setForeground(Color.WHITE);
      enterB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
      
      cancelB = new JButton("CANCEL");
      cancelB.addActionListener(this);
      cancelB.setBackground(new Color(38, 171, 255));
      cancelB.setForeground(Color.WHITE);
      cancelB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
      
      Login_UI.add(ipL);
      Login_UI.add(ipTF);
      Login_UI.add(portL);
      Login_UI.add(portTF);
      Login_UI.add(idL);
      Login_UI.add(idTF);
      Login_UI.add(enterB);
      Login_UI.add(cancelB);
      
      Login_UI.setVisible(true);
   }
   
   public void Chatting() {
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
      personlist.setListData(userlistV);
      
      notesendB = new JButton("귓속말");
      notesendB.addActionListener(this);
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
      roomlist.setListData(roomlistV);
      
      joinB = new JButton("방 참여");
      joinB.addActionListener(this);
      joinB.setBackground(Color.WHITE);
      joinB.setForeground(new Color(38, 171, 255));
      joinB.setPreferredSize(new Dimension(119, 60));
      joinB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
      
      createB = new JButton("방 만들기");
      createB.addActionListener(this);
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
      noteTF.addKeyListener(this);
      noteTF.setEnabled(false);
      
      sendB = new JButton("▲");
      sendB.addActionListener(this);
      sendB.addKeyListener(this);
      sendB.setBackground(Color.WHITE);
      sendB.setForeground(new Color(38, 171, 255));
      sendB.setPreferredSize(new Dimension(80, 30));
      sendB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));
      sendB.setEnabled(false);
      
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
      
      sendB.requestFocus();
      noteTF.requestFocus();
      
      this.setResizable(false);
      this.setVisible(false);
   }
   public EnterChattingFrame() {
      Login();
      Chatting();
   }
   
   private void Network() {
      try {
         clnt_sock = new Socket(ip,port);
         
         if(clnt_sock != null) {
            Connection();
         }
      } catch (Exception e) {
    	  JOptionPane.showMessageDialog(null, "연결 실패","알림",JOptionPane.ERROR_MESSAGE);
      }
   }
   
   private void Connection() {
      try {
         is = clnt_sock.getInputStream();
         dis = new DataInputStream(is);
         
         os = clnt_sock.getOutputStream();
         dos = new DataOutputStream(os);

      } catch (IOException e) {
    	  JOptionPane.showMessageDialog(null, "방만들기 실패","알림",JOptionPane.ERROR_MESSAGE);
      }
      
      this.setVisible(true);
      this.Login_UI.setVisible(false);
      
      sendMessage(id);
      
      userlistV.add(id);
      
      Thread thread = new Thread(new Runnable() {
         @Override
         public void run() {
            while(true) {
               try {
                  String msg = dis.readUTF();
                  System.out.println("서버로부터 수신된 메세지 :" + msg );
                  
                  inmessage(msg);
                  
               } catch (IOException e) {
            	   
            	   try {
                  os.close();
                  is.close();
                  dos.close();
                  dis.close();
                  clnt_sock.close();
                  JOptionPane.showMessageDialog(null, "서버와 접속 끊어짐","알림",JOptionPane.ERROR_MESSAGE);
            	   }catch(IOException ee) {
            		   
            	   }
            	   break;
               }
            }
         }
      });
      thread.start();
   }

   private void inmessage(String str) { //서버로부터 들어오는 모든 메세지
      
      st = new StringTokenizer(str, "/");
      
      String protocol = st.nextToken();
      String Message = st.nextToken();
      
      System.out.println("프로토콜: " + protocol);
      System.out.println("내용 : " + Message);
      
      if(protocol.equals("NewUser")) { //새로운 접속자냐?
         userlistV.add(Message);
      }
      else if(protocol.equals("PreUser")) {
         userlistV.add(Message);
      }
      else if(protocol.equals("Note")) {
         String note = st.nextToken();
         
         System.out.println(Message + "님으로부터 온 쪽지:" + note);
         
         JOptionPane.showMessageDialog(null, note, Message+"님으로부터 온 쪽지", JOptionPane.CLOSED_OPTION);
      }
      else if(protocol.equals("userlistV_update")) {
         
         personlist.setListData(userlistV);
      }
      else if(protocol.equals("CreateRoom")) {//방만들었을때
    	  My_Room = Message;
    	  noteTF.setEnabled(true);
    	  sendB.setEnabled(true);
      }
      else if(protocol.equals("CreateRoomFail")) {//방만들기 실패했을때
    	  JOptionPane.showMessageDialog(null, "방만들기 실패","알림",JOptionPane.ERROR_MESSAGE);
      }
      else if(protocol.equals("New_Room")) {
    	  roomlistV.add(Message);
    	  roomlist.setListData(roomlistV);
      }
      else if(protocol.equals("Chatting")) {
    	  String msg = st.nextToken();
    	  ta.append(Message+": " +msg+"\n");
      }
      else if(protocol.equals("OldRoom")) {
    	  roomlistV.add(Message);
      }
      else if(protocol.equals("roomlistV_update")) {
    	  roomlist.setListData(roomlistV);
      }
      else if(protocol.equals("JoinRoom")) {
    	  My_Room = Message;
    	  noteTF.setEnabled(true);
    	  sendB.setEnabled(true);
    	  JOptionPane.showMessageDialog(null, "채팅방에 입장했습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
      }
      else if(protocol.equals("User_out")) {
    	  userlistV.remove(Message);
    	  ta.append("알림: =================="+ Message + "님이 퇴장하셨습니다.==================\n");
      }
   }
   private void sendMessage(String str) {
      try {
         dos.writeUTF(str);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public static void main(String[] args) {
      new EnterChattingFrame();
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Object o = e.getSource();
      
      if(o == enterB) {
    	  
    	  if(ipTF.getText().length()==0) {
    		  ipTF.setText("IP를 입력해주세요.");
    		  ipTF.requestFocus();
    	  }
    	  else if(portTF.getText().length() ==0) {
    		  portTF.setText("PORT를 입력해주세요.");
    		  portTF.requestFocus();
    	  }
    	  else if(idTF.getText().length() ==0) {
    		  idTF.setText("이름을 입력해주세요.");
    		  idTF.requestFocus();
    	  }
    	  else {
    	         ip = ipTF.getText().trim();
    	         port = Integer.parseInt(portTF.getText().trim());
    	         id = idTF.getText().trim();
    	         Network();
    	  }

      }
      if(o == cancelB) {
         Login_UI.setVisible(false);
      }
      if(o == notesendB) {
         System.out.println("a");
         String user = personlist.getSelectedValue();
         String note = JOptionPane.showInputDialog("보낼메세지");
         
         if(note != null) {
            sendMessage("Note/" + user + "/" + note); //ex Note/User1/나는 User2야
         }
         System.out.println("받는 사람: " + user + "||| 보낼 내용:" + note);
      }
      if(o == createB) {
         
    	  String roomname = JOptionPane.showInputDialog("방 이름");
    	  if(roomname != null) {
    		  sendMessage("CreateRoom/"+roomname);
    	  }
    	  System.out.println("b");
         
      }
      if(o == sendB) {
    	  sendMessage("Chatting/"+My_Room+"/"+noteTF.getText().trim());
    	  noteTF.setText("");
    	  //Chatting + 방 이름 + 내용
    	  
         System.out.println("c");
         
      }
      if(o == joinB) {
    	  
    	  String JoinRoom = (String)roomlist.getSelectedValue();
    	  
    	  sendMessage("JoinRoom/"+JoinRoom);
         System.out.println("d");
      }
   }

@Override
public void keyPressed(KeyEvent e) {
	if(e.getKeyCode() == e.VK_ENTER) {
		sendMessage("Chatting/"+My_Room+"/"+noteTF.getText().trim());
		noteTF.setText("");
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
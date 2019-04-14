package Job;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class Server extends JFrame implements ActionListener{
   private JPanel mainP,topP,bottomP,bottom_UP,bottom_DP;
   private JButton startB,endB;
   private JLabel portL,caL;
   private JTextField portF;
   private JTextArea cA;
   private JScrollPane sp;

   private ServerSocket server_sock;
   private Socket accept_sock;
   private int port;
/*   private InputStream is;
   private OutputStream os;
   private DataInputStream dis;
   private DataOutputStream dos;*/
   private Vector user_vc = new Vector();
   private Vector room_vc = new Vector();
   private StringTokenizer st;


   public Server() {
      setTitle("서버 ");
      setSize(400, 500);
      setLocationRelativeTo(this);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      mainP = new JPanel();
      mainP.setLayout(new BorderLayout());
      mainP.setBackground(Color.WHITE);
      add(mainP);

      topP = new JPanel();
      topP.setLayout(new BorderLayout());
      //topP.setBorder(BorderFactory.createEmptyBorder(8, 40, 8, 40));//위 왼쪽 아래 오른쪽
      topP.setPreferredSize(new Dimension(300, 350));
      topP.setBorder(new LineBorder(new Color(38,171,255),2));


      bottomP = new JPanel();

      bottomP.setLayout(new BorderLayout());

      bottom_UP = new JPanel();
      bottom_UP.setBackground(Color.WHITE);

      bottom_DP = new JPanel();
      bottom_DP.setBackground(Color.WHITE);

      bottomP.add(bottom_UP,BorderLayout.NORTH);
      bottomP.add(bottom_DP,BorderLayout.CENTER);

      mainP.add(topP,BorderLayout.NORTH);
      mainP.add(bottomP,BorderLayout.SOUTH);

      caL = new JLabel("서버",SwingConstants.CENTER);
      caL.setBackground(new Color(38,171,255));
      caL.setOpaque(true);
      caL.setFont(new Font("맑은 고딕",Font.BOLD,20));

      cA = new JTextArea(5,5);
      cA.setFont(new Font("맑은 고딕", Font.PLAIN,13));
      cA.setEditable(false);

      sp = new JScrollPane(cA,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      topP.add(caL,BorderLayout.NORTH);
      topP.add(sp,BorderLayout.CENTER);

      portL = new JLabel("포트번호:");
      portL.setFont(new Font("맑은고딕",Font.TYPE1_FONT,15));
      portL.setForeground(new Color(38,171,255));

      portF = new JTextField(12);

      bottom_UP.add(portL);
      bottom_UP.add(portF);

      startB = new JButton("서버 실행");
      startB.addActionListener(this);
      startB.setBackground(Color.WHITE);
      startB.setForeground(new Color(38, 171, 255));
      startB.setPreferredSize(new Dimension(99, 60));
      startB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));

      endB = new JButton("서버 중지");
      endB.addActionListener(this);
      endB.setBackground(Color.WHITE);
      endB.setForeground(new Color(38, 171, 255));
      endB.setPreferredSize(new Dimension(99, 60));
      endB.setFont(new Font("맑은 고딕", Font.TYPE1_FONT, 13));

      bottom_DP.add(startB);
      bottom_DP.add(endB);
      endB.setEnabled(false);
      setVisible(true);
   }

   private void Server_start() {

      try {
         server_sock = new ServerSocket(port);

         if(server_sock != null) {
            Connection();
         }

      } catch (IOException e) {
    	  JOptionPane.showMessageDialog(null, "이미사용중인 포트","알림",JOptionPane.ERROR_MESSAGE);
      }


   }

   private void Connection() {

      Thread thread = new Thread(new Runnable() {

         @Override
         public void run() {//스레드가 처리할 스레드의메인 부분
            while(true) {
               try {
                  cA.append("서버 구동중\n");
                  accept_sock = server_sock.accept();
                  cA.append("상대방 연결승인\n");
                  
                  UserInfo user = new UserInfo(accept_sock);
                  
                  user.start();
                  /*               
               is = accept_sock.getInputStream();
               dis = new DataInputStream(is);

               os = accept_sock.getOutputStream();
               dos = new DataOutputStream(os);

               String msg;
               msg = dis.readUTF();
               cA.append(msg);

               dos.writeUTF("접속 확인");
                   */
               } catch (IOException e) {
            	   break;
               }
            }//while문 끝
         }
      });
      thread.start();

   }

   public static void main(String[] args) {
      new Server();
   }


   @Override
   public void actionPerformed(ActionEvent e) {
      Object o = e.getSource();

      if(o == startB) {
         port = Integer.parseInt(portF.getText().trim());
         Server_start();
         
         startB.setEnabled(false);
         portF.setEditable(false);
         endB.setEnabled(true);
      }
      if(o == endB) {
    	  endB.setEnabled(false);
    	  startB.setEnabled(true);
          portF.setEditable(true);
          
    	  try {
    	  server_sock.close();
    	  user_vc.removeAllElements();
    	  room_vc.removeAllElements();
    	  }catch(IOException ee) {
    		  
    	  }
      }
   }
   
   class UserInfo extends Thread {
      private OutputStream os;
      private InputStream is;
      private DataOutputStream dos;
      private DataInputStream dis;
      
      private Socket user_socket;
      private String Nickname = "";
      private boolean RoomCh = true;
      
      public UserInfo(Socket sock) {
         this.user_socket = sock;
         
         UserNetwork();
      }
      
      private void UserNetwork() {
         try {
         is = user_socket.getInputStream();
         dis = new DataInputStream(is);
         
         os = user_socket.getOutputStream();
         dos = new DataOutputStream(os);
         
         Nickname = dis.readUTF();
         cA.append(Nickname + "님이 접속했습니다.\n");
         
         
         BroadCast("NewUser/"+Nickname);
         
         //새로운 사용자가 기존사용자를 알수있게끔 해줌
         for(int i=0; i<user_vc.size(); i++) {
            UserInfo u = (UserInfo)user_vc.elementAt(i);
            
            send_Message("PreUser/"+ u.Nickname);
         }
         
         for(int i=0; i<room_vc.size(); i++) {
        	 RoomInfo r = (RoomInfo)room_vc.elementAt(i);
        	 send_Message("OldRoom/"+r.Room_name);
         }
         send_Message("roomlistV_update/ ");
         
         user_vc.add(this); //사용자에게 알린 후 자신을 벡터에 추가
         
         BroadCast("userlistV_update/ ");
         
         }catch(Exception e) {
        	 JOptionPane.showMessageDialog(null, "Stream연결에러","알림",JOptionPane.ERROR_MESSAGE);
         }

      }

      public void run() {//Thread에서 처리할 부분
         while(true) {
            try {
               String msg = dis.readUTF();
               cA.append(Nickname+ ":" + msg+"\n");
               InMessage(msg);
            } catch (IOException e) {
            	cA.append(Nickname+"님이 접속종료하였습니다.\n");
            	try {
            	dos.close();
            	dis.close();
            	user_socket.close();
            	user_vc.remove(this);
            	BroadCast("User_out/"+Nickname);
            	BroadCast("userlistV_update/ ");
            	}catch(IOException ee) {
            		
            	}
            	break;
            }
         }
      }
      
      private void InMessage(String str) { //클라이언트로 부터 들어오는 메세지 처리
         st = new StringTokenizer(str, "/");
         String protocol = st.nextToken();
         String message = st.nextToken();
         
         System.out.println("프로토콜: " + protocol);
         System.out.println("메세지: " + message);
         
         if(protocol.equals("Note")) {
            
            String note = st.nextToken();
            
            System.out.println("받는사람: " + message);
            System.out.println("보낼내용: " + note);
            
            //벡터에서 해당 사용자 찾아서 메세지 전송
            for(int i=0; i<user_vc.size(); i++) {
               UserInfo u = (UserInfo)user_vc.elementAt(i);
               
               if(u.Nickname.equals(message)) {
                  u.send_Message("Note/"+Nickname+"/"+note);
               }
            }
         }
         else if(protocol.equals("CreateRoom")) {
        	 //현재 같은방이 존재하는지 확인할 필요가있음
        	 for(int i=0; i<room_vc.size(); i++) {
        		 RoomInfo r = (RoomInfo)room_vc.elementAt(i);
        		 
        		 if(r.Room_name.equals(message)) {//만들려는 방이 이미 존재할때
        			 send_Message("CreateRoomFail/ok");
        			 RoomCh = false;
        			 break;
        		 }
        		 
        	 }//for 끝
        	 if(RoomCh) {//방을 만들수 있을때 즉, 이미 방이 존재하지않을때
        		 RoomInfo new_room = new RoomInfo(message,this);
        		 room_vc.add(new_room); //전체 방 벡터에 방을 추가
        		 send_Message("CreateRoom/"+message);
        		 
        		 BroadCast("New_Room/"+message);
        	 }
        	 
        	 RoomCh = true;
         }
         else if(protocol.equals("Chatting")) {
        	 String msg = st.nextToken();
        	 for(int i=0; i<room_vc.size(); i++) {
        		 RoomInfo r = (RoomInfo)room_vc.elementAt(i);
        		 
        		 if(r.Room_name.equals(message)) { //해당방을 찾았을때
        			 r.BroadCast_Room("Chatting/"+Nickname+"/"+msg);
        		 }
        	 }
         }
         else if(protocol.equals("JoinRoom")) {
        	 for(int i=0; i<room_vc.size(); i++) {
        		 RoomInfo r =(RoomInfo)room_vc.elementAt(i);
        		 if(r.Room_name.equals(message)) {
        			
        			 //새로운 사용자가 방에 들어왔을때 기존사용자들에게 갈켜줌
        			 r.BroadCast_Room("Chatting/알림/=================="+Nickname+"님이 입장하셨습니다==================");
        			 
        			 
        			 //해당 방에 사용자 추가해줌
        			 r.Add_User(this);
        			 send_Message("JoinRoom/"+message);
        		 }
        	 }
         }
      }
      
      private void BroadCast(String str) {
         for(int i=0; i<user_vc.size(); i++) { //현재 접속된 사용자에게 새로운 사용자알림
            UserInfo u = (UserInfo)user_vc.elementAt(i);
            
            u.send_Message(str);
         }
      }
      private void send_Message(String str) { //문자열을 받아서 전송
         try {
            dos.writeUTF(str);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }//UserInfo 클래스 끝
   
   class RoomInfo {
	   private String Room_name;
	   private Vector Room_user_vc = new Vector();
	   
	   RoomInfo(String str, UserInfo u){
		   this.Room_name = str;
		   this.Room_user_vc.add(u);
	   }
	   
	  public void BroadCast_Room(String str){ //현재방의 모든사람에게 알림
		   for(int i=0; i<Room_user_vc.size(); i++) {
			   UserInfo u = (UserInfo)Room_user_vc.elementAt(i);
			   
			   u.send_Message(str);
		   }
	   }
	  private void Add_User(UserInfo u) {
		  this.Room_user_vc.add(u);
	  }
   }

}
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class guanli extends JFrame implements ActionListener, Runnable{
	
	ServerSocket s_socket;
	Socket c_socket;
	DataInputStream in=null;
	DataOutputStream out=null;
	InputStream in_data;
	OutputStream out_data;
	JTextArea txt1;
	JTextField txtport;
	String str;
	FileInputStream fillin;
	FileOutputStream fillout;
	List<Socket> list=new ArrayList<Socket>();
	
	guanli(){
		super("mp3_server");
		this.setSize(500, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		txt1=new JTextArea(5,4);
		add(txt1,BorderLayout.CENTER);
		this.validate();
		try{
			s_socket=new ServerSocket(4321);
			while(true){
				c_socket=s_socket.accept();
				Thread t=new Thread(this);
				t.start();
			}
		}
		catch(IOException e1){
			System.out.println("建立失败");
		}
	}
	
	public void run() {
		// TODO 自动生成的方法存根
		try{
			while(true){
					list.add(c_socket);
					in_data=c_socket.getInputStream();
					in=new DataInputStream(in_data);
					
					int ip=c_socket.getPort();
					str=in.readUTF();
					txt1.append("客户点播歌曲:   "+str+"\n");
					send();
					Thread.sleep(200);
			}
	
		}
		catch(IOException e2){}
		catch(Exception e3){}
	}
	
	private void send() {
		// TODO 自动生成的方法存根
		for(int k=0;k<list.size();k++){
			System.out.println("c_socket="+k);
			Socket c_socket=list.get(k);
		
			try {
				out_data=c_socket.getOutputStream();
				out=new DataOutputStream(out_data);
				out.writeUTF(str);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public static void main(String args[]){
		new guanli();
	}
}

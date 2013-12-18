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
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


@SuppressWarnings({ "serial", "unused" })
public class main extends JFrame implements ActionListener,Runnable{
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btn4;
	Socket socket;
	ServerSocket s_socket;
	FileInputStream fillin;
	FileOutputStream fillout;
	DataInputStream in=null;
	DataOutputStream out=null;
	InputStream in_data;
	OutputStream out_data;
	String str;
	@SuppressWarnings("rawtypes")
	JList jl;
	JLabel txt1;
	JButton btn5;
	JButton btn6;
	@SuppressWarnings("rawtypes")
	DefaultListModel listmode;
	JScrollPane sclist;
	mediaplayer1 b;
	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	main(){
		super("点歌系统");
		this.setVisible(true);
		this.setSize(500, 500);
		this.setLayout(null);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		btn1=new JButton("歌名点歌");
		add(btn1);
		btn1.setBounds(50,30,100,50);
		btn1.addActionListener(this);
		btn2=new JButton("歌手点歌");
		add(btn2);
		btn2.setBounds(50,135,100,50);
		btn2.addActionListener(this);
		btn3=new JButton("经典老歌");
		add(btn3);
		btn3.setBounds(50,240,100,50);
		btn3.addActionListener(this);
		btn4=new JButton("推荐新歌");
		add(btn4);
		btn4.setBounds(50,345,100,50);
		btn4.addActionListener(this);
		
		
		listmode=new DefaultListModel ();
		jl=new JList(listmode);
		 
		sclist=new JScrollPane(jl);
		/*sclist.setViewportView(jl);*/
		add(sclist);
		/*add(sclist);*/
		sclist.setBounds(220, 80, 200, 300);
		
		btn5=new JButton("播放");
		add(btn5);
		btn5.setBounds(210, 400, 100, 20);
		btn5.addActionListener(this);
		
		btn6=new JButton("删除");
		add(btn6);
		btn6.setBounds(320, 400, 100, 20);
		btn6.addActionListener(this);
		
		txt1=new JLabel("播放列表");
		add(txt1);
		txt1.setBounds(290, 30,150, 20);
		validate();
		b=new mediaplayer1("大城小爱");
		
		go_go();
	}
	
	
	


	private void go_go() {
		// TODO 自动生成的方法存根
		try{
			socket=new Socket("localhost",4321);
		}
		catch(IOException e1){
			System.out.println("找不到服务器");
		}
		try{
			in_data=socket.getInputStream();
			out_data=socket.getOutputStream();
			in=new DataInputStream(in_data);
			out=new DataOutputStream(out_data);
			int p1=socket.getPort();
			int p2=socket.getLocalPort();
		}
		catch(IOException e2){
			System.out.println("建立输入输出流错误");
		}
		Thread t=new Thread(this);
		t.start();
	}


	public void run() {
		// TODO 自动生成的方法存根
		try{
			while(true){
				str=in.readUTF();
				System.out.println(str);
				listmode.addElement(str);
				jl.updateUI();
				Thread.sleep(200);
			}
		}
		catch(IOException e2){}
		catch(Exception e3){}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource().equals(btn1)){
			GeQuChose gequchose=new GeQuChose();
		}
		if(e.getSource().equals(btn2)){
			GeShouChose geshouchose=new GeShouChose();
		}
		if(e.getSource().equals(btn3)){
			JingDian jingdian=new JingDian();
		}
		if(e.getSource().equals(btn4)){
			TuiJian tuijian=new TuiJian();
		}
		
		
		if(e.getSource().equals(btn5)){
			String str3=null;
			str3= (String) jl.getSelectedValue();
			listmode.removeElement(str3);
			jl.updateUI();
			if(str3==null){
				b.close();
			}else{
				b.close();
				b=new mediaplayer1(str3);
			}
			
			
		}
		
		if(e.getSource().equals(btn6)){
			String str3=null;
			str3= (String) jl.getSelectedValue();
			listmode.removeElement(str3);
			jl.updateUI();
		}
	}
	public static void main(String args[]){
		new main();
		
	}

	
	

	
}

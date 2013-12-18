import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;




public class JingDian extends JFrame implements ActionListener{
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JButton btn4;
	JButton btn5;
	JList jlist;
	JScrollPane sclist;
	String name;
	DataInputStream in=null;
	DataOutputStream out=null;
	Socket socket;
	InputStream in_data;
	OutputStream out_data;
	FileInputStream filein;
	FileOutputStream fileout;
	
	JingDian(){
		super("经典老歌");
		this.setSize(300,500);
		this.setVisible(true);
		this.setLayout(null);
		btn1=new JButton("70年代");
		add(btn1);
		btn1.setBounds(30, 20, 100, 30);
		btn1.addActionListener(this);
		
		btn2=new JButton("80年代");
		add(btn2);
		btn2.setBounds(150, 20, 100, 30);
		btn2.addActionListener(this);
		
		btn3=new JButton("90年代");
		add(btn3);
		btn3.setBounds(30, 60, 100, 30);
		btn3.addActionListener(this);
		
		btn4=new JButton("00年代");
		add(btn4);
		btn4.setBounds(150, 60, 100, 30);
		btn4.addActionListener(this);
		
		btn5=new JButton("选择歌曲");
		add(btn5);
		btn5.setBounds(90, 400, 100, 30);
		btn5.addActionListener(this);
		
		jlist=new JList();
		sclist=new JScrollPane(jlist);
		add(sclist);
		sclist.setBounds(30,100,200,280);
		DataModel dm=new DataModel();
		jlist.setModel(dm);
		this.validate();
		go_go3();
	}
	
	
	private void go_go3() {
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
		}
		catch(IOException e2){
			System.out.println("建立输入输出流错误");
		}
	}
	
	class DataModel extends DefaultListModel {
        DataModel() {
        	SelectClass shuchu=new SelectClass();
        	String str1=shuchu.SelectNdName(name);
        	if(str1!=null){
        		Pattern pattern1=Pattern.compile(",");
        		String[] str2=pattern1.split(str1);
        		for(int i=1;i<str2.length;i=i+2){
        			addElement(str2[i]);
        			/*System.out.println(str2[i]+",");*/
        		}
        	}else{
        		addElement("对不起没有你查找的歌曲");
        	}
             
        }
    }
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource().equals(btn1)){
			name="70";
			DataModel dm=new DataModel();
			jlist.setModel(dm);
		}
		if(e.getSource().equals(btn2)){
			name="80";
			DataModel dm=new DataModel();
			jlist.setModel(dm);
		}
		if(e.getSource().equals(btn3)){
			name="90";
			DataModel dm=new DataModel();
			jlist.setModel(dm);
		}
		if(e.getSource().equals(btn4)){
			name="00";
			DataModel dm=new DataModel();
			jlist.setModel(dm);
		}
		if(e.getSource().equals(btn5)){
			String xuanzhong=(String) jlist.getSelectedValue();
			/*System.out.println(xuanzhong+",,,");*/
			try{
				/*	str1=in.readUTF();*/
					out.writeUTF(xuanzhong);	
					/*in.close();
					out.close();
					socket.close();*/
				
				}
				catch(IOException e3){
					System.out.println("线路读取错误");
				}
		}
	}
	public static void main(String args[]){
		new JingDian();
	}
}

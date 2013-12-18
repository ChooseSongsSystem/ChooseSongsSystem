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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class GeShouChose extends JFrame implements ActionListener{
	JLabel jlabel;
	JTextField txt1;
	JButton btn1;
	JButton btn2;
	JButton btn3;
	JList jlist1;
	JList jlist2;
	JScrollPane sclist1;
	JScrollPane sclist2;
	String namego;
	Socket socket;
	InputStream in_data;
	OutputStream out_data;
	FileInputStream filein;
	FileOutputStream fileout;
	DataInputStream in=null;
	DataOutputStream out=null;
	
	
	GeShouChose(){
		super("按歌手选歌");
		this.setSize(500, 500);
		this.setVisible(true);
		this.setLayout(null);
		jlabel=new JLabel("请在下面输入你要选歌歌曲:");
		add(jlabel);
		jlabel.setBounds(30,10,240,30);
		txt1=new JTextField();
		add(txt1);
		txt1.setBounds(30,60,200,30);
		btn1=new JButton("查询歌手");
		add(btn1);
		btn1.setBounds(30, 110, 100, 30	);
		btn1.addActionListener(this);
		btn2=new JButton("选取歌手");
		add(btn2);
		btn2.setBounds(170, 110, 100, 30);
		btn2.addActionListener(this);
		btn3=new JButton("选择歌曲");
		add(btn3);
		btn3.setBounds(310, 110, 100, 30);
		btn3.addActionListener(this);
		
		jlist1=new JList();
		sclist1=new JScrollPane(jlist1);
		add(sclist1);
		sclist1.setBounds(30,160,200,280);
		DataModel dm=new DataModel();
		jlist1.setModel(dm);
		
		jlist2=new JList();
		sclist2=new JScrollPane(jlist2);
		add(sclist2);
		sclist2.setBounds(250,160,200,280);
		DataModel1 dm1=new DataModel1();
		jlist2.setModel(dm1);
		
		
		
		this.validate();
		go_go2();
	}
	
	
	private void go_go2() {
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
        	String name=txt1.getText();
        	SelectClass shuchu1=new SelectClass();
        	String str1=shuchu1.SelectGsName(name);
        	/*System.out.println(str1);*/
        	if(str1!=null){
        		Pattern pattern1=Pattern.compile(",");
        		String[] str2=pattern1.split(str1);
        		for(int i=0;i<str2.length;i++){
        			addElement(str2[i]);
        			/*System.out.println(str2[i]);*/
        		}
        	}else{
        		addElement("对不起没有你查找的歌手");
        	}
             
        }
    }
	
	class DataModel1 extends DefaultListModel {
        DataModel1() {
        	
        	SelectClass shuchu=new SelectClass();
        	String str1=shuchu.SelectGsqName(namego);
        	if(str1!=null){
        		Pattern pattern1=Pattern.compile(",");
        		String[] str2=pattern1.split(str1);
        		for(int i=1;i<str2.length;i=i+2){
        			addElement(str2[i]);
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
			DataModel dm=new DataModel();
			jlist1.setModel(dm);
		}
		if(e.getSource().equals(btn2)){
			
			namego= (String) jlist1.getSelectedValue();
			/*System.out.println(namego+"464");*/
			DataModel1 dm1=new DataModel1();
			jlist2.setModel(dm1);
		}
		if(e.getSource().equals(btn3)){
			String xuanzhong=(String) jlist2.getSelectedValue();
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
		new GeShouChose();
	}
}

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




public class TuiJian extends JFrame implements ActionListener{

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
	
	TuiJian(){
		super("推荐新歌");
		this.setSize(300,500);
		this.setVisible(true);
		this.setLayout(null);
		
		
		btn5=new JButton("选择歌曲");
		add(btn5);
		btn5.setBounds(90, 400, 100, 30);
		btn5.addActionListener(this);
		
		jlist=new JList();
		sclist=new JScrollPane(jlist);
		add(sclist);
		sclist.setBounds(30,20,200,360);
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
        	String str1=shuchu.SelectNdName("10");
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
		new TuiJian();
	}
}

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
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class GeQuChose extends JFrame implements ActionListener{
	@SuppressWarnings("rawtypes")
	JList jlist;
	JButton btn1;
	JButton btn2;
	JTextField txt1;
	JLabel jlabel;
	String xuanzhong;
	int i;
	DataInputStream in=null;
	DataOutputStream out=null;
	Socket socket;
	InputStream in_data;
	OutputStream out_data;
	FileInputStream filein;
	FileOutputStream fileout;
	String str;
	@SuppressWarnings("rawtypes")
	DefaultListModel listmode;
	JScrollPane sclist;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	GeQuChose(){
		super("按歌名选歌");
		this.setSize(300, 500);
		this.setVisible(true);
		//this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLayout(null);
		jlabel=new JLabel("请在下面输入你要选歌歌曲:");
		add(jlabel);
		jlabel.setBounds(30,10,240,30);
		txt1=new JTextField();
		add(txt1);
		txt1.setBounds(30,60,200,30);
		btn1=new JButton("查询");
		add(btn1);
		btn1.setBounds(30, 110, 100, 30	);
		btn1.addActionListener(this);
		btn2=new JButton("选取");
		add(btn2);
		btn2.setBounds(140, 110, 100, 30);
		btn2.addActionListener(this);
		
		jlist=new JList();
		sclist=new JScrollPane(jlist);
		add(sclist);
		sclist.setBounds(30,160,200,280);
		/*DefaultListModel listModel= (DefaultListModel) jlist.getModel();
		listModel.add(listModel.getSize(),"I am Fly");*/
		DataModel dm=new DataModel();
		jlist.setModel(dm);
		this.validate();
		go_go1();
	}
	
	
	
	
	private void go_go1() {
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




	@SuppressWarnings("rawtypes")
	class DataModel extends DefaultListModel {
        @SuppressWarnings("unchecked")
		DataModel() {
        	String name=txt1.getText();
        	SelectClass shuchu=new SelectClass();
        	String str1=shuchu.SelectGqName(name);
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
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource().equals(btn1)){
			DataModel dm=new DataModel();
			jlist.setModel(dm);
		}
		if(e.getSource().equals(btn2)){
			xuanzhong=(String) jlist.getSelectedValue();
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
		GeQuChose a=new GeQuChose();
	}
}

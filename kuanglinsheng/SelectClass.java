import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectClass {
	public static Connection con;
	static String gqname = null;
	static String gsname = null;
	static String gsqname = null;
	static String ndname = null;
	
	
	String SelectGqName(String gmname){
		String gqname1 = null;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=JavaTest","jtt","123");
			Statement s=con.createStatement();
			/*String query="create table student(id char(10),name char(15),score integer)";
			s.executeUpdate(query);*/
			/*System.out.print("ok");*/
			if(gmname!=null){
				ResultSet rs=s.executeQuery("select ����  from Songs where  ����  LIKE '%"+gmname+"%' ;");
				while(rs.next()){
					
					gqname=rs.getString("����");
					gqname1=gqname+ gqname1;
					
				}
			}
			else{
				ResultSet rs=s.executeQuery("select ����  from Songs ");
				while(rs.next()){
					gqname=rs.getString("����");
					gqname1=gqname+gqname1;
					
					
				}
			}
			s.close();
			con.close();
		}
		catch(Exception e){
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
		return gqname1;
	}
	String SelectGsName(String name){
		String gsname1=null;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=JavaTest","jtt","123");
			Statement s=con.createStatement();
			/*String query="create table student(id char(10),name char(15),score integer)";
			s.executeUpdate(query);*/
			
			if(name!=null){
				ResultSet rs=s.executeQuery("select DISTINCT ����  from Songs where  ����  LIKE '%"+name+"%' ;");
				while(rs.next()){
					
					gsqname=rs.getString("����");
					gsname1=gsqname+","+gsname1;
					
				}
			}
			else{
				ResultSet rs=s.executeQuery("select ISTINCT ����  from Songs ");
				while(rs.next()){
					gsname=rs.getString("����");
					gsname1=gsname+","+gsname1;
					
					
				}
			}
			s.close();
			con.close();
		}
		catch(Exception e){
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
		return gsname1;
	}
	String SelectGsqName(String name){
		String gsqname1=null;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=JavaTest","jtt","123");
			Statement s=con.createStatement();
			/*String query="create table student(id char(10),name char(15),score integer)";
			s.executeUpdate(query);*/
			
			if(name!=null){
				ResultSet rs=s.executeQuery("select  ����  from Songs where  ���� ='"+name+"';");
				while(rs.next()){
					
					gsqname=rs.getString("����");
					gsqname1=gsqname+ gsqname1;
					
				}
			}
			else{
				ResultSet rs=s.executeQuery("select  ����  from Songs ");
				while(rs.next()){
					gsqname=rs.getString("����");
					gsqname1=gsqname+gsqname1;
					
					
				}
			}
			s.close();
			con.close();
		}
		catch(Exception e){
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
		return gsqname1;
	}
	String SelectNdName(String name){
		// TODO �Զ����ɵķ������
		String ndname1=null;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=JavaTest","jtt","123");
			Statement s=con.createStatement();
			/*String query="create table student(id char(10),name char(15),score integer)";
			s.executeUpdate(query);*/
			
			if(name!=null){
				ResultSet rs=s.executeQuery("select  ����  from Songs where  ���='"+name+"';");
				while(rs.next()){
					
					ndname=rs.getString("����");
					ndname1=ndname+ ndname1;
					
				}
			}
			else{
				ResultSet rs=s.executeQuery("select  ����  from Songs ");
				while(rs.next()){
					gsqname=rs.getString("����");
					ndname1=ndname+ndname1;
					
					
				}
			}
			s.close();
			con.close();
		}
		catch(Exception e){
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
		
		return ndname1;
	}
	public static void main(String args[]){
		SelectClass a=new SelectClass();
		String str2=a.SelectGsName("��");
		System.out.println(str2);
		
	}
	
}
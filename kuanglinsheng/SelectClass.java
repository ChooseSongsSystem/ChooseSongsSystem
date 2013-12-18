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
				ResultSet rs=s.executeQuery("select 歌名  from Songs where  歌名  LIKE '%"+gmname+"%' ;");
				while(rs.next()){
					
					gqname=rs.getString("歌名");
					gqname1=gqname+ gqname1;
					
				}
			}
			else{
				ResultSet rs=s.executeQuery("select 歌名  from Songs ");
				while(rs.next()){
					gqname=rs.getString("歌名");
					gqname1=gqname+gqname1;
					
					
				}
			}
			s.close();
			con.close();
		}
		catch(Exception e){
			System.out.println("链接失败");
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
				ResultSet rs=s.executeQuery("select DISTINCT 歌手  from Songs where  歌手  LIKE '%"+name+"%' ;");
				while(rs.next()){
					
					gsqname=rs.getString("歌手");
					gsname1=gsqname+","+gsname1;
					
				}
			}
			else{
				ResultSet rs=s.executeQuery("select ISTINCT 歌手  from Songs ");
				while(rs.next()){
					gsname=rs.getString("歌手");
					gsname1=gsname+","+gsname1;
					
					
				}
			}
			s.close();
			con.close();
		}
		catch(Exception e){
			System.out.println("链接失败");
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
				ResultSet rs=s.executeQuery("select  歌名  from Songs where  歌手 ='"+name+"';");
				while(rs.next()){
					
					gsqname=rs.getString("歌名");
					gsqname1=gsqname+ gsqname1;
					
				}
			}
			else{
				ResultSet rs=s.executeQuery("select  歌名  from Songs ");
				while(rs.next()){
					gsqname=rs.getString("歌名");
					gsqname1=gsqname+gsqname1;
					
					
				}
			}
			s.close();
			con.close();
		}
		catch(Exception e){
			System.out.println("链接失败");
			e.printStackTrace();
		}
		return gsqname1;
	}
	String SelectNdName(String name){
		// TODO 自动生成的方法存根
		String ndname1=null;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=JavaTest","jtt","123");
			Statement s=con.createStatement();
			/*String query="create table student(id char(10),name char(15),score integer)";
			s.executeUpdate(query);*/
			
			if(name!=null){
				ResultSet rs=s.executeQuery("select  歌名  from Songs where  年代='"+name+"';");
				while(rs.next()){
					
					ndname=rs.getString("歌名");
					ndname1=ndname+ ndname1;
					
				}
			}
			else{
				ResultSet rs=s.executeQuery("select  歌名  from Songs ");
				while(rs.next()){
					gsqname=rs.getString("歌名");
					ndname1=ndname+ndname1;
					
					
				}
			}
			s.close();
			con.close();
		}
		catch(Exception e){
			System.out.println("链接失败");
			e.printStackTrace();
		}
		
		return ndname1;
	}
	public static void main(String args[]){
		SelectClass a=new SelectClass();
		String str2=a.SelectGsName("敬");
		System.out.println(str2);
		
	}
	
}
package open;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.io.*;
import java.util.Date;
public class Open {
	
	public static void main(String args[]) throws IOException,SQLException
	{
	
	String url="jdbc:mysql://localhost:3306/Assignment";
		String username="root";
		String password="password";
		int ch;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c=DriverManager.getConnection(url,username,password);
			//System.out.println("Connection  established");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			PreparedStatement ps=c.prepareStatement("insert into STUDENT values(?,?,?,?)");
			PreparedStatement ps1=c.prepareStatement("delete from STUDENT where STUDENT_NO=?");
			PreparedStatement ps2=c.prepareStatement("update STUDENT set STUDENT_NAME=? where STUDENT_NO=?");
			if(c==null)
			{
				System.out.println("Connection not established");
			}
			else
			{
				System.out.println("Connection  established");
				Statement stmt=c.createStatement();
				 do
			     {
				System.out.println("1.Create"); 
				System.out.println("2.View");
				System.out.println("3.Delete");
				System.out.println("4.Update");
				System.out.println("5.Search");
				System.out.println("6.Exit");
				System.out.println("Enter choise :");
				 ch =Integer.parseInt(br.readLine());
				switch(ch)
				{
					case 1: 
						System.out.println("Enter Student  Number :");
						int STUDENT_NO=Integer.parseInt(br.readLine());

						System.out.println("Enter Name :");
						String STUDENT_NAME=br.readLine();

						System.out.println("Enter date of birth :");
						String STUDENT_DOB=br.readLine();
						
						SimpleDateFormat sdf1=new SimpleDateFormat("dd/mm/yyyy");
						java.util.Date udob1=sdf1.parse(STUDENT_DOB); //util date class object
						long ms1=udob1.getTime();
						java.sql.Date sqdob1=new java.sql.Date(ms1); // SQL date class object

						System.out.println("Enter date of joining:");
						String STUDENT_DOJ=br.readLine();
						
						SimpleDateFormat sdf2=new SimpleDateFormat("dd/mm/yyyy");
						java.util.Date udob2=sdf2.parse(STUDENT_DOJ); //util date class object
						long ms2=udob2.getTime();
						java.sql.Date sqdob2=new java.sql.Date(ms2); // SQL date class object
						
						ps.setInt(1,STUDENT_NO);
						ps.setString(2,STUDENT_NAME);
						ps.setDate(3,sqdob1);
						ps.setDate(4, sqdob2);
						
						ps.executeUpdate();
						break;
				
					
						
					case 2:
						ResultSet rs=stmt.executeQuery("Select * from STUDENT1");
						while(rs.next())
						{
							System.out.println("Student  number:"+rs.getInt(1));
							System.out.println("Student Name:"+rs.getString(2));
							System.out.println("Student DOB :"+rs.getDate(3));
							System.out.println("Student date of joining :"+ rs.getDate(4));
							
						}
						break;	
						
					case 3:
						System.out.println("Enter Roll Number for delete record :");
						STUDENT_NO=Integer.parseInt(br.readLine());
						ps1.setInt(1,STUDENT_NO);
						ps1.executeUpdate();
						break;
						
						
					case 4:
						System.out.println("Enter Roll Number :");
						 STUDENT_NO=Integer.parseInt(br.readLine());
						System.out.println("Enter name to modify:");
						 String name=br.readLine();
						 ps2.setInt(2,STUDENT_NO);
						ps2.setString(1,name);
						ps2.executeUpdate();
						break;
						
					case 5:
						System.out.println("Enter Roll Number for search record :");
						Integer rno=Integer.parseInt(br.readLine());
						rs=stmt.executeQuery("Select STUDENT_NAME,STUDENT_DOB,STUDENT_DOJ from STUDENT where STUDENT_NO="+rno);
						while(rs.next())
						{
							
							System.out.println("Student Name:"+rs.getString(1));
							System.out.println("Date of birth:"+rs.getDate(2));
							System.out.println("Date of joining is:"+rs.getDate(3));
						}
						break;
						
					case 6:
						System.exit(0);
						break;					
				}
				
		             }while(ch<6);	
				c.close();	
			}
			
			
		}
		
	
	catch(Exception e)
	{
		System.out.println(e);
	}
	}

}

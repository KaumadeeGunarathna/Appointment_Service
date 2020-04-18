package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {

	private Connection connect()  
	{
		Connection con = null; 
	
	 
	  try   
	  {   
		  Class.forName("com.mysql.jdbc.Driver"); 
		  //Provide the correct details: DBServer/DBName, username, password    
		  con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");   
	 }
	  catch (Exception e)  
	  {
		  e.printStackTrace();
	  }
	 
	  return con; 
	}
	 
	 public String insertAppointment(String type, String no, String desc,String date,String uid,String did,String hosid) 
 
	 {
		 String output = ""; 
		 
	 
	 try  
	 {    
		 Connection con = connect(); 
	 
	 
	   if (con == null)   
	   {
		   return "Error while connecting to the database for inserting.";
		    
	   }
	 
	   // create a prepared statement    
	   String query = " insert into appointment(`appointment_ID`,`appointment_type`,`appointment_no`,`appointment_desc`,`appointment_date`,`Ruser_ID`,`Rdoctor_ID`,`Hospital_ID`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?)"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setInt(1, 0);    
	   preparedStmt.setString(2, type);    
	   preparedStmt.setString(3, no);    
	   preparedStmt.setString(4, desc);   
	   preparedStmt.setString(5, date);
	   preparedStmt.setString(6, uid);
	   preparedStmt.setString(7, did);
	   preparedStmt.setString(8, hosid);
	   
	   // execute the statement   
	   preparedStmt.execute();   
	   con.close(); 
	   
	   output = "Inserted successfully";   
	  }  
	   catch (Exception e)   
	  {
		   output = "Error while inserting the appointment.";  
		   System.err.println(e.getMessage());  
		   
	  }
	 
	  return output;  
	  } 

	 public String readAppointment() 
		{
			 String output = ""; 
		 
		 
		    try
		    {
		    	Connection con=connect();
		 
		       
			if (con == null) 
		       {
			      return "Error while connecting to the database for reading";
			   } 
		 
		   // Prepare the html table to be displayed
		        output = "<table border=\"1\">"
		        + "<tr><th>Appointment Type</th><th>Appointment Number</th><th>Appointment   "
		   		+ "Description</th><th>Appointment          "
		   		+ "Date</th><th>User ID</th><th>Doctor ID</th><th>Hospital ID</th><th>Update</th><th>Remove</th></tr>"; 
		 
		        String query = "select * from appointment";    
		        Statement stmt = con.createStatement();   
		        ResultSet rs = stmt.executeQuery(query);
		   
		  // iterate through the rows in the result set  
		       while (rs.next())    
		       {
			       String appointment_ID = Integer.toString(rs.getInt("appointment_ID"));  
			       String appointment_type = rs.getString("appointment_type");   
			       String appointment_no = rs.getString("appointment_no");     
			       String appointment_desc = rs.getString(("appointment_desc"));  
			       String appointment_date = rs.getString("appointment_date");
			       String Ruser_ID = rs.getString("Ruser_ID");
			       String Rdoctor_ID = rs.getString("Rdoctor_ID");
			       String Hospital_ID = rs.getString("Hospital_ID");
		   
		   
		    // Add into the html table  
			       output += "<tr><td>" + appointment_type + "</td>";     
			       output += "<td>" + appointment_no + "</td>";     
			       output += "<td>" + appointment_desc + "</td>";    
			       output += "<td>" + appointment_date + "</td>"; 
			       output += "<td>" + Ruser_ID + "</td>";     
			       output += "<td>" + Rdoctor_ID + "</td>";    
			       output += "<td>" + Hospital_ID + "</td>";
		 
		    // buttons     
			       output += "<td><input name=\"btnUpdate\" type=\"button\"        "
			   		+"value=\"Update\" class=\"btn btn-secondary\"></td>"     
					   + "<td><form method=\"post\" action=\"appointments.jsp\">"      
			   		+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"     "
			   		+ " class=\"btn btn-danger\">"     
			   		+ "<input name=\"appointment_ID\" type=\"hidden\" value=\"" + appointment_ID     
			   		+ "\">" + "</form></td></tr>";    
			    }
		       
		        con.close(); 
		   
		   // Complete the html table    
		         output += "</table>";  
	          }
	          catch(Exception e)  
	          {
	    	     output = "Error while reading the appointment.";    
	    	     System.err.println(e.getMessage());   
	          } 
		 
		      return output;
		 
	      }

	 public String updateAppointment(String ID, String type, String no, String desc, String date,String uid,String did,String hosid)  
	  {
		  String output = ""; 
	  
	       try   
	       {
		      Connection con = connect(); 
	 
	          if (con == null)    
	          {
		        return "Error while connecting to the database for updating."; 
		      } 
	 
	   // create a prepared statement    
	       String query = "UPDATE appointment SET appointment_type=?,appointment_no=?,appointment_desc=?,appointment_date=?,Ruser_ID=?,Rdoctor_ID=?,Hospital_ID=?WHERE appointment_ID=?"; 
	 
	       PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	       preparedStmt.setString(1, type);    
	       preparedStmt.setString(2, no);   
	       preparedStmt.setString(3, desc);    
	       preparedStmt.setString(4, date);
	       preparedStmt.setString(5, uid);   
	       preparedStmt.setString(6, did);    
	       preparedStmt.setString(7, hosid);
	       preparedStmt.setInt(8, Integer.parseInt(ID)); 
	 
	   // execute the statement    
	      preparedStmt.execute();    
	      con.close(); 
	 
	      output = "Updated successfully";   
	      }   
	      catch (Exception e)   
	      {   
		     output = "Error while updating the appointment.";    
	         System.err.println(e.getMessage());   
	      } 
	 
	      return output;  
	  }
	  
	  public String deleteAppointment(String appointment_ID)  
	  {
		  String output = ""; 
	  
	  try  
	  {
		    Connection con = connect(); 
	 
	        if (con == null)    
	        {
	        	return "Error while connecting to the database for deleting."; 
	        } 
	 
	   // create a prepared statement    
	        String query = "delete from appointment where appointment_ID=?"; 
	 
	        PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	        preparedStmt.setInt(1, Integer.parseInt(appointment_ID)); 
	 
	   // execute the statement    
	        preparedStmt.execute();   
	        con.close(); 
	 
	        output = "Deleted successfully";   
	        }   
	        catch (Exception e)  
	        {    output = "Error while deleting the appointment.";    
	            System.err.println(e.getMessage());   
	        } 
	 
	  return output;  
	  } 
}

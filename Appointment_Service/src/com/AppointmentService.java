package com;

import model.Appointment;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Appointments")
public class AppointmentService
{
	Appointment appointmentObj = new Appointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAppointment()
	{
		return appointmentObj.readAppointment();
	}

	
	@POST 
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertAppointment(@FormParam("appointment_type") String appointment_type,       
			                @FormParam("appointment_no") String appointment_no,    
			                @FormParam("appointment_desc") String appointment_desc,     
			                @FormParam("appointment_date") String appointment_date,
			                @FormParam("Ruser_ID") String Ruser_ID,
			                @FormParam("Rdoctor_ID") String Rdoctor_ID,
			                @FormParam("Hospital_ID") String Hospital_ID) 
	{  
		String output = appointmentObj.insertAppointment(appointment_type, appointment_no, appointment_desc, appointment_date,Ruser_ID,Rdoctor_ID,Hospital_ID);  
	    return output; 
	}
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateAppointment(String appointmentData) 
	{
		//Convert the input string to a JSON object 
		JsonObject appointmentObject = new JsonParser().parse(appointmentData).getAsJsonObject(); 
		 
		 //Read the values from the JSON object  
		String appointment_ID = appointmentObject.get("appointment_ID").getAsString(); 
		String appointment_type = appointmentObject.get("appointment_type").getAsString(); 
		String appointment_no = appointmentObject.get("appointment_no").getAsString();  
		String appointment_desc = appointmentObject.get("appointment_desc").getAsString();  
		String appointment_date = appointmentObject.get("appointment_date").getAsString();
		String Ruser_ID = appointmentObject.get("Ruser_ID").getAsString();  
		String Rdoctor_ID = appointmentObject.get("Rdoctor_ID").getAsString();  
		String Hospital_ID = appointmentObject.get("Hospital_ID").getAsString();
		 
		String output = appointmentObj.updateAppointment(appointment_ID, appointment_type, appointment_no, appointment_desc, appointment_date,Ruser_ID,Rdoctor_ID,Hospital_ID); 
		 
		return output; 
	}
	
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteAppointment(String appointmentData) 
	{
		//Convert the input string to an XML document  
		Document doc = Jsoup.parse(appointmentData, "", Parser.xmlParser());     
		
		//Read the value from the element <itemID>  
		String appointment_ID = doc.select("appointment_ID").text(); 
		 
	    String output = appointmentObj.deleteAppointment(appointment_ID); 
		 
		 return output;
	} 
	
	
}


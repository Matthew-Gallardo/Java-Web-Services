package org.acumen.training.codes.services;



import java.util.ArrayList;
import java.util.List;

import org.acumen.training.codes.model.data.Profile;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {
	//Endpoints
	
	@GET
	@Path("/greet")
	@Produces(value = MediaType.TEXT_PLAIN)
	public String showMessage() {
		return "Hello World";
	}
	
	//Request Params
	@GET
	@Path("/profile") //?id=101&fullname=Juan Luna&salary=50000
	@Produces(value = MediaType.TEXT_PLAIN)
	public String createProfile(@QueryParam("id")Integer id, 
			@QueryParam("fullname") String fullname , 
			@QueryParam("salary") Double salary) {
		String record = "%d %s %f".formatted(id, fullname, salary);
		return record;
	}
	
	//Dynamic Url
	@GET
	@Path("/profile/tx/{operation}") 
	@Produces(value = MediaType.TEXT_PLAIN)
	public String updateOrEditPage(@PathParam("operation") String operation) {
		switch(operation) {
			case "edit":
				return "EDIT";
			case "add":
				return "ADD";
			default:
				return "ERROR";
		}	
	}
	
	//Using model.data
	@GET
	@Path("/profile/record") 
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Profile createProfileRec(@QueryParam("id")Integer id, 
			@QueryParam("fullname") String fullname , 
			@QueryParam("salary") Double salary) {
		Profile rec = new Profile();
		rec.setId(id);
		rec.setFullname(fullname);
		rec.setSalary(salary);
		return rec;
		
	}
	
	//Collections
	@GET
	@Path("/profile/records/array") 
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Profile[] createProfileRec() {
		Profile rec1= new Profile();
		Profile rec2= new Profile();
		rec1.setId(101);
		rec1.setFullname("Lebron James");
		rec1.setSalary(1000.00);
		rec2.setId(102);
		rec2.setFullname("Carmelo Anthony");
		rec2.setSalary(1000.00);
		
		Profile[] records = new Profile[2];
		records[0]=rec1;
		records[1]=rec2;
		return records;
		
	}
	//List
	@GET
	@Path("/profile/records/list") 
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response selectAllProfiles(){
		try {
			Profile rec1= new Profile();
			Profile rec2= new Profile();
			rec1.setId(101);
			rec1.setFullname("Lebron James");
			rec1.setSalary(1000.00);
			rec2.setId(102);
			rec2.setFullname("Carmelo Anthony");
			rec2.setSalary(1000.00);
			
			List<Profile> records = new ArrayList<Profile>();
			records.add(rec1);
			records.add(rec2);
			GenericEntity<List<Profile>> data = 
					new GenericEntity<List<Profile>>(records) {};
			
			return Response.ok(data).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	
	}
	
	//Post
	//Form Handling
	@POST
	@Path("/profile/form/add") 
	@Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Profile saveProfile(@FormParam("id")Integer id, 
			@FormParam("fullname")String fullname ,
			@FormParam("salary")Double salary) {
		
		Profile profile = new Profile();
		profile.setId(id);
		profile.setFullname(fullname);
		profile.setSalary(salary);
		return profile;
	}
	
	//JSON OBJECT POST
	@POST
	@Path("/profile/json/add") 
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Profile saveProfile(Profile profile) {
		return profile;
	}
	
	//Put
	@PUT
	@Path("/profile/json/edit") 
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Profile editProfileJson(Profile profile) {
		return profile;
	}
	
	//Patch - partial edit
	@DELETE
	@Path("/profile/{id}") 
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response editPartialProfileJson(@PathParam("id")Integer id) {
		return Response.ok().build();
	}
	
	//Delete
	@PATCH
	@Path("/profile/json/edit/partial") 
	@Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Profile editPartialProfileJson(Profile profile) {
		return profile;
	}
	
}

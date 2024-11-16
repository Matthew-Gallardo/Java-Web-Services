package org.acumen.training.codes.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.ws.rs.core.NewCookie;

import java.util.ArrayList;
import java.util.List;

import org.acumen.training.codes.model.data.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/hello")
public class HelloWorldRestController {
	
	@GetMapping(path = "/greet")
	public String greet() {
		return "HELLO WORLD";
	}
	
	//Request Param
	@GetMapping(path = "/profile")
	public String createProfile(@RequestParam("id")Integer id,
			@RequestParam("fullname") String fullname , 
			@RequestParam("salary") Double salary) 
	{
		return"%d %s %f".formatted(id,fullname,salary);
	}
	
	//PathVariable (explore same as MVC)
	@GetMapping(path = "/profile/record", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Profile getProfile() {
		Profile profile = new Profile();
		profile.setId(102);
		profile.setFullname("lebron jaems");
		profile.setSalary(10000.00);
		return profile;
	}
	
	//List
	@GetMapping(path = "/profile/records/all", 
			produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<Profile>getProfileList(){
		Profile profile = new Profile();
		profile.setId(102);
		profile.setFullname("lebron jaems");
		profile.setSalary(10000.00);
		Profile profile2 = new Profile();
		profile2.setId(103);
		profile2.setFullname("caemraslo anthony");
		profile2.setSalary(20000.00);
		
		List<Profile>records = new ArrayList<Profile>();
		records.add(profile);
		records.add(profile2);
		return records;
	}
	
	
	 @GetMapping(path = "/profile/records/all/response")
	 public ResponseEntity<List<Profile>> getProfileListResponse() {
	     Profile profile1 = new Profile();
	     profile1.setId(102);
	     profile1.setFullname("lebron jaems");
	     profile1.setSalary(10000.00);
	
	     Profile profile2 = new Profile();
	     profile2.setId(103);
	     profile2.setFullname("caemraslo anthony");
	     profile2.setSalary(20000.00);
	
	     List<Profile> records = new ArrayList<>();
	     records.add(profile1);
	     records.add(profile2);
	
	     return ResponseEntity.ok(records); 
	 }
	
	 //Post
	 @PostMapping(path = "/profile/add",
			 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			 consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	 public Profile saveProfile(@RequestBody Profile profile ) {
		 return profile;
	 }
	 
	 @PostMapping(path = "/profile/form/add",
			 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			 consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	 public Profile saveProfile(@RequestParam("id")Integer id,
				@RequestParam("fullname") String fullname , 
				@RequestParam("salary") Double salary) {
		Profile profile = new Profile();
	     profile.setId(103);
	     profile.setFullname("caemraslo anthony");
	     profile.setSalary(20000.00);
		 return profile;
	 }
	 
	 //PUT
	 @PutMapping(path = "/profile/edit",
			 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			 consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	 public Profile editFullProfile(@RequestBody Profile profile) {
		 return profile;
	 }
	 
	 
	 @PatchMapping(path = "/profile/edit/partial",
			 produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			 consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	 public Profile editpartialProfile(@RequestBody Profile profile) {
		 return profile;
	 }
	 
	 @DeleteMapping(path = "/profile/delete/{id}",
			 consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	 public String deleteProfile(@PathVariable("id")Integer id) {
		 return "removed";
	 }

}

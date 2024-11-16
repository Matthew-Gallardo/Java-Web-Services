package org.acumen.training.codes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acumen.training.codes.model.data.Profile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(locations = 
      {"file:src/main/webapp/WEB-INF/context/applicationContext.xml"})
@WebAppConfiguration
public class TestHelloWorldRestController {
	
	@Test
	public void testGreet() {
		RestTemplate client = new RestTemplate();
		String res = client
				 .getForObject("http://localhost:8080/myspringrs/rest/hello/greet", 
						 String.class);
		System.out.println(res);
	}
	
	@Test
	public void testCreateProfile() {
		RestTemplate client = new RestTemplate();
		String res = client
			     .getForObject("http://localhost:8080/myspringrs/rest/hello/profile?id=101&fullname=Juan Luna&salary=50000.00", String.class);
		System.out.println(res);
	}
	
	@Test
	public void testCreateProfileExchange() {
		Map<String, String> form = new HashMap<>();
		form.put("id", "401");
		form.put("fullname", "Apo Mabini");
		form.put("salary", "60000.00");
		
		RestTemplate client = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<String> res = client.exchange("http://localhost:8080/myspringrs/rest/hello/profile?id={id}&fullname={fullname}&salary={salary}", 
				HttpMethod.GET, httpEntity, String.class, form);
		System.out.println(res.getBody());
	}
	
	@Test
	public void testGetProfile() {
		RestTemplate client = new RestTemplate();
		Profile res = client
			     .getForObject("http://localhost:8080/myspringrs/rest/hello/profile/record", Profile.class);
		System.out.println("%d %s %f".formatted(
				res.getId(), res.getFullname(), res.getSalary()));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetProfilesList() {
		RestTemplate client = new RestTemplate();
		List<Profile> res = (ArrayList<Profile>) client
			     .getForObject("http://localhost:8080/myspringrs/rest/hello/profile/records/list", ArrayList.class);
		System.out.println(res);
	}
	

	@Test
	public void testGetProfilesListResponse() {
		RestTemplate client = new RestTemplate();
		ResponseEntity<List> res = client
			     .getForEntity("http://localhost:8080/myspringrs/rest/hello/profile/records/list/response", List.class);
		System.out.println(res.getBody());
	}
	
	// POST
	@Test
	public void testSaveProfile() {
		Profile rec = new Profile();
		rec.setId(201);
		rec.setFullname("Gabriela Silang");
		rec.setSalary(50000.00);
		RestTemplate client = new RestTemplate();
		Profile res = client
			     .postForObject("http://localhost:8080/myspringrs/rest/hello/profile/add", rec, Profile.class);
		System.out.println(res);
	}
	
	@Test
	public void testSaveProfileForm() {
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("id", "401");
		form.add("fullname", "Apo Mabini");
		form.add("salary", "60000.00");
		RestTemplate client = new RestTemplate();		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(form, headers);
		ResponseEntity<Profile> res = client.exchange("http://localhost:8080/myspringrs/rest/hello/profile/form/add", 
				HttpMethod.POST, httpEntity, Profile.class);
		System.out.println(res.getBody());
	}
	
	@Test
	public void testPartialEditProfile() {
		Profile rec = new Profile();
		rec.setId(201);
		rec.setSalary(90000.00);
		
		RestTemplate client = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		Profile res = client
			     .patchForObject("http://localhost:8080/myspringrs/rest/hello/profile/edit/partial", 
			    		 rec, Profile.class);
		System.out.println(res);
	}



}


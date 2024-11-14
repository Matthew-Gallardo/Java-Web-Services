package org.acumen.training.codes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acumen.training.codes.model.data.Profile;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class HelloServiceClient {
	
	public String getGreet() {
		Client client = ClientBuilder.newClient();
		Response resp = client.target("http://localhost:8080/jaxrs/rs/hello/greet")
				.request().accept(MediaType.TEXT_PLAIN).get();
		int sc = resp.getStatus();
		System.out.println("Status Code: %d".formatted(sc));
		String result = resp.readEntity(String.class);
		return result;
		
		
	}
	
	public String getProfile() {
		Client client = ClientBuilder.newClient();
		Response resp = client.target("http://localhost:8080/jaxrs/rs/hello/profile")
				.queryParam("id", "100")
				.queryParam("fullname", "Lebron James")
				.queryParam("salary", "999.99")
				.request().accept(MediaType.TEXT_PLAIN).get();
		int sc = resp.getStatus();
		System.out.println("Status Code: %d".formatted(sc));
		String result = resp.readEntity(String.class);
		return result;
		
	}
	
	public String getPage() {
		Client client = ClientBuilder.newClient();
		Response resp = client.target("http://localhost:8080/jaxrs/rs/hello/profile/tx/edit")
				.queryParam("id", "100")
				.queryParam("fullname", "Lebron James")
				.queryParam("salary", "999.99")
				.request().accept(MediaType.TEXT_PLAIN).get();
		int sc = resp.getStatus();
		System.out.println("Status Code: %d".formatted(sc));
		String result = resp.readEntity(String.class);
		return result;
		
	}
	
	public Profile getProfileRec() {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/jaxrs/rs/hello/profile/record")
				.queryParam("id", "100")
				.queryParam("fullname", "Juan Luma")
				.queryParam("salary", "9090192401")
				.request()
				.accept(MediaType.APPLICATION_XML)
				.get();
		int sc = response.getStatus();
		System.out.println("SC: %d".formatted(sc));
		Profile res = response.readEntity(Profile.class);
		return res;
	}
	
	
	public Profile[] getProfileArray() {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/jaxrs/rs/hello/profile/records/array")
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
		int sc = response.getStatus();
		System.out.println("SC: %d".formatted(sc));
		Profile[] res = response.readEntity(Profile[].class);
		return res;
	}
	
	public List<Profile> getProfileList() {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/jaxrs/rs/hello/profile/records/list")
				.request()
				.accept(MediaType.APPLICATION_JSON)
				.get();
		int sc = response.getStatus();
		System.out.println("SC: %d".formatted(sc));
		List<Profile> res = response.readEntity(new GenericType<List<Profile>>() {});
		return res;
	}
	
	//form
	public boolean postAddProfile(Integer id, String fullname, Double salary) {
		try {
			Form params = new Form();
			params.param("id", String.valueOf(id));
			params.param("fullname", String.valueOf(fullname));
			params.param("salary", String.valueOf(salary));
 
			Client client = ClientBuilder.newClient();
			Profile response = client.target("http://localhost:8080/jaxrs/rs/hello/profile/form/add")
					.request(MediaType.APPLICATION_FORM_URLENCODED)
					.accept(MediaType.APPLICATION_JSON)
					.post(Entity.form(params), Profile.class);
			System.out.println(response);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//JSON
	public boolean saveProfileJson(Profile profile) {
		try {
			Client client = ClientBuilder.newClient();
			Profile response = client.target("http://localhost:8080/jaxrs/rs/hello/profile/json/add")
					.request(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.post(Entity.json(profile), Profile.class);
			System.out.println(response);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean partialEditProfile(Profile profile) {
		try {
			Client client = ClientBuilder.newClient();
			Profile response = client.target("http://localhost:8080/jaxrs/rs/hello/profile/json/edit/partial")
					.request(MediaType.APPLICATION_JSON)
					.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true)
					.accept(MediaType.APPLICATION_JSON)
					.method("PATCH", Profile.class);
			System.out.println(response);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

package org.acumen.training.codes.test;

import java.util.Arrays;

import org.acumen.training.codes.HelloServiceClient;
import org.acumen.training.codes.model.data.Profile;

public class TestHelloServiceClient {
	public static void main(String[] args) {
		HelloServiceClient client = new HelloServiceClient();
		System.out.println(client.getGreet());
		System.out.println(client.getProfile());
		System.out.println(client.getPage());
		Profile p = client.getProfileRec();
		System.out.println(p.toString());
		
		System.out.println(Arrays.toString(client.getProfileArray()));
		
		System.out.println(client.getProfileList());
		
		//POST
		System.out.println(client.postAddProfile(101, "Kyrie", 10000.00));
		Profile pjson = new Profile();
		pjson.setId(300);
		pjson.setFullname("Kanye west");
		pjson.setSalary(3000.00);
		System.out.println(pjson.toString());
	}

}

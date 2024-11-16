package org.acumen.training.codes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class HelloWorldRestControllerCLient {
	
	public String getGreet() {
		RestTemplate client = new RestTemplate();
		Jaxb2RootElementHttpMessageConverter jaxb = new Jaxb2RootElementHttpMessageConverter();
		MappingJackson2HttpMessageConverter json = new MappingJackson2HttpMessageConverter();
		RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
		
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(jaxb);
		converters.add(json);
		adapter.setMessageConverters(converters);
		String res = client.getForObject("http://localhost:8080/myspringrest/rest/hello/greet", String.class);
		return res;
	}

}

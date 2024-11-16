package org.acumen.training.codes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class Sample {
	
	public void test() {
		Jaxb2RootElementHttpMessageConverter jaxb = new Jaxb2RootElementHttpMessageConverter();
		MappingJackson2HttpMessageConverter json = new MappingJackson2HttpMessageConverter();
		RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(jaxb);
		converters.add(json);
		adapter.setMessageConverters(converters);
	}

}

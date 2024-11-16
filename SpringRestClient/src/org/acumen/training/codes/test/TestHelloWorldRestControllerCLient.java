package org.acumen.training.codes.test;

import org.acumen.training.codes.HelloWorldRestControllerCLient;

public class TestHelloWorldRestControllerCLient {
	public static void main(String[] args) {
		HelloWorldRestControllerCLient cLient = new HelloWorldRestControllerCLient();
		System.out.println(cLient.getGreet());
	}
}

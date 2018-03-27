package com.chai.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	//method - "Hello WOrld"
	//GET
	//URI - /hello-world
	@Autowired
	public MessageSource messageSource;
	
	@GetMapping(path= "/hello-world")
	public String helloWorld(){
		return "Hello World";
	}
	
	@GetMapping(path= "/hello-world-bean")
	public HelloWorldBean helloWorldBean(){
		return new HelloWorldBean("Hello World Bean");
	}
	
	@GetMapping("/good-morning")
	public String goodMorning(){
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}
	
}

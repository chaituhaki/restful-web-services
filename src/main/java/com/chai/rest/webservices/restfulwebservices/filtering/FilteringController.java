package com.chai.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/retrievesomebean")
	public SomeBean retrieveSomeBean(){
		return new SomeBean("value1", "value2", "value3");
	}
	
	@GetMapping("/retrievesomebean-list")
	public MappingJacksonValue retrieveSomeBeanList(){
		List<SomeBean> list = Arrays.asList(new SomeBean("v1", "v2", "v3"),new SomeBean("u1", "u2", "u3"));
		//SomeBean somebean= new SomeBean("v1", "v2", "v3");
		
		SimpleBeanPropertyFilter filters = 
				SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		
		FilterProvider filter = 
				new SimpleFilterProvider().addFilter("someBeanFilter", filters);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filter);
		return mapping;
	}

}

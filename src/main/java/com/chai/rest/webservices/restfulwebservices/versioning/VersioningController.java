package com.chai.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VersioningController {

	@GetMapping("/v1/person")
	public PersonV1 personV1(){
		return new PersonV1("Authur Pendragon");
	}
	@GetMapping("/v2/person")
	public PersonV2 personV2(){
		return new PersonV2("Aurthur", "Pendragon");
	}
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramV1(){
		return new PersonV1("Authur Pendragon");
	}
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 paramV2(){
		return new PersonV2("Authur","Pendragon");
	}
	@GetMapping(value="/person/header", headers="X-API-VERSION")
	public PersonV1 headerV1(){
		return new PersonV1("Authur Pendragon");
	}
	@GetMapping(value="/person/header", headers="X-API-VERSION=2")
	public PersonV2 headerV2(){
		return new PersonV2("Authur","Pendragon");
	}
	
}

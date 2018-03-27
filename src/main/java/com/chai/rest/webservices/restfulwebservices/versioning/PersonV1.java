package com.chai.rest.webservices.restfulwebservices.versioning;

public class PersonV1 {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PersonV1(String name) {
		super();
		this.name = name;
	}

	private String name;
}

package com.chai.rest.webservices.restfulwebservices.UserServices;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the user")
@Entity
public class User {
	
	@Size(min=2, message="Name should have atleast 3 Characters")
	@ApiModelProperty(notes="Name should have atleast 3 Characters")
	private String name;
	
	@Id
	@GeneratedValue
	private int id;
	
	//@Size(min=3, max=4)
	@ApiModelProperty(notes="Department is Mandatory")
	private String dept;
	
	@OneToMany(mappedBy="user")
	private List<Post> post;
	
	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public User(int id,String name, String dept) {
		this.name = name;
		this.id = id;
		this.dept = dept;
	}
	
	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", dept=" + dept + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
}

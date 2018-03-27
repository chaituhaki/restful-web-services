package com.chai.rest.webservices.restfulwebservices.UserServices;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

	
	
	@GetMapping(path= "/users")
	public List<User> allUsers(){
		return UserDAO.getUsers();
	}
	
	@GetMapping(path="/users/{id}")
	public Resource<User> getUser(@PathVariable int id){
		User user= UserDAO.getUser(id);
		if(user==null){
			throw new UserNotFoundException("Id-"+id);
		}
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).allUsers());
		resource.add(linkTo.withRel("/all-users"));
		return resource;
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(path="/users")
	public ResponseEntity addUser(@Valid @RequestBody User user){
		User newUser= UserDAO.addUser(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/users/{id}/delete")
	public void deleteUser(@PathVariable int id){
		User user= UserDAO.deleteUser(id);
		if(user == null){
			throw new UserNotFoundException("Id-"+id);
		}
	}
	
}

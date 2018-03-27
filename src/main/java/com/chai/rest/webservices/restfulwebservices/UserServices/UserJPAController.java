package com.chai.rest.webservices.restfulwebservices.UserServices;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserJPAController {

	@Autowired
	public UserRepository userRepository;
	
	@GetMapping(path= "/jpa/users")
	public List<User> allUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping(path="/jpa/users/{id}")
	public Resource<User> getUser(@PathVariable int id){
		User user= userRepository.getOne(id);
		if(user==null){
			throw new UserNotFoundException("Id-"+id);
		}
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).allUsers());
		resource.add(linkTo.withRel("/all-users"));
		return resource;
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping(path="/jpa/users")
	public ResponseEntity addUser(@Valid @RequestBody User user){
		User newUser= userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/jpa/users/{id}/delete")
	public void deleteUser(@PathVariable int id){
		userRepository.delete(id);
	}
	
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Post> retrieveAllPostsOfUser(@PathVariable int id){
		 User u= userRepository.findById(id);
		 if(user==null){
			 throw new UserNotFoundException("id-"+ id);
		 }
		 return u.get().getPost();
		
	}
	
}

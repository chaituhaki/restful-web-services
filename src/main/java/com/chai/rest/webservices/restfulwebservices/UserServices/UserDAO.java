package com.chai.rest.webservices.restfulwebservices.UserServices;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	private static List<User> users = new ArrayList<>();
	
	static{
		users.add(new User(1, "adam", "Cse"));		
		users.add(new User(2, "Eve", "ECE"));
		users.add(new User(3, "King", "EEE"));
	}
	
	
	public static List<User> getUsers() {
		return users;
	}

	public static void setUsers(List<User> users) {
		UserDAO.users = users;
	}
	
	public static User addUser(User user){
		users.add(user);
		return user;
	}
	
	public static User getUser(int id){
		for(User user: users){
			if(user.getId()==id){
				return user;
			}
		}
		return null;
	}
	
	public static User deleteUser(int id){
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()){
			User user = iterator.next();
			if(user.getId() == id){
				iterator.remove();
				return user;
			}
		}
		return null;
 	}
	
}

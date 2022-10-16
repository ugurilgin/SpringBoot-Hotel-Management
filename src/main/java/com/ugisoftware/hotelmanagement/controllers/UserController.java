package com.ugisoftware.hotelmanagement.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugisoftware.hotelmanagement.entities.User;
import com.ugisoftware.hotelmanagement.services.UserServices;


@RestController
@RequestMapping("/users")
public class UserController {
	
private UserServices userServices;

public UserController(UserServices userServices){
	this.userServices=userServices;
}

@GetMapping
public List<User> getAllUsers()
{
	return userServices.getAllUsers();
}

@PostMapping
public User createUsers(@RequestBody User newUser)
{
	return userServices.createUser(newUser);
}

@GetMapping("/{userId}")
public User getUser(@PathVariable Long userId)
{
	return userServices.getUser(userId);
 	

}

@PutMapping("/{userId}")
public User updateUser(@RequestBody User updateUser,@PathVariable Long userId)
{
	return userServices.updateUser(updateUser,userId);
	
}

@DeleteMapping("/{userId}")
public void deleteUser(@PathVariable Long userId)
{
	userServices.deleteUser(userId);
	
	
}

}
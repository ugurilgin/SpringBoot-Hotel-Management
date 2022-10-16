package com.ugisoftware.hotelmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ugisoftware.hotelmanagement.entities.Employee;
import com.ugisoftware.hotelmanagement.entities.User;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.UserRepository;
import com.ugisoftware.hotelmanagement.utils.DateUtil;


@Service
public class UserServices {
private UserRepository userRepository;
private PasswordEncoder passwordEncoder;
public UserServices(UserRepository userRepository,PasswordEncoder passwordEncoder) {

	this.userRepository = userRepository;
	this.passwordEncoder=passwordEncoder;
}

public List<User> getAllUsers() {
	// TODO Auto-generated method stub
	return userRepository.findAll();
}
public User createUser(User newUser)
{
	
	
	Boolean isUserByEmail=userRepository.existsByEmail(newUser.getEmail());
	Boolean isUserByUserName=userRepository.existsByUsername(newUser.getUsername());
	 if(isUserByEmail )
		throw new EntityNotFoundException("This email is already in use");
	
	 else if(isUserByUserName )
			throw new EntityNotFoundException("This username is already in use");

	else {
	
	User createUser=new User();
	
	createUser.setEmail(newUser.getEmail());
	createUser.setName(newUser.getName());
	createUser.setSurname(newUser.getSurname());
	createUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
	createUser.setId(newUser.getId());
	return userRepository.save(createUser);
	}
}

public User getUser(Long userId) {
	User user =  userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User Not Found with id : " + userId));
     return user;
	}

public User updateUser(User updateUser, Long userId) {
	// TODO Auto-generated method stub
	 return userRepository.findById(userId).map(user -> {
		 User foundedUser=new User();
		 foundedUser.setName(updateUser.getName());
		 foundedUser.setSurname(updateUser.getSurname());
		foundedUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
		return userRepository.save(foundedUser);
		
     }).orElseThrow(() -> new EntityNotFoundException("UserId " + userId + " not found"));
}

public ResponseEntity<?> deleteUser(Long userId) {
	return userRepository.findById(userId).map(user -> {
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }).orElseThrow(() -> new EntityNotFoundException("UserId " + userId + " not found"));
}


}

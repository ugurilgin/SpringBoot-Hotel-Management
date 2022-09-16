package com.ugisoftware.hotelmanagement.services;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ugisoftware.hotelmanagement.entities.User;
import com.ugisoftware.hotelmanagement.repositories.UserRepository;


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
	
	return userRepository.save(newUser);
}

public User getUser(Long userId) {
	// TODO Auto-generated method stub
	return userRepository.findById(userId).orElse(null);
	}

public User updateUser(User updateUser, Long userId) {
	// TODO Auto-generated method stub
	User updatedUser;
	Optional<User> user=userRepository.findById(userId);
	if(user.isPresent())
	{
		User foundedUser=user.get();
		foundedUser.setUsername(updateUser.getUsername());
		foundedUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
		updatedUser= userRepository.save(foundedUser);
	}
	else
	{
		updatedUser= null;
	}
	return updatedUser;
}

public void deleteUser(Long userId) {
	// TODO Auto-generated method stub
	 userRepository.deleteById(userId);	
}


}

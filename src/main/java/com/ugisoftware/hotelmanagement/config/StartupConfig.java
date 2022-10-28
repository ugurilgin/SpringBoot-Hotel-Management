package com.ugisoftware.hotelmanagement.config;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ugisoftware.hotelmanagement.controllers.AuthController;
import com.ugisoftware.hotelmanagement.dto.request.RegisterRequestDTO;
import com.ugisoftware.hotelmanagement.entities.ERole;
import com.ugisoftware.hotelmanagement.entities.Employee;
import com.ugisoftware.hotelmanagement.entities.Role;
import com.ugisoftware.hotelmanagement.entities.User;
import com.ugisoftware.hotelmanagement.repositories.EmployeeRepository;
import com.ugisoftware.hotelmanagement.repositories.RoleRepository;
import com.ugisoftware.hotelmanagement.repositories.UserRepository;
import com.ugisoftware.hotelmanagement.services.UserServices;
import com.ugisoftware.hotelmanagement.utils.Blood;
import com.ugisoftware.hotelmanagement.utils.Gender;




@Component
public class StartupConfig implements CommandLineRunner{

	private  RoleRepository roleRepository;
	private UserRepository userRepository;
	private EmployeeRepository employeeRepository;
    public StartupConfig(RoleRepository roleRepository,UserRepository userRepository,EmployeeRepository employeeRepository) {
		
		this.roleRepository = roleRepository;
		this.userRepository=userRepository;
		this.employeeRepository=employeeRepository;
	}


	@Override
    public void run(String... args) throws Exception {
   
		Optional<Role> roleOptional =roleRepository.findByName(ERole.ROLE_ADMIN);
		 
    	if(roleOptional == null || roleOptional.isEmpty())  
		{
	
       System.out.println("Creating Datas");
      
       roleRepository.save(new Role(ERole.ROLE_ADMIN));
       roleRepository.save(new Role(ERole.ROLE_MODERATOR));
       roleRepository.save(new Role(ERole.ROLE_USER));

       Employee employee=new Employee(1L,"Test","Test","Test","01-01-1998",Gender.Erkek,"01-01-2013","01-01-2014","Test adres-Update","123-476-1815","test@test.com",Blood.ABRhN,5000);

       employeeRepository.save(employee);
		}
    }
}
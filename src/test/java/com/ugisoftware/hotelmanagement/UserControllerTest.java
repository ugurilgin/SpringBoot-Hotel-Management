package com.ugisoftware.hotelmanagement;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ugisoftware.hotelmanagement.controllers.UserController;

import com.ugisoftware.hotelmanagement.entities.User;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.UserRepository;
import com.ugisoftware.hotelmanagement.services.UserServices;


@WebMvcTest(UserController.class)
@Import(UserController.class)
public class UserControllerTest {
	
   
		
	    @Autowired
	    private MockMvc mockMvc;
	
	    @MockBean
	    private UserServices userService;
	    @MockBean
	    private UserRepository userRepository;
	
	    @Autowired
	    private ObjectMapper objectMapper;
	    
	
	
		@Test
	    public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception{
	
	        // given - precondition or setup
	    	User newUser =new User(1L,"Test-Name","Test-Surname","TestUserName","test@test.com","Password",true);
	
	
	        given(userService.createUser(any(User.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));
	
	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/users")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(newUser)));
	
	        // then - verify the result or output using assert statements
	        response.andDo(print()).
	                andExpect(status().isOk())
	                .andExpect(jsonPath("$.name",
	                        is(newUser.getName())))
	                .andExpect(jsonPath("$.surname",
	                        is(newUser.getSurname())))
	                .andExpect(jsonPath("$.username",
	                        is(newUser.getUsername())))
	                .andExpect(jsonPath("$.password",
	                        is(newUser.getPassword())))
	                .andExpect(jsonPath("$.email",
	                        is(newUser.getEmail())));
	       
	    }
		
		@Test
	    public void givenUserObject_whenCreateUserwitAlreadyUsedEmail_thenReturn40withEmailError() throws Exception{
	        
	        // given - precondition or setup
	    	User newUser =new User(1L,"Test-Name","Test-Surname","TestUserName","test@test.com","Password",true);
	
		given(userRepository.existsByEmail(newUser.getEmail())).willReturn(true);
	
	        given(userService.createUser(any(User.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));
	        
	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/users")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(newUser)));
	        // then - verify the result or output using assert statements
	        
	        response.andDo(print()).
	                andExpect(status().isOk());
	       
	    }
		@Test
	    public void givenUserObject_whenCreateUserwitAlreadyUsedUserName_thenReturn40withEmailError() throws Exception{
	        
	        // given - precondition or setup
	    	User newUser =new User(1L,"Test-Name","Test-Surname","TestUserName","test@test.com","Password",true);
	
		given(userRepository.existsByUsername(newUser.getUsername())).willReturn(true);
	
	        given(userService.createUser(any(User.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));
	        
	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/users")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(newUser)));
	        // then - verify the result or output using assert statements
	        
	        response.andDo(print()).
	                andExpect(status().isOk());
	       
	    }
		   // JUnit test for Get All users REST API
	    @Test
	    public void givenListOfUser_whenGetAllUser_thenReturnUsersList() throws Exception{
	        // given - precondition or setup
	        List<User > listOfUsers = new ArrayList<>();
	        listOfUsers.add(new User(1L,"Test-Name","Test-Surname","TestUserName","test@test.com","Password",true));
	        listOfUsers.add(new User(2L,"Test-Name2","Test-Surname2","TestUserName2","test2@test.com","Password2",true));
	        given(userService.getAllUsers()).willReturn(listOfUsers);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/users"));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.size()",
	                        is(listOfUsers.size())));
	
	    }
	    @Test
	    public void givenuserId_whenGetUserById_thenReturnUserObject() throws Exception{
	        // given - precondition or setup
	        long userId = 1L;
	    	User newUser =new User(1L,"Test-Name","Test-Surname","TestUserName","test@test.com","Password",true);
	
	        given(userService.getUser(userId)).willReturn(newUser);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/users/{userId}", userId));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.name", is(newUser.getName())))
	                .andExpect(jsonPath("$.surname", is(newUser.getSurname())))
	                .andExpect(jsonPath("$.email", is(newUser.getEmail())));
	
	    }
	    @Test
	    public void givenInvaliduserId_whenGetUserById_thenReturnException() throws Exception{
	        // given - precondition or setup
	        long userId = 1L;
	     
	       
	     	when(userService.getUser(userId)).thenThrow(new EntityNotFoundException("userId " + userId + " not found"));
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/users/{userId}", userId));
	
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
	
	    }
	    @Test
	    public void givenUpdatedUser_whenUpdateUser_thenReturnUpdateUserObject() throws Exception{
	        // given - precondition or setup
	        long userId = 1L;
	    	User savedUser =new User(1L,"Test-Name","Test-Surname","TestUserName","test@test.com","Password",true);
	
	
	    	User updatedUser =new User(1L,"Test-Name-Update","Test-Surname-Update","TestUserName-Update","test@test.com","Password-Update",true);
	
	        given(userRepository.findById(userId)).willReturn(Optional.of(savedUser) );
	        given(userService.getUser(userId)).willReturn(savedUser);
	
	        given(userService.updateUser(any(User.class),anyLong()))
	        .willReturn(updatedUser);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(put("/users/{userId}", userId)
	                                    .contentType(MediaType.APPLICATION_JSON)
	                                    .content(objectMapper.writeValueAsString(updatedUser)));
	
	
	        // then - verify the output
	        response.andDo(print()).
            andExpect(status().isOk())
            .andExpect(jsonPath("$.name",
                    is(updatedUser.getName())))
            .andExpect(jsonPath("$.surname",
                    is(updatedUser.getSurname())))
            .andExpect(jsonPath("$.password",
             
                    is(updatedUser.getPassword())));
	    }
	    @Test
	    public void givenUpdatedUser_whenUpdateUser_thenReturnException() throws Exception{
	        // given - precondition or setup
	    	 long userId = 1L;
		    	User updatedUser =new User(1L,"Test-Name-Update","Test-Surname-Update","TestUserName-Update","test@test.com","Password-Update",true);
	
	     	  given(userRepository.findById(userId)).willReturn(null );
	     	
	         
	     	when(userService.updateUser(any(User.class),anyLong())).thenThrow(new EntityNotFoundException("userId " + userId + " not found"));
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(put("/users/{userId}", userId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(updatedUser)));
	
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
	    }
	    @Test
	    public void givenuserId_whenDeleteUser_thenReturn200() throws Exception{
	        // given - precondition or setup
	        long userId = 1L;
	    	User savedUser =new User(1L,"Test-Name","Test-Surname","TestUserName","test@test.com","Password",true);
	        
	        given(userRepository.findById(userId)).willReturn(Optional.of(savedUser) );
	        given(userService.deleteUser(userId))
	        .willAnswer((invocation)-> invocation.getArgument(0));
	     
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(delete("/users/{userId}", userId));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print());
	    }
	    @Test
	    public void givenuserId_whenDeleteUser_thenReturnException() throws Exception{
	        // given - precondition or setup
	        long userId = 1L;
	        
	        given(userRepository.findById(userId)).willReturn(null );
	     	when(userService.deleteUser(userId)).thenThrow(new EntityNotFoundException("userId " + userId + " not found"));
	
	     
	
	        // when -  action or the behaviour that we are going test
	     	
	        ResultActions response = mockMvc.perform(delete("/users/{userId}", userId));
	      
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
	    }
	}


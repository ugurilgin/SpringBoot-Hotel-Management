package com.ugisoftware.hotelmanagement;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ugisoftware.hotelmanagement.controllers.EmployeeController;
import com.ugisoftware.hotelmanagement.dto.response.EmployeeResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Employee;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.EmployeeRepository;
import com.ugisoftware.hotelmanagement.services.EmployeeService;
import com.ugisoftware.hotelmanagement.utils.Blood;
import com.ugisoftware.hotelmanagement.utils.Gender;

@WebMvcTest(EmployeeController.class)
@Import(EmployeeController.class)
public class EmployeeControllerTest {
	
   
		
	    @Autowired
	    private MockMvc mockMvc;
	
	    @MockBean
	    private EmployeeService employeeService;
	    @MockBean
	    private EmployeeRepository employeeRepository;
	
	    @Autowired
	    private ObjectMapper objectMapper;
	    
	
	
		@Test
	    public void givenEmployeeObject_whenCreateEmployee_thenReturnSavedEmployee() throws Exception{
	
	        // given - precondition or setup
	    	Employee newEmployee =new Employee(1L,"Test-Name","Test-Surname","Test-Job","01-01-1999",Gender.Erkek,"01-01-2020","01-01-2021","Test-Adress","183-456-7744","test@test.com",Blood.ABRhN,1000);
	
	
	        given(employeeService.createEmployee(any(Employee.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));
	
	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/employee")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(newEmployee)));
	
	        // then - verify the result or output using assert statements
	        response.andDo(print()).
	                andExpect(status().isOk())
	                .andExpect(jsonPath("$.name",
	                        is(newEmployee.getName())))
	                .andExpect(jsonPath("$.surname",
	                        is(newEmployee.getSurname())))
	                .andExpect(jsonPath("$.birthDate",
	                        is(newEmployee.getBirthDate())))
	                
	                .andExpect(jsonPath("$.startDate",
	                        is(newEmployee.getStartDate())))
	                .andExpect(jsonPath("$.finishDate",
	                        is(newEmployee.getFinishDate())))
	                .andExpect(jsonPath("$.adress",
	                        is(newEmployee.getAdress())))
	                
	                .andExpect(jsonPath("$.salary",
	                        is(newEmployee.getSalary())))
	                .andExpect(jsonPath("$.phone",
	                        is(newEmployee.getPhone())))
	                .andExpect(jsonPath("$.job",
	                        is(newEmployee.getJob())))
	                .andExpect(jsonPath("$.email",
	                        is(newEmployee.getEmail())));
	       
	    }
		
		@Test
	    public void givenEmployeeObject_whenCreateEmployeewitAlreadyUsedEmail_thenReturn40withEmailError() throws Exception{
	        
	        // given - precondition or setup
	    	Employee newEmployee =new Employee(1L,"Test-Name","Test-Surname","Test-Job","01-01-1999",Gender.Erkek,"01-01-2022","01-01-2021","Test-Adress","183-456-7744","testsame@test.com",Blood.ABRhN,1000);
	
		given(employeeRepository.getByEmail(newEmployee.getEmail())).willReturn(newEmployee);
	
	        given(employeeService.createEmployee(any(Employee.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));
	        
	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/employee")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(newEmployee)));
	        // then - verify the result or output using assert statements
	        
	        response.andDo(print()).
	                andExpect(status().isOk());
	       
	    }
		
		   // JUnit test for Get All employees REST API
	    @Test
	    public void givenListOfEmployees_whenGetAllEmployees_thenReturnEmployeesList() throws Exception{
	        // given - precondition or setup
	        List<EmployeeResponseDTO > listOfEmployees = new ArrayList<>();
	        listOfEmployees.add(new EmployeeResponseDTO(  new Employee(1L,"Test-Name","Test-Surname","Test-Job","01-01-1999",Gender.Erkek,"01-01-2020","01-01-2021","Test-Adress","183-456-7744","test@test.com",Blood.ABRhN,1000)));
	        listOfEmployees.add(new EmployeeResponseDTO(new Employee(2L,"Test-Name-2","Test-Surname-2","Test-Job-2","01-01-1999",Gender.Erkek,"01-01-2020","01-01-2021","Test-Adress-2","184-456-7744","test2@test.com",Blood.ABRhN,2000)));
	        given(employeeService.getAllEmployee()).willReturn(listOfEmployees);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/employee"));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.size()",
	                        is(listOfEmployees.size())));
	
	    }
	    @Test
	    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject() throws Exception{
	        // given - precondition or setup
	        long employeeId = 1L;
	    	Employee employee =new Employee(1L,"Test-Name","Test-Surname","Test-Job","01-01-1999",Gender.Erkek,"01-01-2020","01-01-2021","Test-Adress","183-456-7744","test@test.com",Blood.ABRhN,1000);
	
	        given(employeeService.getEmployee(employeeId)).willReturn(employee);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/employee/{employeeId}", employeeId));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.name", is(employee.getName())))
	                .andExpect(jsonPath("$.surname", is(employee.getSurname())))
	                .andExpect(jsonPath("$.email", is(employee.getEmail())));
	
	    }
	    @Test
	    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnException() throws Exception{
	        // given - precondition or setup
	        long employeeId = 1L;
	     
	       
	     	when(employeeService.getEmployee(employeeId)).thenThrow(new EntityNotFoundException("EmployeeId " + employeeId + " not found"));
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/employee/{employeeId}", employeeId));
	
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
	
	    }
	    @Test
	    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnUpdateEmployeeObject() throws Exception{
	        // given - precondition or setup
	        long employeeId = 1L;
	    	Employee savedEmployee =new Employee(1L,"Test-Name","Test-Surname","Test-Job","01-01-1999",Gender.Erkek,"01-01-2020","01-01-2021","Test-Adress","183-456-7744","test@test.com",Blood.ABRhN,1000);
	
	
	    	Employee updatedEmployee =new Employee(1L,"Test-Name-Update","Test-Surname-Update","Test-Job-Update","01-01-1999",Gender.Erkek,"01-01-2020","01-01-2021","Test-Adress-Update","183-456-7744","test-Update@test.com",Blood.ABRhN,1000);
	
	        given(employeeRepository.findById(employeeId)).willReturn(Optional.of(savedEmployee) );
	        given(employeeService.getEmployee(employeeId)).willReturn(savedEmployee);
	
	        given(employeeService.updateEmployee(anyLong(),any(Employee.class)))
	        .willReturn(updatedEmployee);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(put("/employee/{employeeId}", employeeId)
	                                    .contentType(MediaType.APPLICATION_JSON)
	                                    .content(objectMapper.writeValueAsString(updatedEmployee)));
	
	
	        // then - verify the output
	        response.andDo(print()).
	        andExpect(status().isOk())
	        .andExpect(jsonPath("$.name",
	                is(updatedEmployee.getName())))
	        .andExpect(jsonPath("$.surname",
	                is(updatedEmployee.getSurname())))
	        .andExpect(jsonPath("$.birthDate",
	                is(updatedEmployee.getBirthDate())))
	        
	        .andExpect(jsonPath("$.startDate",
	                is(updatedEmployee.getStartDate())))
	        .andExpect(jsonPath("$.finishDate",
	                is(updatedEmployee.getFinishDate())))
	        .andExpect(jsonPath("$.adress",
	                is(updatedEmployee.getAdress())))
	        
	        .andExpect(jsonPath("$.salary",
	                is(updatedEmployee.getSalary())))
	        .andExpect(jsonPath("$.phone",
	                is(updatedEmployee.getPhone())))
	        .andExpect(jsonPath("$.job",
	                is(updatedEmployee.getJob())))
	        .andExpect(jsonPath("$.email",
	                is(updatedEmployee.getEmail())));
	    }
	    @Test
	    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturnException() throws Exception{
	        // given - precondition or setup
	    	 long employeeId = 1L;
	     	Employee updatedEmployee =new Employee(1L,"Test-Name-Update","Test-Surname-Update","Test-Job-Update","01-01-1999",Gender.Erkek,"01-01-2020","01-01-2021","Test-Adress-Update","183-456-7744","test-Update@test.com",Blood.ABRhN,1000);
	
	     	  given(employeeRepository.findById(employeeId)).willReturn(null );
	     	
	         
	     	when(employeeService.updateEmployee(anyLong(),any(Employee.class))).thenThrow(new EntityNotFoundException("EmployeeId " + employeeId + " not found"));
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(put("/employee/{employeeId}", employeeId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(updatedEmployee)));
	
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
	    }
	    @Test
	    public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception{
	        // given - precondition or setup
	        long employeeId = 1L;
	        Employee savedEmployee =new Employee(1L,"Test-Name","Test-Surname","Test-Job","01-01-1999",Gender.Erkek,"01-01-2020","01-01-2021","Test-Adress","183-456-7744","test@test.com",Blood.ABRhN,1000);
	        
	        given(employeeRepository.findById(employeeId)).willReturn(Optional.of(savedEmployee) );
	        given(employeeService.deleteEmployee(employeeId))
	        .willAnswer((invocation)-> invocation.getArgument(0));
	     
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(delete("/employee/{employeeId}", employeeId));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print());
	    }
	    @Test
	    public void givenEmployeeId_whenDeleteEmployee_thenReturnException() throws Exception{
	        // given - precondition or setup
	        long employeeId = 1L;
	        
	        given(employeeRepository.findById(employeeId)).willReturn(null );
	     	when(employeeService.deleteEmployee(employeeId)).thenThrow(new EntityNotFoundException("EmployeeId " + employeeId + " not found"));
	
	     
	
	        // when -  action or the behaviour that we are going test
	     	
	        ResultActions response = mockMvc.perform(delete("/employee/{employeeId}", employeeId));
	      
	        // then - verify the output
	        response.andExpect(status().isOk())
	        .andDo(print());
	    }
	}



	
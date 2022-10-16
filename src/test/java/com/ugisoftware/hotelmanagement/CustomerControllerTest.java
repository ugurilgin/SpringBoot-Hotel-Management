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
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ugisoftware.hotelmanagement.controllers.CustomerController;
import com.ugisoftware.hotelmanagement.dto.request.CustomerCreateDTO;
import com.ugisoftware.hotelmanagement.dto.response.CustomerResponseDTO;

import com.ugisoftware.hotelmanagement.entities.Customers;

import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.CustomerRepository;
import com.ugisoftware.hotelmanagement.services.CustomerService;
import com.ugisoftware.hotelmanagement.services.ExtrasService;
import com.ugisoftware.hotelmanagement.services.RestaurantService;
import com.ugisoftware.hotelmanagement.services.ServiceService;
import com.ugisoftware.hotelmanagement.utils.Blood;
import com.ugisoftware.hotelmanagement.utils.Gender;



@WebMvcTest(CustomerController.class)
@Import(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;
    @MockBean
    private ExtrasService extrasService;
    @MockBean
    private RestaurantService restaurantService;
    @MockBean
    private ServiceService serviceService;
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void givenCustomerObject_whenCreateCustomer_thenReturnSavedCustomer() throws Exception{

        // given - precondition or setup
    	CustomerCreateDTO newCustomers =new CustomerCreateDTO(1L,"Test-Name","Test-Surname","Test-Adress",Blood.ABRhN,"test@test.com",Gender.Erkek,"183-456-7744");
    	Customers savedCustomers=new Customers();
            
    		
    	savedCustomers.setAdress("Test-Adress");
    	savedCustomers.setPhone("183-456-7844");
    	savedCustomers.setBlood(Blood.ABRhN);
    	savedCustomers.setEmail("test@test.com");
    	savedCustomers.setGender(Gender.Erkek);
    	savedCustomers.setName("Test-Name");
    	savedCustomers.setSurname("Test-Surname");
    	savedCustomers.setId(1L);
        given(customerService.createCustomer(any(CustomerCreateDTO.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/customer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newCustomers)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
        andExpect(status().isOk())
        .andExpect(jsonPath("$.name",
                is(savedCustomers.getName())))
        .andExpect(jsonPath("$.surname",
                is(savedCustomers.getSurname())))

        .andExpect(jsonPath("$.adress",
                is(savedCustomers.getAdress())))
        
        .andExpect(jsonPath("$.phone",
                is(savedCustomers.getPhone())))
      
        .andExpect(jsonPath("$.email",
                is(savedCustomers.getEmail())));
       
    }
	
	
	
	   // JUnit test for Get All Customers REST API
    @Test
    public void givenListOfCustomers_whenGetAllCustomers_thenReturnCustomersList() throws Exception{
        // given - precondition or setup
    	Customers newCustomers=new Customers();
    	Customers newCustomers2=new Customers();
    	
        List<Customers > customersList = new ArrayList<>();
        List<CustomerResponseDTO > customerResponseList = new ArrayList<>();
        
        newCustomers.setAdress("Test-Adress");
		newCustomers.setPhone("183-456-7844");
		newCustomers.setBlood(Blood.ABRhN);
		newCustomers.setEmail("test@test.com");
		newCustomers.setGender(Gender.Erkek);
		newCustomers.setName("Test-Name");
		newCustomers.setSurname("Test-Surname");
		newCustomers.setId(1L);
		
		newCustomers2.setAdress("Test-Adress2");
		newCustomers2.setPhone("183-456-7846");
		newCustomers2.setBlood(Blood.ABRhN);
		newCustomers2.setEmail("test2@test.com");
		newCustomers2.setGender(Gender.Erkek);
		newCustomers2.setName("Test-Name2");
		newCustomers2.setSurname("Test-Surname2");
		newCustomers2.setId(2L);
		
        customersList.add(newCustomers);
        customersList.add(newCustomers2);
        
       
        customerResponseList= customersList.stream().map(customer -> new CustomerResponseDTO(customer)).collect(Collectors.toList());

    	given(customerService.getAllCustomers()).willReturn(customerResponseList);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/customer"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(customerResponseList.size())));

    }
    @Test
    public void givenCustomerId_whenGetCustomerById_thenReturnCustomerObject() throws Exception{
        // given - precondition or setup
        long customerId = 1L;
        Customers savedCustomers=new Customers();
        
		
    	savedCustomers.setAdress("Test-Adress");
    	savedCustomers.setPhone("183-456-7844");
    	savedCustomers.setBlood(Blood.ABRhN);
    	savedCustomers.setEmail("test@test.com");
    	savedCustomers.setGender(Gender.Erkek);
    	savedCustomers.setName("Test-Name");
    	savedCustomers.setSurname("Test-Surname");
    	savedCustomers.setId(1L);
        given(customerService.getCustomer(customerId)).willReturn(savedCustomers);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/customer/{customerId}", customerId));

        // then - verify the output
        response.andDo(print()).
        andExpect(status().isOk())
        .andExpect(jsonPath("$.name",
                is(savedCustomers.getName())))
        .andExpect(jsonPath("$.surname",
                is(savedCustomers.getSurname())))

        .andExpect(jsonPath("$.adress",
                is(savedCustomers.getAdress())))
        
        .andExpect(jsonPath("$.phone",
                is(savedCustomers.getPhone())))
      
        .andExpect(jsonPath("$.email",
                is(savedCustomers.getEmail())));
           

    }
    @Test
    public void givenInvalidCustomerId_whenGetCustomerById_thenReturnException() throws Exception{
        // given - precondition or setup
        long customerId = 1L;
     
       
     	when(customerService.getCustomer(customerId)).thenThrow(new EntityNotFoundException("customerId " + customerId + " not found"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/customer/{customerId}", customerId));

        // then - verify the output
        response.andExpect(status().isNotFound())
        .andDo(print());

    }
    @Test
    public void givenUpdatedCustomer_whenUpdateCustomer_thenReturnUpdateCustomerObject() throws Exception{
        // given - precondition or setup
    	 long customerId = 1L;
    	 Customers savedCustomers=new Customers();
     	
         
         savedCustomers.setAdress("Test-Adress");
         savedCustomers.setPhone("183-456-7844");
         savedCustomers.setBlood(Blood.ABRhN);
         savedCustomers.setEmail("test@test.com");
 		savedCustomers.setGender(Gender.Erkek);
 		savedCustomers.setName("Test-Name");
 		savedCustomers.setSurname("Test-Surname");
 		savedCustomers.setId(1L);

 		 Customers updatedCustomers=new Customers();
 	    	
 	        
 		 updatedCustomers.setAdress("Test-Adress2");
 		 updatedCustomers.setPhone("183-456-7844");
 		 updatedCustomers.setBlood(Blood.ABRhN);
 		 updatedCustomers.setEmail("tes2t@test.com");
 		 updatedCustomers.setGender(Gender.Erkek);
 		 updatedCustomers.setName("Test-Name2");
 		 updatedCustomers.setSurname("Test-Surname2");
 		 updatedCustomers.setId(1L);
        given(customerRepository.findById(customerId)).willReturn(Optional.of(savedCustomers) );
        given(customerService.getCustomer(customerId)).willReturn(savedCustomers);

        given(customerService.updateCustomer(anyLong(),any(Customers.class)))
        .willReturn(updatedCustomers);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/customer/{customerId}", customerId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(updatedCustomers)));


        // then - verify the output
        response.andDo(print()).
        andExpect(status().isOk())
        .andExpect(jsonPath("$.name",
                is(updatedCustomers.getName())))
        .andExpect(jsonPath("$.surname",
                is(updatedCustomers.getSurname())))

        .andExpect(jsonPath("$.adress",
                is(updatedCustomers.getAdress())))
        
        .andExpect(jsonPath("$.phone",
                is(updatedCustomers.getPhone())))
      
        .andExpect(jsonPath("$.email",
                is(updatedCustomers.getEmail())));
    }
    @Test
    public void givenUpdatedCustomer_whenUpdateCustomer_thenReturnException() throws Exception{
        // given - precondition or setup
    	long customerId = 1L;
    	 Customers updatedCustomers=new Customers();
	    	
	        
		 updatedCustomers.setAdress("Test-Adress2");
		 updatedCustomers.setPhone("183-456-7844");
		 updatedCustomers.setBlood(Blood.ABRhN);
		 updatedCustomers.setEmail("tes2t@test.com");
		 updatedCustomers.setGender(Gender.Erkek);
		 updatedCustomers.setName("Test-Name2");
		 updatedCustomers.setSurname("Test-Surname2");
		 updatedCustomers.setId(1L);

     	  given(customerRepository.findById(customerId)).willReturn(null );
     	
         
     	when(customerService.updateCustomer(anyLong(),any(Customers.class))).thenThrow(new EntityNotFoundException("customerId " + customerId + " not found"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/customer/{customerId}", customerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCustomers)));

        // then - verify the output
        response.andExpect(status().isNotFound())
        .andDo(print());
    }
    @Test
    public void givenCustomerId_whenDeleteCustomer_thenReturn200() throws Exception{
        // given - precondition or setup
    	long customerId = 1L;
    	 Customers savedCustomers=new Customers();
	    	
	        
    	 savedCustomers.setAdress("Test-Adress2");
    	 savedCustomers.setPhone("183-456-7844");
    	 savedCustomers.setBlood(Blood.ABRhN);
    	 savedCustomers.setEmail("tes2t@test.com");
    	 savedCustomers.setGender(Gender.Erkek);
    	 savedCustomers.setName("Test-Name2");
    	 savedCustomers.setSurname("Test-Surname2");
    	 savedCustomers.setId(1L);
        
        given(customerRepository.findById(customerId)).willReturn(Optional.of(savedCustomers) );
        given(customerService.deleteCustomer(customerId))
        .willAnswer((invocation)-> invocation.getArgument(0));
     

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/customer/{customerId}", customerId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void givenCustomerId_whenDeleteCustomer_thenReturnException() throws Exception{
        // given - precondition or setup
        long customerId = 1L;
        
        given(customerRepository.findById(customerId)).willReturn(null );
     	when(customerService.deleteCustomer(customerId)).thenThrow(new EntityNotFoundException("customerId " + customerId + " not found"));

     

        // when -  action or the behaviour that we are going test
     	
        ResultActions response = mockMvc.perform(delete("/customer/{customerId}", customerId));
      
        // then - verify the output
        response.andExpect(status().isNotFound())
        .andDo(print());
    }
}

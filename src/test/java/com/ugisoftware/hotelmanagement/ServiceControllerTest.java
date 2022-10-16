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
import com.ugisoftware.hotelmanagement.controllers.ServiceController;

import com.ugisoftware.hotelmanagement.entities.Services;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.ServicesRepositories;
import com.ugisoftware.hotelmanagement.services.ServiceService;




@WebMvcTest(ServiceController.class)
@Import(ServiceController.class)
public class ServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceService serviceService;
    @MockBean
    private ServicesRepositories serviceRepository;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void givenServiceObject_whenCreateService_thenReturnSavedService() throws Exception{

        // given - precondition or setup
    	Services newService=new Services();
    	newService.setId(1L);
    	newService.setName("Test");
    	newService.setPrice(0);
    	newService.setCustomers(null);
        given(serviceService.createService(any(Services.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/services")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newService)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        is(newService.getName())))
                
                .andExpect(jsonPath("$.price",
                        is(newService.getPrice())));
       
    }
	
	
	
	   // JUnit test for Get All service REST API
    @Test
    public void givenListOfService_whenGetAllService_thenReturnServicesList() throws Exception{
        // given - precondition or setup
       List<Services>  listService=new ArrayList<>();
    	Services newService=new Services();
    	newService.setId(1L);
    	newService.setName("Test");
    	newService.setPrice(0);
    	newService.setCustomers(null);        
    	Services newService2=new Services();
    	newService2.setId(2L);
    	newService2.setName("Test2");
    	newService2.setPrice(0);
    	newService2.setCustomers(null);  
    	listService.add(newService);
    	listService.add(newService2);

    	given(serviceService.getAllServices()).willReturn(listService);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/services"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listService.size())));

    }
    @Test
    public void givenServiceId_whenGetServiceById_thenReturnServiceObject() throws Exception{
        // given - precondition or setup
        long serviceId = 1L;
        Services newService=new Services();
    	newService.setId(1L);
    	newService.setName("Test");
    	newService.setPrice(0);
    	newService.setCustomers(null);
        given(serviceService.getService(serviceId)).willReturn(newService);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/services/{serviceId}", serviceId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(newService.getName())))
                .andExpect(jsonPath("$.price", is(newService.getPrice())));
           

    }
    @Test
    public void givenInvalidServiceId_whenGetServiceById_thenReturnException() throws Exception{
        // given - precondition or setup
        long serviceId = 1L;
     
       
     	when(serviceService.getService(serviceId)).thenThrow(new EntityNotFoundException("ServiceId " + serviceId + " not found"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/services/{ServiceId}", serviceId));

        // then - verify the output
        response.andExpect(status().isNotFound())
        .andDo(print());

    }
    @Test
    public void givenUpdatedService_whenUpdateService_thenReturnUpdateServiceObject() throws Exception{
        // given - precondition or setup
    	 long serviceId = 1L;
         Services savedService=new Services();
         savedService.setId(1L);
         savedService.setName("Test");
         savedService.setPrice(0);
         savedService.setCustomers(null);
         Services uptatedService=new Services();
         uptatedService.setId(1L);
         uptatedService.setName("Test2");
         uptatedService.setPrice(1);
         uptatedService.setCustomers(null);
        given(serviceRepository.findById(serviceId)).willReturn(Optional.of(savedService) );
        given(serviceService.getService(serviceId)).willReturn(savedService);

        given(serviceService.updateService(anyLong(),any(Services.class)))
        .willReturn(uptatedService);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/services/{ServiceId}", serviceId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(uptatedService)));


        // then - verify the output
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        is(uptatedService.getName())))
                
                .andExpect(jsonPath("$.price",
                        is(uptatedService.getPrice())));
    }
    @Test
    public void givenUpdatedService_whenUpdateService_thenReturnException() throws Exception{
        // given - precondition or setup
    	long serviceId = 1L;
        Services savedService=new Services();
        savedService.setId(1L);
        savedService.setName("Test");
        savedService.setPrice(0);
        savedService.setCustomers(null);

     	  given(serviceRepository.findById(serviceId)).willReturn(null );
     	
         
     	when(serviceService.updateService(anyLong(),any(Services.class))).thenThrow(new EntityNotFoundException("ServiceId " + serviceId + " not found"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/services/{ServiceId}", serviceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedService)));

        // then - verify the output
        response.andExpect(status().isNotFound())
        .andDo(print());
    }
    @Test
    public void givenServiceId_whenDeleteService_thenReturn200() throws Exception{
        // given - precondition or setup
    	long serviceId = 1L;
        Services savedService=new Services();
        savedService.setId(1L);
        savedService.setName("Test");
        savedService.setPrice(0);
        savedService.setCustomers(null);
        
        given(serviceRepository.findById(serviceId)).willReturn(Optional.of(savedService) );
        given(serviceService.deleteService(serviceId))
        .willAnswer((invocation)-> invocation.getArgument(0));
     

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/services/{serviceId}", serviceId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void givenServiceId_whenDeleteService_thenReturnException() throws Exception{
        // given - precondition or setup
        long serviceId = 1L;
        
        given(serviceRepository.findById(serviceId)).willReturn(null );
     	when(serviceService.deleteService(serviceId)).thenThrow(new EntityNotFoundException("ServiceId " + serviceId + " not found"));

     

        // when -  action or the behaviour that we are going test
     	
        ResultActions response = mockMvc.perform(delete("/services/{serviceId}", serviceId));
      
        // then - verify the output
        response.andExpect(status().isOk())
        .andDo(print());
    }
}

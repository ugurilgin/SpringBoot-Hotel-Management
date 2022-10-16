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
import com.ugisoftware.hotelmanagement.controllers.CustomerBillController;
import com.ugisoftware.hotelmanagement.dto.request.BillCreateDTO;
import com.ugisoftware.hotelmanagement.dto.response.BillResponseDTO;
import com.ugisoftware.hotelmanagement.entities.CustomerBill;
import com.ugisoftware.hotelmanagement.entities.Customers;
import com.ugisoftware.hotelmanagement.entities.Rooms;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.CustomerBillRepository;
import com.ugisoftware.hotelmanagement.services.CustomerBillService;


@WebMvcTest(CustomerBillController.class)
@Import(CustomerBillController.class)
public class CustomerBillControllerTest {
	
   
		
	    @Autowired
	    private MockMvc mockMvc;
	
	    @MockBean
	    private CustomerBillService billService;
	    @MockBean
	    private CustomerBillRepository billRepository;
	
	    @Autowired
	    private ObjectMapper objectMapper;
	    
	
	
		@Test
	    public void givenCustomerBillObject_whenCreateCustomerBill_thenReturnSavedCustomerBill() throws Exception{
	
	        // given - precondition or setup
	    	BillCreateDTO newbill =new BillCreateDTO(1L,1,"01-01-1999","01-01-2020",1L,1L);
	    
	
	        given(billService.createBill(any(BillCreateDTO.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));
	
	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/bill")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(newbill)));
	
	        // then - verify the result or output using assert statements
	        response.andDo(print()).
	                andExpect(status().isOk());
	                
	       
	    }
		
		
		
		   // JUnit test for Get All CustomerBills REST API
	    @Test
	    public void givenListOfCustomerBills_whenGetAllCustomerBills_thenReturnCustomerBillsList() throws Exception{
	        // given - precondition or setup
	        List<BillResponseDTO > listOfBills = new ArrayList<>();
	        listOfBills.add(new BillResponseDTO(  new CustomerBill(1L,new Customers(),"01-01-2020","05-01-2020",1,new Rooms())));
	        listOfBills.add(new BillResponseDTO(new CustomerBill(2L,new Customers(),"01-01-2020","05-01-2020",1,new Rooms())));
	        given(billService.getAllBills()).willReturn(listOfBills);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/bill"));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.size()",
	                        is(listOfBills.size())));
	
	    }
	    @Test
	    public void givenbillId_whenGetCustomerBillById_thenReturnCustomerBillObject() throws Exception{
	        // given - precondition or setup
	        long billId = 1L;
	    CustomerBill bill=   new CustomerBill(1L,new Customers(),"01-01-2020","05-01-2020",1,new Rooms());	
	        given(billService.getBill(billId)).willReturn(new BillResponseDTO( bill));
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/bill/{billId}", billId));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print());
	
	    }
	    @Test
	    public void givenInvalidbillId_whenGetCustomerBillById_thenReturnException() throws Exception{
	        // given - precondition or setup
	        long billId = 1L;
	     
	       
	     	when(billService.getBill(billId)).thenThrow(new EntityNotFoundException("billId " + billId + " not found"));
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/bill/{billId}", billId));
	
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
	
	    }
	    @Test
	    public void givenUpdatedCustomerBill_whenUpdateCustomerBill_thenReturnUpdateCustomerBillObject() throws Exception{
	        // given - precondition or setup
	        long billId = 1L;
		    CustomerBill savedBill=   new CustomerBill(1L,new Customers(),"01-01-2020","05-01-2020",1,new Rooms());	
		    CustomerBill reBill=   new CustomerBill(1L,new Customers(),"01-01-1999","01-01-2020",1,new Rooms());	

	
	    	BillCreateDTO updatedBill =new BillCreateDTO(1L,1,"01-01-1999","01-01-2020",1L,1L);
	
	        given(billRepository.findById(billId)).willReturn(Optional.of(savedBill) );
	        given(billService.getBill(billId)).willReturn(new BillResponseDTO(savedBill));
	
	        given(billService.updateBill(anyLong(),any(BillCreateDTO.class)))
	        .willReturn(reBill);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(put("/bill/{billId}", billId)
	                                    .contentType(MediaType.APPLICATION_JSON)
	                                    .content(objectMapper.writeValueAsString(updatedBill)));
	
	
	        // then - verify the output
	        response.andDo(print()).
	        andExpect(status().isOk());
	    }
	    @Test
	    public void givenUpdatedCustomerBill_whenUpdateCustomerBill_thenReturnException() throws Exception{
	        // given - precondition or setup
	    	 long billId = 1L;
		    	BillCreateDTO updatedBill =new BillCreateDTO(1L,1,"01-01-1999","01-01-2020",1L,1L);
	
	     	  given(billRepository.findById(billId)).willReturn(null );
	     	
	         
	     	when(billService.updateBill(anyLong(),any(BillCreateDTO.class))).thenThrow(new EntityNotFoundException("billId " + billId + " not found"));
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(put("/bill/{billId}", billId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(updatedBill)));
	
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
	    }
	    @Test
	    public void givenbillId_whenDeleteCustomerBill_thenReturn200() throws Exception{
	        // given - precondition or setup
	        long billId = 1L;
		    CustomerBill savedBill=   new CustomerBill(1L,new Customers(),"01-01-2020","05-01-2020",1,new Rooms());	
	        
	        given(billRepository.findById(billId)).willReturn(Optional.of(savedBill) );
	        given(billService.deleteBill(billId))
	        .willAnswer((invocation)-> invocation.getArgument(0));
	     
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(delete("/bill/{billId}", billId));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print());
	    }
	    @Test
	    public void givenbillId_whenDeleteCustomerBill_thenReturnException() throws Exception{
	        // given - precondition or setup
	        long billId = 1L;
	        
	        given(billRepository.findById(billId)).willReturn(null );
	     	when(billService.deleteBill(billId)).thenThrow(new EntityNotFoundException("billId " + billId + " not found"));
	
	     
	
	        // when -  action or the behaviour that we are going test
	     	
	        ResultActions response = mockMvc.perform(delete("/bill/{billId}", billId));
	      
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
	    }
	}



	
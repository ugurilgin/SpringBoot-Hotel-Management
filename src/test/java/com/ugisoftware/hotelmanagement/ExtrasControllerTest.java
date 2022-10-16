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
import com.ugisoftware.hotelmanagement.controllers.ExtrasController;

import com.ugisoftware.hotelmanagement.dto.response.ExtrasResponseDTO;

import com.ugisoftware.hotelmanagement.entities.Extras;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.ExtrasRepository;
import com.ugisoftware.hotelmanagement.services.ExtrasService;




@WebMvcTest(ExtrasController.class)
@Import(ExtrasController.class)
public class ExtrasControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExtrasService extrasService;
    @MockBean
    private ExtrasRepository extrasRepository;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void givenExtraObject_whenCreateExtra_thenReturnSavedExtra() throws Exception{

        // given - precondition or setup
    	Extras newExtras=new Extras();
    	newExtras.setId(1L);
    	newExtras.setName("Test");
    	newExtras.setPrice(0);
    	newExtras.setCustomers(null);
        given(extrasService.createExtras(any(Extras.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/extras")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newExtras)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        is(newExtras.getName())))
                
                .andExpect(jsonPath("$.price",
                        is(newExtras.getPrice())));
       
    }
	
	
	
	   // JUnit test for Get All Extras REST API
    @Test
    public void givenListOfExtras_whenGetAllExtras_thenReturnExtrasList() throws Exception{
        // given - precondition or setup
        List<ExtrasResponseDTO > listOfExtras = new ArrayList<>();
       List<Extras>  listExtras=new ArrayList<>();
    	Extras newExtras=new Extras();
    	newExtras.setId(1L);
    	newExtras.setName("Test");
    	newExtras.setPrice(0);
    	newExtras.setCustomers(null);        
    	Extras newExtras2=new Extras();
    	newExtras2.setId(2L);
    	newExtras2.setName("Test2");
    	newExtras2.setPrice(0);
    	newExtras2.setCustomers(null);  
    	listExtras.add(newExtras);
    	listExtras.add(newExtras2);
    	listOfExtras=  listExtras.stream().map(list -> new ExtrasResponseDTO(list)).collect(Collectors.toList());

    	given(extrasService.getAllExtras()).willReturn(listOfExtras);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/extras"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfExtras.size())));

    }
    @Test
    public void givenExtraId_whenGetExtraById_thenReturnExtraObject() throws Exception{
        // given - precondition or setup
        long extrasId = 1L;
        Extras newExtras=new Extras();
    	newExtras.setId(1L);
    	newExtras.setName("Test");
    	newExtras.setPrice(0);
    	newExtras.setCustomers(null);
        given(extrasService.getExtras(extrasId)).willReturn(newExtras);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/extras/{extrasId}", extrasId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(newExtras.getName())))
                .andExpect(jsonPath("$.price", is(newExtras.getPrice())));
           

    }
    @Test
    public void givenInvalidExtraId_whenGetExtraById_thenReturnException() throws Exception{
        // given - precondition or setup
        long extrasId = 1L;
     
       
     	when(extrasService.getExtras(extrasId)).thenThrow(new EntityNotFoundException("ExtrasId " + extrasId + " not found"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/extras/{extrasId}", extrasId));

        // then - verify the output
        response.andExpect(status().isNotFound())
        .andDo(print());

    }
    @Test
    public void givenUpdatedExtra_whenUpdateExtra_thenReturnUpdateExtraObject() throws Exception{
        // given - precondition or setup
    	 long extrasId = 1L;
         Extras savedExtras=new Extras();
         savedExtras.setId(1L);
         savedExtras.setName("Test");
         savedExtras.setPrice(0);
         savedExtras.setCustomers(null);
         Extras uptatedExtras=new Extras();
         uptatedExtras.setId(1L);
         uptatedExtras.setName("Test2");
         uptatedExtras.setPrice(1);
         uptatedExtras.setCustomers(null);
        given(extrasRepository.findById(extrasId)).willReturn(Optional.of(savedExtras) );
        given(extrasService.getExtras(extrasId)).willReturn(savedExtras);

        given(extrasService.updateExtras(anyLong(),any(Extras.class)))
        .willReturn(uptatedExtras);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/extras/{extrasId}", extrasId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(uptatedExtras)));


        // then - verify the output
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        is(uptatedExtras.getName())))
                
                .andExpect(jsonPath("$.price",
                        is(uptatedExtras.getPrice())));
    }
    @Test
    public void givenUpdatedExtra_whenUpdateExtra_thenReturnException() throws Exception{
        // given - precondition or setup
    	long extrasId = 1L;
        Extras savedExtras=new Extras();
        savedExtras.setId(1L);
        savedExtras.setName("Test");
        savedExtras.setPrice(0);
        savedExtras.setCustomers(null);

     	  given(extrasRepository.findById(extrasId)).willReturn(null );
     	
         
     	when(extrasService.updateExtras(anyLong(),any(Extras.class))).thenThrow(new EntityNotFoundException("ExtrasId " + extrasId + " not found"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/extras/{extrasId}", extrasId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedExtras)));

        // then - verify the output
        response.andExpect(status().isNotFound())
        .andDo(print());
    }
    @Test
    public void givenExtraId_whenDeleteExtra_thenReturn200() throws Exception{
        // given - precondition or setup
    	long extrasId = 1L;
        Extras savedExtras=new Extras();
        savedExtras.setId(1L);
        savedExtras.setName("Test");
        savedExtras.setPrice(0);
        savedExtras.setCustomers(null);
        
        given(extrasRepository.findById(extrasId)).willReturn(Optional.of(savedExtras) );
        given(extrasService.deleteExtras(extrasId))
        .willAnswer((invocation)-> invocation.getArgument(0));
     

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/extras/{extrasId}", extrasId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void givenExtraId_whenDeleteExtra_thenReturnException() throws Exception{
        // given - precondition or setup
        long extrasId = 1L;
        
        given(extrasRepository.findById(extrasId)).willReturn(null );
     	when(extrasService.deleteExtras(extrasId)).thenThrow(new EntityNotFoundException("ExtrasId " + extrasId + " not found"));

     

        // when -  action or the behaviour that we are going test
     	
        ResultActions response = mockMvc.perform(delete("/extras/{extrasId}", extrasId));
      
        // then - verify the output
        response.andExpect(status().isOk())
        .andDo(print());
    }
}

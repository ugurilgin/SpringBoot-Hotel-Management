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
import com.ugisoftware.hotelmanagement.controllers.RestaurantController;

import com.ugisoftware.hotelmanagement.entities.Restaurant;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.RestaurantRepository;
import com.ugisoftware.hotelmanagement.services.RestaurantService;




@WebMvcTest(RestaurantController.class)
@Import(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantService restaurantService;
    @MockBean
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void givenMealsObject_whenCreateMeals_thenReturnSavedMeals() throws Exception{

        // given - precondition or setup
    	Restaurant newRestaurant=new Restaurant();
    	newRestaurant.setId(1L);
    	newRestaurant.setName("Test");
    	newRestaurant.setPrice(0);
    	newRestaurant.setCustomers(null);
        given(restaurantService.createMeal(any(Restaurant.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/meals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newRestaurant)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        is(newRestaurant.getName())))
                
                .andExpect(jsonPath("$.price",
                        is(newRestaurant.getPrice())));
       
    }
	
	
	
	   // JUnit test for Get All Meals REST API
    @Test
    public void givenListOfMeals_whenGetAllMeals_thenReturnMealsList() throws Exception{
        // given - precondition or setup
       List<Restaurant>  listRestaurant=new ArrayList<>();
    	Restaurant newRestaurant=new Restaurant();
    	newRestaurant.setId(1L);
    	newRestaurant.setName("Test");
    	newRestaurant.setPrice(0);
    	newRestaurant.setCustomers(null);        
    	Restaurant newRestaurant2=new Restaurant();
    	newRestaurant2.setId(2L);
    	newRestaurant2.setName("Test2");
    	newRestaurant2.setPrice(0);
    	newRestaurant2.setCustomers(null);  
    	

    	given(restaurantService.getAllMeals()).willReturn(listRestaurant);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/meals"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listRestaurant.size())));

    }
    @Test
    public void givenMealId_whenGetMealById_thenReturnMealObject() throws Exception{
        // given - precondition or setup
        long mealsId = 1L;
        Restaurant newRestaurant=new Restaurant();
    	newRestaurant.setId(1L);
    	newRestaurant.setName("Test");
    	newRestaurant.setPrice(0);
    	newRestaurant.setCustomers(null);
        given(restaurantService.getMeal(mealsId)).willReturn(newRestaurant);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/meals/{mealsId}", mealsId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(newRestaurant.getName())))
                .andExpect(jsonPath("$.price", is(newRestaurant.getPrice())));
           

    }
    @Test
    public void givenInvalidMealId_whenGetMealById_thenReturnException() throws Exception{
        // given - precondition or setup
        long mealsId = 1L;
     
       
     	when(restaurantService.getMeal(mealsId)).thenThrow(new EntityNotFoundException("mealsId " + mealsId + " not found"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/meals/{mealsId}", mealsId));

        // then - verify the output
        response.andExpect(status().isNotFound())
        .andDo(print());

    }
    @Test
    public void givenUpdatedMeal_whenUpdateMeal_thenReturnUpdateMealObject() throws Exception{
        // given - precondition or setup
    	 long mealsId = 1L;
         Restaurant savedRestaurant=new Restaurant();
         savedRestaurant.setId(1L);
         savedRestaurant.setName("Test");
         savedRestaurant.setPrice(0);
         savedRestaurant.setCustomers(null);
         Restaurant uptatedRestaurant=new Restaurant();
         uptatedRestaurant.setId(1L);
         uptatedRestaurant.setName("Test2");
         uptatedRestaurant.setPrice(1);
         uptatedRestaurant.setCustomers(null);
        given(restaurantRepository.findById(mealsId)).willReturn(Optional.of(savedRestaurant) );
        given(restaurantService.getMeal(mealsId)).willReturn(savedRestaurant);

        given(restaurantService.updateMeal(anyLong(),any(Restaurant.class)))
        .willReturn(uptatedRestaurant);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/meals/{mealsId}", mealsId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(uptatedRestaurant)));


        // then - verify the output
        response.andDo(print()).
                andExpect(status().isOk())
                .andExpect(jsonPath("$.name",
                        is(uptatedRestaurant.getName())))
                
                .andExpect(jsonPath("$.price",
                        is(uptatedRestaurant.getPrice())));
    }
    @Test
    public void givenUpdatedMeal_whenUpdateMeal_thenReturnException() throws Exception{
        // given - precondition or setup
    	long mealsId = 1L;
        Restaurant savedRestaurant=new Restaurant();
        savedRestaurant.setId(1L);
        savedRestaurant.setName("Test");
        savedRestaurant.setPrice(0);
        savedRestaurant.setCustomers(null);

     	  given(restaurantRepository.findById(mealsId)).willReturn(null );
     	
         
     	when(restaurantService.updateMeal(anyLong(),any(Restaurant.class))).thenThrow(new EntityNotFoundException("mealsId " + mealsId + " not found"));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/meals/{mealsId}", mealsId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(savedRestaurant)));

        // then - verify the output
        response.andExpect(status().isNotFound())
        .andDo(print());
    }
    @Test
    public void givenMealId_whenDeleteMeal_thenReturn200() throws Exception{
        // given - precondition or setup
    	long mealsId = 1L;
        Restaurant savedRestaurant=new Restaurant();
        savedRestaurant.setId(1L);
        savedRestaurant.setName("Test");
        savedRestaurant.setPrice(0);
        savedRestaurant.setCustomers(null);
        
        given(restaurantRepository.findById(mealsId)).willReturn(Optional.of(savedRestaurant) );
        given(restaurantService.deleteMeal(mealsId))
        .willAnswer((invocation)-> invocation.getArgument(0));
     

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/meals/{mealsId}", mealsId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void givenMealId_whenDeleteMeal_thenReturnException() throws Exception{
        // given - precondition or setup
        long mealsId = 1L;
        
        given(restaurantRepository.findById(mealsId)).willReturn(null );
     	when(restaurantService.deleteMeal(mealsId)).thenThrow(new EntityNotFoundException("mealsId " + mealsId + " not found"));

     

        // when -  action or the behaviour that we are going test
     	
        ResultActions response = mockMvc.perform(delete("/meals/{mealsId}", mealsId));
      
        // then - verify the output
        response.andExpect(status().isOk())
        .andDo(print());
    }
}

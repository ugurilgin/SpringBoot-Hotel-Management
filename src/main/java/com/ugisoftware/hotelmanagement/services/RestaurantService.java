package com.ugisoftware.hotelmanagement.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ugisoftware.hotelmanagement.entities.Customers;
import com.ugisoftware.hotelmanagement.entities.Restaurant;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.CustomerRepository;
import com.ugisoftware.hotelmanagement.repositories.RestaurantRepository;

@Service
public class RestaurantService {
private RestaurantRepository restaurantRepository;
private CustomerRepository customerRepository;
	 
public RestaurantService(RestaurantRepository restaurantRepository,CustomerRepository customerRepository) {
	this.restaurantRepository = restaurantRepository;
	this.customerRepository=customerRepository;
}
	public List<Restaurant> getAllMeals() {
		List<Restaurant> restaurantList;
		restaurantList= restaurantRepository.findAll();
		 return restaurantList;
	}

	public Restaurant getMeal(Long mealId) {
		Restaurant restaurant =  restaurantRepository.findById(mealId)
	             .orElseThrow(() -> new EntityNotFoundException("Meal Not Found with id : " + mealId));
	      return restaurant;
	}

	public Restaurant createMeal(@Valid Restaurant newMeal) {
		Restaurant createMeal=new Restaurant();
		createMeal.setId(newMeal.getId());
		createMeal.setName(newMeal.getName());
		createMeal.setPrice(newMeal.getPrice());
		return restaurantRepository.save(createMeal);
		
	}

	public Restaurant updateMeal(Long mealId, @Valid Restaurant newMeal) {
		return restaurantRepository.findById(mealId).map(meal -> {
				 Restaurant updateMeal=new Restaurant();
				 updateMeal.setId(newMeal.getId());
				 updateMeal.setName(newMeal.getName());
				 updateMeal.setPrice(newMeal.getPrice());
	         return restaurantRepository.save(updateMeal);
				
	     }).orElseThrow(() -> new EntityNotFoundException("MealId " + mealId + " not found"));

	}


	public ResponseEntity<?> deleteMeal(Long mealId) {
		return restaurantRepository.findById(mealId).map(meal -> {
			restaurantRepository.delete(meal);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new EntityNotFoundException("Meal not found with id: " + mealId ));
		
	}

	public ResponseEntity<List<Customers>> getAllCustomersByMealId(Long mealId) {
		return restaurantRepository.findById(mealId).map(meal -> {
			 List<Customers> customers = customerRepository.findCustomersByRestaurantId(mealId);
	        return new ResponseEntity<>(customers, HttpStatus.OK);
	    }).orElseThrow(() -> new EntityNotFoundException("Meal not found with id: " + mealId ));
		
	}
	
	public ResponseEntity<?> getAllMealsByCustomerId(Long customerId) {
		    return customerRepository.findById(customerId).map(customer -> {
			    List<Restaurant> meals = restaurantRepository.findRestaurantByCustomersId(customerId);
		        return new ResponseEntity<>(meals, HttpStatus.OK);
		    }).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId ));
	}
	
	public ResponseEntity<?> addMeals(Long customerId, @Valid Restaurant newMeals) {
		Restaurant meals = customerRepository.findById(customerId).map(customer -> {
		      long mealsId = newMeals.getId();
		      // meal is existed
		      if (mealsId >=0) {
		        Restaurant _meals = restaurantRepository.findById(mealsId)
		            .orElseThrow(() -> new EntityNotFoundException("Not found Meals with id = " + mealsId));
		        customer.addRestuarant(_meals);
		        customerRepository.save(customer);
		        return _meals;
		      }
		      
		      // add and create new Meals
		      customer.addRestuarant(newMeals);
		      return restaurantRepository.save(newMeals);
		      
		    }).orElseThrow(() -> new EntityNotFoundException("Not found Customer with id = " + customerId));
		return new ResponseEntity<>(meals, HttpStatus.CREATED);
	}

	public ResponseEntity<?> deleteMealsFromCustomer(Long customerId, Long mealsId) {
		return customerRepository.findById(customerId).map(customers -> {
			customers.removeRestuarant(mealsId);
			customerRepository.save(customers);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId ));
	}

	}
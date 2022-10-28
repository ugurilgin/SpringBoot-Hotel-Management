package com.ugisoftware.hotelmanagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugisoftware.hotelmanagement.entities.Restaurant;
import com.ugisoftware.hotelmanagement.services.RestaurantService;

@RequestMapping("/api/meals")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestaurantController {
	private RestaurantService restaurantService;

public RestaurantController(RestaurantService restaurantService)
{
	this.restaurantService=restaurantService;
}

@GetMapping
public List<Restaurant> getAllMeals() {
	return restaurantService.getAllMeals();
}

@GetMapping("/{mealsId}")
public Restaurant getMeal(@PathVariable Long mealsId) {
	return restaurantService.getMeal(mealsId);
}


@GetMapping("/{mealsId}/customers")
public ResponseEntity<?> getAllCustomersByMealId(@PathVariable Long mealsId) {

	return restaurantService.getAllCustomersByMealId(mealsId);
}
@PostMapping
public Restaurant  createMeal(@Valid @RequestBody Restaurant newMeals) {
	return restaurantService.createMeal(newMeals);
}

@PutMapping("/{mealsId}")
public Restaurant updateMeal(@PathVariable Long mealsId,@Valid @RequestBody Restaurant newMeals)
{
	return restaurantService.updateMeal(mealsId,newMeals);
} 

@DeleteMapping("/{mealsId}")
public void deleteExtras(Long mealsId)
{
	restaurantService.deleteMeal(mealsId);
}
}
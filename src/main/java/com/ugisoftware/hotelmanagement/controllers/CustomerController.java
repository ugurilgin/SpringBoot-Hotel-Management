package com.ugisoftware.hotelmanagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugisoftware.hotelmanagement.dto.request.CustomerCreateDTO;
import com.ugisoftware.hotelmanagement.dto.response.CustomerResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Customers;
import com.ugisoftware.hotelmanagement.entities.Extras;
import com.ugisoftware.hotelmanagement.entities.Restaurant;
import com.ugisoftware.hotelmanagement.entities.Services;
import com.ugisoftware.hotelmanagement.services.CustomerService;
import com.ugisoftware.hotelmanagement.services.ExtrasService;
import com.ugisoftware.hotelmanagement.services.RestaurantService;
import com.ugisoftware.hotelmanagement.services.ServiceService;

	@RequestMapping("/customer")
	@RestController
	public class CustomerController {
		private CustomerService customerService;
		private ExtrasService extrasService;
		private RestaurantService restaurantService;
		private ServiceService serviceService;
	public CustomerController(CustomerService customerService,ExtrasService extrasService,RestaurantService restaurantService,ServiceService serviceService)
	{
		this.customerService=customerService;
		this.extrasService=extrasService;
		this.restaurantService=restaurantService;
		this.serviceService=serviceService;
	}

	@GetMapping
	public List<CustomerResponseDTO> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{customerId}")
	public Customers getCustomer(@PathVariable Long customerId) {
		return customerService.getCustomer(customerId);
	}
	
	@GetMapping("/{customerId}/extras")
	public ResponseEntity<?>  getAllExtrasByCustomerId(@PathVariable  Long customerId) {
	 
		return extrasService.getAllExtrasByCustomerId(customerId);
	}
	
	@GetMapping("/{customerId}/meals")
	public ResponseEntity<?>  getAllMealsByCustomerId(@PathVariable  Long customerId) {
	 
		return restaurantService.getAllMealsByCustomerId(customerId);
	}  
	  
	@GetMapping("/{customerId}/services")
	public ResponseEntity<?>  getAllServiceByCustomerId(@PathVariable  Long customerId) {
	 
		return serviceService.getAllServiceByCustomerId(customerId);
	} 
	@PostMapping
	public Customers  createPersonel(@Valid @RequestBody CustomerCreateDTO newCustomer) {
		return customerService.createCustomer(newCustomer);
	}

	@PostMapping("/{customerId}/extras")
	public ResponseEntity<?> addExtras(@PathVariable  Long customerId, @Valid @RequestBody Extras newExtras) {
       
		return extrasService.addExtras(customerId,newExtras);
	}
	
	@PostMapping("/{customerId}/meals")
	public ResponseEntity<?> addMeals(@PathVariable  Long customerId, @Valid @RequestBody Restaurant newMeals) {

		return restaurantService.addMeals(customerId,newMeals);
	}
	
	@PostMapping("/{customerId}/services")
	public ResponseEntity<?> addService(@PathVariable  Long customerId, @Valid @RequestBody Services newService) {

		return serviceService.addService(customerId,newService);
	}
	
	@PutMapping("/{customerId}")
	public Customers updateCustomer(@PathVariable Long customerId,@Valid @RequestBody Customers newCustomer)
	{
		return customerService.updateCustomer(customerId,newCustomer);
	} 
	@DeleteMapping("/{customerId}")
	public void deleteCustomer(@PathVariable Long customerId)
	{
		customerService.deleteCustomer(customerId);
	}
	
	@DeleteMapping("/{customerId}/extras/{extrasId}")
	public ResponseEntity<?> deleteExtrasFromCustomer(@PathVariable Long customerId,@PathVariable Long extrasId)
	{
		return extrasService.deleteExtrasFromCustomer(customerId,extrasId);
	}
	
	@DeleteMapping("/{customerId}/meals/{mealsId}")
	public ResponseEntity<?> deleteMealsFromCustomer(@PathVariable Long customerId,@PathVariable Long mealsId)
	{
		return restaurantService.deleteMealsFromCustomer(customerId,mealsId);
	}
	
	@DeleteMapping("/{customerId}/services/{serviceId}")
	public ResponseEntity<?> deleteServiceFromCustomer(@PathVariable Long customerId,@PathVariable Long serviceId)
	{
		return serviceService.deleteServiceFromCustomer(customerId,serviceId);
	}
	
	}


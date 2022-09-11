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
import com.ugisoftware.hotelmanagement.services.CustomerService;
import com.ugisoftware.hotelmanagement.services.ExtrasService;



	@RequestMapping("/customer")
	@RestController
	public class CustomerController {
		private CustomerService customerService;
		private ExtrasService extrasService;
	public CustomerController(CustomerService customerService,ExtrasService extrasService)
	{
		this.customerService=customerService;
		this.extrasService=extrasService;
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
	  
	  
	@PostMapping
	public Customers  createPersonel(@Valid @RequestBody CustomerCreateDTO newCustomer) {
		return customerService.createCustomer(newCustomer);
	}

	@PostMapping("/{customerId}/extras")
	public ResponseEntity<?> addExtras(@PathVariable  Long customerId, @Valid @RequestBody Extras newExtras) {
        System.out.println("controller");

		return extrasService.addExtras(customerId,newExtras);
	}
	
	@PutMapping("/{customerId}")
	public Customers updateCustomer(@PathVariable Long customerId,@Valid @RequestBody Customers newCustomer)
	{
		return customerService.updateCustomer(customerId,newCustomer);
	} 

	@DeleteMapping("/{customerId}/extras/{extrasId}")
	public ResponseEntity<?> deleteExtrasFromCustomer(@PathVariable Long customerId,@PathVariable Long extrasId)
	{
		return extrasService.deleteExtrasFromCustomer(customerId,extrasId);
	}
	@DeleteMapping("/{customerId}")
	public void deleteCustomer(@PathVariable Long customerId)
	{
		customerService.deleteCustomer(customerId);
	}
	}


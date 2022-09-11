package com.ugisoftware.hotelmanagement.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ugisoftware.hotelmanagement.dto.request.CustomerCreateDTO;
import com.ugisoftware.hotelmanagement.dto.response.CustomerResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Customers;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.CustomerRepository;

@Service
public class CustomerService {
	CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customers getCustomer(Long customerId) {
		Customers customers =  customerRepository.findById(customerId)
	             .orElseThrow(() -> new EntityNotFoundException("Customer Not Found with id : " + customerId));
	      return customers;
	}

	public List<CustomerResponseDTO> getAllCustomers() {
		 List<Customers> customersList;
		 customersList= customerRepository.findAll();
		 return customersList.stream().map(customer -> new CustomerResponseDTO(customer)).collect(Collectors.toList());
	}
	public Customers createCustomer(@Valid CustomerCreateDTO newCustomer) {
		
		Customers customer1=customerRepository.getByEmail(newCustomer.getEmail());
		Customers customer2=customerRepository.getByPhone(newCustomer.getPhone());
		if(customer1!=null && customer2 !=null)
			throw new EntityNotFoundException("These email and phone are already in use");
		else if(customer1!=null )
			throw new EntityNotFoundException("This email is already in use");
		
		else if( customer2 !=null)
			throw new EntityNotFoundException("This phone is already in use");

		else {
		
		Customers createCustomer=new Customers();
		
		createCustomer.setAdress(newCustomer.getAdress());
		createCustomer.setPhone(newCustomer.getPhone());
		createCustomer.setBlood(newCustomer.getBlood());
		createCustomer.setEmail(newCustomer.getEmail());
		createCustomer.setGender(newCustomer.getGender());
		createCustomer.setName(newCustomer.getName());
		createCustomer.setSurname(newCustomer.getSurname());
		createCustomer.setId(newCustomer.getId());
	
		return customerRepository.save(createCustomer);
		}
	}
	
	public Customers updateCustomer(Long customerId, @Valid Customers newCustomer) {
		 return customerRepository.findById(customerId).map(customer -> {
			 if(customer==null)
					throw new EntityNotFoundException("Customer Not Found with id : " + customer.getId());
			 
			 else {
					
				 Customers updateCustomer=new Customers();
					
				 updateCustomer.setAdress(newCustomer.getAdress());
				 updateCustomer.setPhone(newCustomer.getPhone());
				 updateCustomer.setBlood(newCustomer.getBlood());
				 updateCustomer.setEmail(newCustomer.getEmail());
				 updateCustomer.setGender(newCustomer.getGender());
				 updateCustomer.setName(newCustomer.getName());
				 updateCustomer.setSurname(newCustomer.getSurname());
				 updateCustomer.setId(newCustomer.getId());
	         return customerRepository.save(updateCustomer);
				}
	     }).orElseThrow(() -> new EntityNotFoundException("CustomerId " + customerId + " not found"));

	}
	
	public ResponseEntity<?> deleteCustomer(Long customerId) {
		return customerRepository.findById(customerId).map(customer -> {
			customerRepository.delete(customer);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId ));
		
	}



	



}

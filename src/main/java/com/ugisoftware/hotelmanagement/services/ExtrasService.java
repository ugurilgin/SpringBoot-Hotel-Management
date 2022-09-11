package com.ugisoftware.hotelmanagement.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ugisoftware.hotelmanagement.dto.response.ExtrasResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Customers;
import com.ugisoftware.hotelmanagement.entities.Extras;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.CustomerRepository;
import com.ugisoftware.hotelmanagement.repositories.ExtrasRepository;

@Service
public class ExtrasService {
private ExtrasRepository extrasRepository;
private CustomerRepository customerRepository;
	 
public ExtrasService(ExtrasRepository extrasRepository,CustomerRepository customerRepository) {
	this.extrasRepository = extrasRepository;
	this.customerRepository=customerRepository;
}
	public List<ExtrasResponseDTO> getAllExtras() {
		List<Extras> extrasList;
		extrasList= extrasRepository.findAll();
		 return extrasList.stream().map(extra -> new ExtrasResponseDTO(extra)).collect(Collectors.toList());
	}

	public Extras getExtras(Long extrasId) {
		Extras extras =  extrasRepository.findById(extrasId)
	             .orElseThrow(() -> new EntityNotFoundException("Extra Not Found with id : " + extrasId));
	      return extras;
	}

	public Extras createExtras(@Valid Extras newExtras) {
		Extras createExtra=new Extras();
		createExtra.setId(newExtras.getId());
		createExtra.setName(newExtras.getName());
		createExtra.setPrice(newExtras.getPrice());
		return extrasRepository.save(createExtra);
		
	}

	public Extras updateExtras(Long extrasId, @Valid Extras newExtras) {
		return extrasRepository.findById(extrasId).map(extra -> {
			 if(extra==null)
					throw new EntityNotFoundException("Extra Not Found with id : " + extra.getId());
			 else {
				 Extras updateExtra=new Extras();
				 updateExtra.setId(newExtras.getId());
				 updateExtra.setName(newExtras.getName());
				 updateExtra.setPrice(newExtras.getPrice());
	         return extrasRepository.save(updateExtra);
				}
	     }).orElseThrow(() -> new EntityNotFoundException("ExtraId " + extrasId + " not found"));

	}


	public ResponseEntity<?> deleteExtras(Long extrasId) {
		return extrasRepository.findById(extrasId).map(extra -> {
			extrasRepository.delete(extra);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new EntityNotFoundException("Extra not found with id: " + extrasId ));
		
	}

	public ResponseEntity<List<Customers>> getAllCustomersByExtrasId(Long extrasId) {
		return extrasRepository.findById(extrasId).map(extra -> {
			 List<Customers> customers = customerRepository.findCustomersByExtrasId(extrasId);
	        return new ResponseEntity<>(customers, HttpStatus.OK);
	    }).orElseThrow(() -> new EntityNotFoundException("Extra not found with id: " + extrasId ));
		
	}
	
	public ResponseEntity<?> getAllExtrasByCustomerId(Long customerId) {
		    return customerRepository.findById(customerId).map(customer -> {
			    List<Extras> extras = extrasRepository.findExtrasByCustomersId(customerId);
		        return new ResponseEntity<>(extras, HttpStatus.OK);
		    }).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId ));
	}
	
	public ResponseEntity<?> addExtras(Long customerId, @Valid Extras newExtras) {

		Extras extras = customerRepository.findById(customerId).map(customer -> {
		      long extrasId = newExtras.getId();
		      // extra is existed
		      if (extrasId >=0) {
		        Extras _extras = extrasRepository.findById(extrasId)
		            .orElseThrow(() -> new EntityNotFoundException("Not found Extras with id = " + extrasId));
		        System.out.println("burada");
		        customer.addExtras(_extras);
		        customerRepository.save(customer);
		        return _extras;
		      }
		      
		      // add and create new Extra
		      customer.addExtras(newExtras);
		      return extrasRepository.save(newExtras);
		      
		    }).orElseThrow(() -> new EntityNotFoundException("Not found Customer with id = " + customerId));
		return new ResponseEntity<>(extras, HttpStatus.CREATED);
	}

	public ResponseEntity<?> deleteExtrasFromCustomer(Long customerId, Long extrasId) {
		return customerRepository.findById(customerId).map(customers -> {
			customers.removeExtras(extrasId);
			customerRepository.save(customers);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new EntityNotFoundException("Extra not found with id: " + customerId ));
	}

	}
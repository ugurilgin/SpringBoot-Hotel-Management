package com.ugisoftware.hotelmanagement.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ugisoftware.hotelmanagement.entities.Customers;
import com.ugisoftware.hotelmanagement.entities.Services;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.CustomerRepository;
import com.ugisoftware.hotelmanagement.repositories.ServicesRepositories;

@Service
public class ServiceService {
private ServicesRepositories servicesRepositories;
private CustomerRepository customerRepository;
	 
public ServiceService(ServicesRepositories servicesRepositories,CustomerRepository customerRepository) {
	this.servicesRepositories = servicesRepositories;
	this.customerRepository=customerRepository;
}
	public List<Services> getAllServices() {
		List<Services> serviceList;
		serviceList= servicesRepositories.findAll();
		 return serviceList;
	}

	public Services getService(Long serviceId) {
		Services services =  servicesRepositories.findById(serviceId)
	             .orElseThrow(() -> new EntityNotFoundException("Service Not Found with id : " + serviceId));
	      return services;
	}

	public Services createService(@Valid Services newService) {
		Services createService=new Services();
		createService.setId(newService.getId());
		createService.setName(newService.getName());
		createService.setPrice(newService.getPrice());
		return servicesRepositories.save(createService);
		
	}

	public Services updateService(Long serviceId, @Valid Services newService) {
		return servicesRepositories.findById(serviceId).map(service -> {
			 if(service==null)
					throw new EntityNotFoundException("Service Not Found with id : " + service.getId());
			 else {
				 Services updateServices=new Services();
				 updateServices.setId(newService.getId());
				 updateServices.setName(newService.getName());
				 updateServices.setPrice(newService.getPrice());
	         return servicesRepositories.save(updateServices);
				}
	     }).orElseThrow(() -> new EntityNotFoundException("SeviceId " + serviceId + " not found"));

	}


	public ResponseEntity<?> deleteService(Long serviceId) {
		return servicesRepositories.findById(serviceId).map(service -> {
			servicesRepositories.delete(service);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + serviceId ));
		
	}

	public ResponseEntity<List<Customers>> getAllCustomersByServiceId(Long serviceId) {
		return servicesRepositories.findById(serviceId).map(service -> {
			 List<Customers> customers = customerRepository.findCustomersByServicesId(serviceId);
	        return new ResponseEntity<>(customers, HttpStatus.OK);
	    }).orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + serviceId ));
		
	}
	
	public ResponseEntity<?> getAllServiceByCustomerId(Long customerId) {
		    return customerRepository.findById(customerId).map(customer -> {
			    List<Services> services = servicesRepositories.findServicesByCustomersId(customerId);
		        return new ResponseEntity<>(services, HttpStatus.OK);
		    }).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId ));
	}
	
	public ResponseEntity<?> addService(Long customerId, @Valid Services newService) {
		Services services = customerRepository.findById(customerId).map(customer -> {
		      long serviceId = newService.getId();
		      // meal is existed
		      if (serviceId >=0) {
		        Services _services = servicesRepositories.findById(serviceId)
		            .orElseThrow(() -> new EntityNotFoundException("Not found Service with id = " + serviceId));
		        customer.addServices(_services);
		        customerRepository.save(customer);
		        return _services;
		      }
		      
		      // add and create new Meals
		      customer.addServices(newService);
		      return servicesRepositories.save(newService);
		      
		    }).orElseThrow(() -> new EntityNotFoundException("Not found Customer with id = " + customerId));
		return new ResponseEntity<>(services, HttpStatus.CREATED);
	}

	public ResponseEntity<?> deleteServiceFromCustomer(Long customerId, Long serviceId) {
		return customerRepository.findById(customerId).map(customers -> {
			customers.removeServices(serviceId);
			customerRepository.save(customers);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId ));
	}

	}

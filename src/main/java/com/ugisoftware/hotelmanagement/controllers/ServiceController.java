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

import com.ugisoftware.hotelmanagement.entities.Services;
import com.ugisoftware.hotelmanagement.services.ServiceService;

@RequestMapping("/services")
@RestController
public class ServiceController {
	private ServiceService serviceService;

public ServiceController(ServiceService serviceService)
{
	this.serviceService=serviceService;
}

@GetMapping
public List<Services> getAllServices() {
	return serviceService.getAllServices();
}

@GetMapping("/{serviceId}")
public Services getService(@PathVariable Long serviceId) {
	return serviceService.getService(serviceId);
}


@GetMapping("/{serviceId}/customers")
public ResponseEntity<?> getAllCustomersByServiceId(@PathVariable Long serviceId) {

	return serviceService.getAllCustomersByServiceId(serviceId);
}
@PostMapping
public Services  createService(@Valid @RequestBody Services newService) {
	return serviceService.createService(newService);
}

@PutMapping("/{serviceId}")
public Services updateService(@PathVariable Long serviceId,@Valid @RequestBody Services newService)
{
	return serviceService.updateService(serviceId,newService);
} 

@DeleteMapping("/{serviceId}")
public void deleteExtras(Long serviceId)
{
	serviceService.deleteService(serviceId);
}
}
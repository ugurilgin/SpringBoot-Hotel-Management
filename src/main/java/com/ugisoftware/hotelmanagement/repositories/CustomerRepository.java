package com.ugisoftware.hotelmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.hotelmanagement.entities.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
	  List<Customers> findCustomersByExtrasId(Long extrasId);
	  List<Customers> findCustomersByRestaurantId(Long restaurantId);
	  List<Customers> findCustomersByServicesId(Long servicesId);
	Customers getByEmail(String email);
	Customers getByPhone(String phone);

}

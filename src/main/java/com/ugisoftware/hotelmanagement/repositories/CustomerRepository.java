package com.ugisoftware.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.hotelmanagement.entities.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long> {

}

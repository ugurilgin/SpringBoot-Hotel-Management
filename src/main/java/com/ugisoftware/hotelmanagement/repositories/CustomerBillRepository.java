package com.ugisoftware.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.hotelmanagement.entities.CustomerBill;

public interface CustomerBillRepository extends JpaRepository<CustomerBill, Long> {

}

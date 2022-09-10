package com.ugisoftware.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.hotelmanagement.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee getByEmail(String email);

	Employee getByPhone(String phone);

}

package com.ugisoftware.hotelmanagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugisoftware.hotelmanagement.dto.response.EmployeeResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Employee;
import com.ugisoftware.hotelmanagement.services.EmployeeService;


@RequestMapping("/employee")
@RestController
public class EmployeeController {
	private EmployeeService employeeService;

public EmployeeController(EmployeeService employeeService)
{
	this.employeeService=employeeService;
}

@GetMapping
public List<EmployeeResponseDTO> getAllPersonels() {
	return employeeService.getAllEmployee();
}

@GetMapping("/{employeeId}")
public Employee getPersonel(@PathVariable Long employeeId) {
	return employeeService.getEmployee(employeeId);
}

@PostMapping
public Employee  createPersonel(@Valid @RequestBody Employee newEmployee) {
	return employeeService.createEmployee(newEmployee);
}

@PutMapping("/{employeeId}")
public Employee updatePersonel(@PathVariable Long employeeId,@Valid @RequestBody Employee newEmployee)
{
	return employeeService.updateEmployee(employeeId,newEmployee);
} 

@DeleteMapping("/{employeeId}")
public void deleteEmployee(Long employeeId)
{
	employeeService.deleteEmployee(employeeId);
}
}
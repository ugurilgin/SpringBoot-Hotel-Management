package com.ugisoftware.hotelmanagement.services;


import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ugisoftware.hotelmanagement.dto.response.EmployeeResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Employee;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.EmployeeRepository;
import com.ugisoftware.hotelmanagement.utils.DateUtil;

@Service
public class EmployeeService {
	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
		
	}

	
	public Employee getEmployee(Long employeeId) {
		
		Employee employee =  employeeRepository.findById(employeeId)
	             .orElseThrow(() -> new EntityNotFoundException("Employee Not Found with id : " + employeeId));
	      return employee;
					

	}
	public List<EmployeeResponseDTO> getAllEmployee() {
		 List<Employee> employeelList;
		 employeelList= employeeRepository.findAll();
		 return employeelList.stream().map(personel -> new EmployeeResponseDTO(personel)).collect(Collectors.toList());
	}


	
	public Employee createEmployee(@Valid Employee newEmployee) 
	{
		DateUtil.compareDates(newEmployee.getBirthDate(), newEmployee.getStartDate(), newEmployee.getFinishDate());
		
		Employee employee=employeeRepository.getByEmail(newEmployee.getEmail());
		Employee employee2=employeeRepository.getByPhone(newEmployee.getPhone());
		if(employee!=null && employee2 !=null)
			throw new EntityNotFoundException("These email and phone are already in use");
		else if(employee!=null )
			throw new EntityNotFoundException("This email is already in use");
		
		else if( employee2 !=null)
			throw new EntityNotFoundException("This phone is already in use");

		else {
		
		Employee createEmployee=new Employee();
		
		createEmployee.setAdress(newEmployee.getAdress());
		createEmployee.setBirthDate(newEmployee.getBirthDate());
		createEmployee.setBlood(newEmployee.getBlood());
		createEmployee.setEmail(newEmployee.getEmail());
		createEmployee.setGender(newEmployee.getGender());
		createEmployee.setName(newEmployee.getName());
		createEmployee.setSurname(newEmployee.getSurname());
		createEmployee.setId(newEmployee.getId());
		createEmployee.setFinishDate(newEmployee.getFinishDate());
		createEmployee.setPhone(newEmployee.getPhone());
		createEmployee.setSalary(newEmployee.getSalary());
		createEmployee.setStartDate(newEmployee.getStartDate());
		createEmployee.setJob(newEmployee.getJob());
		return employeeRepository.save(createEmployee);
		}
	}



	public Employee updateEmployee(Long employeeId, @Valid Employee newEmployee) {
		DateUtil.compareDates(newEmployee.getBirthDate(), newEmployee.getStartDate(), newEmployee.getFinishDate());

		 return employeeRepository.findById(employeeId).map(employee -> {
	
					
			 employee.setAdress(newEmployee.getAdress());
			 employee.setBirthDate(newEmployee.getBirthDate());
			 employee.setBlood(newEmployee.getBlood());
			 employee.setEmail(newEmployee.getEmail());
			 employee.setGender(newEmployee.getGender());
			 employee.setName(newEmployee.getName());
			 employee.setSurname(newEmployee.getSurname());
			 employee.setFinishDate(newEmployee.getFinishDate());
			 employee.setPhone(newEmployee.getPhone());
			 employee.setSalary(newEmployee.getSalary());
			 employee.setStartDate(newEmployee.getStartDate());
			 employee.setJob(newEmployee.getJob());
	         return employeeRepository.save(employee);
				
	     }).orElseThrow(() -> new EntityNotFoundException("EmployeeId " + employeeId + " not found"));

		
	}
	public ResponseEntity<?> deleteEmployee(Long employeeId) {
		return employeeRepository.findById(employeeId).map(employee -> {
	        employeeRepository.delete(employee);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new EntityNotFoundException("EmployeeId " + employeeId + " not found"));
		
		
	}
}

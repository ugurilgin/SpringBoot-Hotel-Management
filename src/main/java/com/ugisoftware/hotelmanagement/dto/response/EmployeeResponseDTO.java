package com.ugisoftware.hotelmanagement.dto.response;

import java.time.LocalDate;
import java.util.Date;

import com.ugisoftware.hotelmanagement.entities.Employee;
import com.ugisoftware.hotelmanagement.utils.Blood;
import com.ugisoftware.hotelmanagement.utils.DateUtil;
import com.ugisoftware.hotelmanagement.utils.Gender;

public class EmployeeResponseDTO {

	private Long id;
	private String name;
	private String surname;
	private String birthDate; 
	private Gender gender;
	private String startDate; 
	private String finishDate ; 
	private String adress;
	private String email;
	private Blood blood;
	private int salary;
	private String job;
	public EmployeeResponseDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.surname = employee.getSurname();
		this.birthDate = employee.getBirthDate();
		this.gender = employee.getGender();
		this.startDate = employee.getStartDate();
		this.finishDate = employee.getFinishDate();
		this.adress = employee.getAdress();
		this.email = employee.getEmail();
		this.blood = employee.getBlood();
		this.salary = employee.getSalary();
		this.job=employee.getJob();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Blood getBlood() {
		return blood;
	}
	public void setBlood(Blood blood) {
		this.blood = blood;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}


}

package com.ugisoftware.hotelmanagement.dto.response;

import java.util.Date;

import com.ugisoftware.hotelmanagement.entities.Employee;
import com.ugisoftware.hotelmanagement.utils.DateUtil;
import com.ugisoftware.hotelmanagement.utils.Gender;

public class EmployeeResponseDTO {

	private Long id;
	private String name;
	private String surname;
	private Date birthDate; 
	private Gender gender;
	private Date startDate; 
	private Date finishDate ; 
	private String adress;
	private String email;
	private String blood;
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
	public Date getBirthDate() {
		return DateUtil.removeTime(birthDate);
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = DateUtil.removeTime(birthDate);
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getStartDate() {
		return DateUtil.removeTime(startDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = DateUtil.removeTime(startDate);
	}

	public Date getFinishDate() {
		return DateUtil.removeTime(finishDate);
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = DateUtil.removeTime(finishDate);
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
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
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

package com.ugisoftware.hotelmanagement.entities;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ugisoftware.hotelmanagement.constraints.BirthDate;
import com.ugisoftware.hotelmanagement.constraints.PastString;
import com.ugisoftware.hotelmanagement.utils.Blood;
import com.ugisoftware.hotelmanagement.utils.Gender;

import lombok.Data;
@Data
@Entity
@Table(name="employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=25 ,message = "Name Size must be between 3 and 25")
	@NotNull(message = "name can not be empty")
	private String name;
	
	@Size(min=3, max=25,message = "Surname Size must be between 3 and 25")
	@NotNull(message = "surname can not be empty")
	private String surname;
	
	@Size(min=3, max=25,message = "Job Size must be between 3 and 25")
	@NotNull(message = "Job can not be empty")
	private String job;
	
	@PastString(message = "The date of birth must be in the past.")
	@BirthDate(message = "The birth date must be greater or equal than 18")
	private String birthDate; 
	
	@NotNull(message = "gender can not be empty")
	private Gender gender;
	
	@NotNull(message = "Start to Job Date can not be empty")
	private String startDate; 
	
	private String finishDate ; 
	
	@Lob
	@Column(columnDefinition="text")
	private String adress;
	
	@Column(unique=true)
	@Size(min=12 ,max=12)
	@NotNull(message = "phone can not be empty")
	@Pattern(regexp ="(?:\\d{3}-){2}\\d{4}")
	private String phone;
	
	@Column(unique=true)
	@Size(min=3 ,max=80)
	@NotNull(message = "email can not be empty")
	@Email(message = "email should be a valid email")
	private String email;
	
	private Blood blood;
	
	@NotNull(message = "salary can not be empty")
	private int salary;
	public Employee() {
	
	}
	
	public Employee(Long id,
			@Size(min = 3, max = 25, message = "Name Size must be between 3 and 25") @NotNull(message = "name can not be empty") String name,
			@Size(min = 3, max = 25, message = "Surname Size must be between 3 and 25") @NotNull(message = "surname can not be empty") String surname,
			@Size(min = 3, max = 25, message = "Job Size must be between 3 and 25") @NotNull(message = "Job can not be empty") String job,
			String birthDate, @NotNull(message = "gender can not be empty") Gender gender,
			@NotNull(message = "Start to Job Date can not be empty") String startDate, String finishDate, String adress,
			@Size(min = 12, max = 12) @NotNull(message = "phone can not be empty") @Pattern(regexp = "(?:\\d{3}-){2}\\d{4}") String phone,
			@Size(min = 3, max = 80) @NotNull(message = "email can not be empty") @Email(message = "email should be a valid email") String email,
			Blood blood, @NotNull(message = "salary can not be empty") int salary) {
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.job = job;
		this.birthDate = birthDate;
		this.gender = gender;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
		this.blood = blood;
		this.salary = salary;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

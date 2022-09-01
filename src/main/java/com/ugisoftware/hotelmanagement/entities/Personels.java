package com.ugisoftware.hotelmanagement.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
@Entity
@Table(name="personels")
public class Personels {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=25 ,message = "Name Size must be between 3 and 25")
	@NotNull(message = "name can not be empty")
	private String name;
	
	@Size(min=3, max=25,message = "Surname Size must be between 3 and 25")
	@NotNull(message = "surname can not be empty")
	private String surname;
	
	@NotNull(message = "Birth Date can not be empty")
	@Temporal(TemporalType.DATE)
	private Date birthDate; 
	
	@Size(min=3, max=25,message = "Gender Size must be between 3 and 25")
	@NotNull(message = "gender can not be empty")
	private String gender;
	
	@NotNull(message = "Start to Job Date can not be empty")
	@Temporal(TemporalType.DATE)
	private Date startDate; 
	
	@Temporal(TemporalType.DATE)
	private Date finishDate ; 
	
	@Lob
	@Column(columnDefinition="text")
	private String adress;
	
	@NotNull(message = "phone can not be empty")
	@Pattern(regexp ="[0-9\\s]{12}")
	private String phone;
	
	@NotNull(message = "email can not be empty")
	@Email(message = "email should be a valid email")
	private String email;
	
	@Size(min=2, max=3)
	private String blood;
	
	@NotNull(message = "salary can not be empty")
	private int salary;
}

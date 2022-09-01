package com.ugisoftware.hotelmanagement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name="customer")
public class Customers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=25 ,message = "Name Size must be between 3 and 25")
	@NotNull(message = "name can not be empty")
	private String name;
	
	@Size(min=3, max=25,message = "Surname Size must be between 3 and 25")
	@NotNull(message = "surname can not be empty")
	private String surname;
	
	
	@Size(min=3, max=25,message = "Gender Size must be between 3 and 25")
	@NotNull(message = "gender can not be empty")
	private String gender;
	
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
}

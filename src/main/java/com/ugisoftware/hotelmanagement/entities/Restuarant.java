package com.ugisoftware.hotelmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
@Entity
@Table(name="restuarant")
public class Restuarant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=25,message = "Extra Name size must be between 3 and 25")
	@NotNull(message = "name can not be empty")
	private String name;
	
	@NotNull(message = "price can not be empty")
	private int price;
}

package com.ugisoftware.hotelmanagement.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ugisoftware.hotelmanagement.utils.Gender;

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
	
	
	@NotNull(message = "gender can not be empty")
	private Gender gender;
	
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
	
	@Size(min=2, max=3)
	private String blood;
	
	 @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
		  @JoinTable(name = "customer_extras",
		        joinColumns = { @JoinColumn(name = "customer_id") },
		        inverseJoinColumns = { @JoinColumn(name = "extras_id") })
	  private Set<Extras> extras = new HashSet<>();
	 
	 @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
		  @JoinTable(name = "customer_services",
		        joinColumns = { @JoinColumn(name = "customer_id") },
		        inverseJoinColumns = { @JoinColumn(name = "service_id") })
	  private Set<Services> services = new HashSet<>();
	 
	 @ManyToMany(fetch = FetchType.LAZY,
		      cascade = {
		          CascadeType.PERSIST,
		          CascadeType.MERGE
		      })
		  @JoinTable(name = "customer_restaurant",
		        joinColumns = { @JoinColumn(name = "customer_id") },
		        inverseJoinColumns = { @JoinColumn(name = "restaurant_id") })
	  private Set<Restaurant> restaurant = new HashSet<>();

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

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public Set<Extras> getExtras() {
		return extras;
	}

	public void setExtras(Set<Extras> extras) {
		this.extras = extras;
	}

	public Set<Services> getServices() {
		return services;
	}

	public void setServices(Set<Services> services) {
		this.services = services;
	}

	public Set<Restaurant> getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Set<Restaurant> restaurant) {
		this.restaurant = restaurant;
	}
	 
	 
}

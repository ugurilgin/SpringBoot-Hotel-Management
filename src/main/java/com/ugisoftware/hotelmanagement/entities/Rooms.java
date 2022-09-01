package com.ugisoftware.hotelmanagement.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Table(name="rooms")
public class Rooms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Beds number can not be null")
	private int beds;
	
	@NotNull(message = "Price can not be null")
	private int price;
	
	@NotNull(message = "Type can not be null")
	@Size(min=3, max=25,message = "Type of Room Size must be between 3 and 25")
	private String type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personel_id",nullable = false)
	@JsonIgnore
	private Personels personels;
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private RoomStatus roomStatus;
}

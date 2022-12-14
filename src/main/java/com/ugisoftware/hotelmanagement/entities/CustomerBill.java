package com.ugisoftware.hotelmanagement.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Data
@Entity
@Table(name = "customerbill")
public class CustomerBill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Customers customers;
	
	@NotNull(message = "Entry Date can not be empty")
	private String  entryDate;
	
	@NotNull(message = "Exit Date can not be empty")
	private String  exitDate;
	
	@NotNull(message = "count can not be empty")
	private int count;//Person Count who stayed in the room
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Rooms rooms;

	public CustomerBill() {
		super();
	}

	public CustomerBill(Long id, Customers customers,
			@NotNull(message = "Entry Date can not be empty") String entryDate,
			@NotNull(message = "Exit Date can not be empty") String exitDate,
			@NotNull(message = "count can not be empty") int count, Rooms rooms) {
		super();
		this.id = id;
		this.customers = customers;
		this.entryDate = entryDate;
		this.exitDate = exitDate;
		this.count = count;
		this.rooms = rooms;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	


	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getExitDate() {
		return exitDate;
	}

	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Rooms getRooms() {
		return rooms;
	}

	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}
	
	
}

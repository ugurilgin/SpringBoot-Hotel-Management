package com.ugisoftware.hotelmanagement.entities;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ugisoftware.hotelmanagement.utils.DateUtil;

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
	
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotNull(message = "Entry Date can not be empty")
	@JsonFormat(pattern="dd-MM-yyyy" )
	private Date entryDate;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@NotNull(message = "Exit Date can not be empty")
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date exitDate;
	
	@NotNull(message = "count can not be empty")
	private int count;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Rooms rooms;

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

	public Date getEntryDate() {
		return DateUtil.removeTime(entryDate);
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = DateUtil.removeTime(entryDate);
	}

	public Date getExitDate() {
		return DateUtil.removeTime(exitDate);
	}

	public void setExitDate(Date exitDate) {
		this.exitDate = DateUtil.removeTime(exitDate);
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

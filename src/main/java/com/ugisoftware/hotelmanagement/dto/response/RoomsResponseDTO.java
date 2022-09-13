package com.ugisoftware.hotelmanagement.dto.response;

import com.ugisoftware.hotelmanagement.entities.Rooms;
import com.ugisoftware.hotelmanagement.utils.RoomClean;
import com.ugisoftware.hotelmanagement.utils.RoomStatues;
import com.ugisoftware.hotelmanagement.utils.RoomTypes;


public class RoomsResponseDTO {
	private Long id;
	private Long personelid;
	private String personelfullname;
	private int bed;
	private int price;
	private RoomTypes type;
	private RoomStatues statue;
	private RoomClean clean;
	private int roomNumber;
	public RoomsResponseDTO(Rooms room) {
		this.id = room.getId();
		this.personelid =room.getEmployee().getId();
		this.personelfullname=room.getEmployee().getName()+" "+room.getEmployee().getSurname();
		this.bed=room.getBeds();
		this.price=room.getPrice();
		this.type=room.getType();
		this.statue=room.getStatue();
		this.clean=room.getClean();
		this.roomNumber=room.getRoomNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPersonelid() {
		return personelid;
	}

	public void setPersonelid(Long personelid) {
		this.personelid = personelid;
	}

	public String getPersonelfullname() {
		return personelfullname;
	}

	public void setPersonelfullname(String personelfullname) {
		this.personelfullname = personelfullname;
	}

	public int getBed() {
		return bed;
	}

	public void setBed(int bed) {
		this.bed = bed;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public RoomTypes getType() {
		return type;
	}

	public void setType(RoomTypes type) {
		this.type = type;
	}

	public RoomStatues getStatue() {
		return statue;
	}

	public void setStatue(RoomStatues statue) {
		this.statue = statue;
	}

	public RoomClean getClean() {
		return clean;
	}

	public void setClean(RoomClean clean) {
		this.clean = clean;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	

	
	
	
}

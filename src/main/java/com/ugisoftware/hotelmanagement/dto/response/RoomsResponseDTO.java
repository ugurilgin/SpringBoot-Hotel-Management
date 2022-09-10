package com.ugisoftware.hotelmanagement.dto.response;

import com.ugisoftware.hotelmanagement.entities.Rooms;


public class RoomsResponseDTO {
	private Long id;
	private Long personelid;
	private String personelfullname;
	private int bed;
	private int price;
	private String type;
	private String statue;
	private String clean;

	public RoomsResponseDTO(Rooms room) {
		this.id = room.getId();
		this.personelid =room.getEmployee().getId();
		this.personelfullname=room.getEmployee().getName()+" "+room.getEmployee().getSurname();
		this.bed=room.getBeds();
		this.price=room.getPrice();
		this.type=room.getType();
		this.statue=room.getStatue();
		this.clean=room.getClean();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getClean() {
		return clean;
	}

	public void setClean(String clean) {
		this.clean = clean;
	}

	
	
	
}

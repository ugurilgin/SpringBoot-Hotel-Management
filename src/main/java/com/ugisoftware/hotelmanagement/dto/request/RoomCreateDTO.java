package com.ugisoftware.hotelmanagement.dto.request;


public class RoomCreateDTO {
	private Long id;
	private Long employeeId;
	private int beds;
	private int price;
	private String type;
	private String statue;
	private String clean;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}




	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public int getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
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
package com.ugisoftware.hotelmanagement.dto.response;

import com.ugisoftware.hotelmanagement.entities.Extras;

public class ExtrasResponseDTO {
private Long id;
private String name;
private int price;
public ExtrasResponseDTO(Extras extras) {
	
	this.id = extras.getId();
	this.name = extras.getName();
	this.price = extras.getPrice();
}
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
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}


}

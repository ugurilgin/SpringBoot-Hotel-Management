package com.ugisoftware.hotelmanagement.dto.request;

import com.ugisoftware.hotelmanagement.utils.Blood;
import com.ugisoftware.hotelmanagement.utils.Gender;

public class CustomerCreateDTO {
private Long id; 
private String name;
private String surname;
private String	adress; 
private Blood blood	;
private String email;
private Gender	gender;
private String phone;
public CustomerCreateDTO(Long id, String name, String surname, String adress, Blood blood, String email, Gender gender,
		String phone) {
	this.id = id;
	this.name = name;
	this.surname = surname;
	this.adress = adress;
	this.blood = blood;
	this.email = email;
	this.gender = gender;
	this.phone = phone;
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
public String getSurname() {
	return surname;
}
public void setSurname(String surname) {
	this.surname = surname;
}
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public Blood getBlood() {
	return blood;
}
public void setBlood(Blood blood) {
	this.blood = blood;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Gender getGender() {
	return gender;
}
public void setGender(Gender gender) {
	this.gender = gender;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}


}

package com.ugisoftware.hotelmanagement.dto.request;

import java.time.LocalDate;
import java.util.Date;




public class BillCreateDTO {
private Long id;
private int count;


private String entryDate;


private String exitDate;

private Long customerId;
private Long roomId;

public BillCreateDTO() {
	super();
}
public BillCreateDTO(Long id, int count, String entryDate, String exitDate, Long customerId, Long roomId) {
	super();
	this.id = id;
	this.count = count;
	this.entryDate = entryDate;
	this.exitDate = exitDate;
	this.customerId = customerId;
	this.roomId = roomId;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
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
public Long getCustomerId() {
	return customerId;
}
public void setCustomerId(Long customerId) {
	this.customerId = customerId;
}
public Long getRoomId() {
	return roomId;
}
public void setRoomId(Long roomId) {
	this.roomId = roomId;
}


}

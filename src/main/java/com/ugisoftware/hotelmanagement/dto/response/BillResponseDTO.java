package com.ugisoftware.hotelmanagement.dto.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ugisoftware.hotelmanagement.entities.CustomerBill;
import com.ugisoftware.hotelmanagement.entities.Extras;
import com.ugisoftware.hotelmanagement.entities.Restaurant;
import com.ugisoftware.hotelmanagement.entities.Services;
import com.ugisoftware.hotelmanagement.utils.DateUtil;
import com.ugisoftware.hotelmanagement.utils.Gender;
import com.ugisoftware.hotelmanagement.utils.RoomTypes;


public class BillResponseDTO {
	private Long id;
	private int count;
	private String entryDate;
	private String exitDate;
	private Long customerId;
	private String customerName;
	private String customerSurname;
	private String email;
	private String phone;
	private Gender gender;
	private List<Extras> extras;
	private List<Restaurant> meals;
	private List<Services> services;
	private Long roomId;
	private int roomPrice;
	private int roomNumber;
	private RoomTypes roomType;
	private Long sumRoomPrice;;
	private Long sumExtras;
	private Long sumMeals;
	private Long sumServices;
	private Long sumAll;
	public BillResponseDTO(CustomerBill bill) {
		super();
		this.id = bill.getId();
		this.count = bill.getCount();
		this.entryDate = bill.getEntryDate();
		this.exitDate = bill.getExitDate();
		this.customerId = bill.getCustomers().getId();
		this.roomId = bill.getRooms().getId();
		this.roomPrice=bill.getRooms().getPrice();
		this.roomType=bill.getRooms().getType();
		this.roomNumber=bill.getRooms().getRoomNumber();
		this.customerName=bill.getCustomers().getName();
		this.customerSurname=bill.getCustomers().getSurname();
		this.email=bill.getCustomers().getEmail();
		this.phone=bill.getCustomers().getPhone();
		this.gender=bill.getCustomers().getGender();
		this.extras=bill.getCustomers().getExtras().stream().toList();
		this.meals=bill.getCustomers().getRestaurant().stream().toList();
		this.services=bill.getCustomers().getServices().stream().toList();
		this.sumRoomPrice=DateUtil.getDifferenceDays(entryDate, exitDate)*roomPrice;
		this.sumExtras=sumExtraList(extras);
		this.sumMeals=sumMealList(meals);
		this.sumServices=sumServiceList(services);
		this.sumAll=sumRoomPrice+sumExtras+sumMeals+sumServices;
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
	public int getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}
	public Long getSumRoomPrice() {

		return sumRoomPrice;
	}
	public void setSumRoomPrice(Long sumRoomPrice) {
		this.sumRoomPrice = sumRoomPrice;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerSurname() {
		return customerSurname;
	}
	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public List<Extras> getExtras() {
		return extras;
	}
	public void setExtras(List<Extras> extras) {
		this.extras = extras;
	}
	public List<Restaurant> getMeals() {
		return meals;
	}
	public void setMeals(List<Restaurant> meals) {
		this.meals = meals;
	}
	public List<Services> getServices() {
		return services;
	}
	public void setServices(List<Services> services) {
		this.services = services;
	}
	public Long getSumExtras() {
		return sumExtras;
	}
	public void setSumExtras(Long sumExtras) {
		this.sumExtras = sumExtras;
	}
	public Long getSumMeals() {
		return sumMeals;
	}
	public void setSumMeals(Long sumMeals) {
		this.sumMeals = sumMeals;
	}
	public Long getSumServices() {
		return sumServices;
	}
	public void setSumServices(Long sumServices) {
		this.sumServices = sumServices;
	}
	public Long getSumAll() {
		return sumAll;
	}
	public void setSumAll(Long sumAll) {
		this.sumAll = sumAll;
	}
	public RoomTypes getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomTypes roomType) {
		this.roomType = roomType;
	}

	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	private long sumExtraList(List<Extras> list)
	{
		Integer sum = list.stream()
				  .map(x -> x.getPrice())
				  .collect(Collectors.summingInt(Integer::intValue));

		return sum.longValue();
	}
	
	private long sumMealList(List<Restaurant> list)
	{
		Integer sum = list.stream()
				  .map(x -> x.getPrice())
				  .collect(Collectors.summingInt(Integer::intValue));

		return sum.longValue();
	}
	private long sumServiceList(List<Services> list)
	{
		Integer sum = list.stream()
				  .map(x -> x.getPrice())
				  .collect(Collectors.summingInt(Integer::intValue));

		return sum.longValue();
	}
}
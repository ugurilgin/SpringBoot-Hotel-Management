package com.ugisoftware.hotelmanagement.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ugisoftware.hotelmanagement.dto.request.BillCreateDTO;
import com.ugisoftware.hotelmanagement.dto.response.BillResponseDTO;
import com.ugisoftware.hotelmanagement.entities.CustomerBill;
import com.ugisoftware.hotelmanagement.entities.Customers;
import com.ugisoftware.hotelmanagement.entities.Rooms;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.CustomerBillRepository;
import com.ugisoftware.hotelmanagement.utils.DateUtil;
@Service
public class CustomerBillService {
	private CustomerBillRepository customerBillRepository;
	private CustomerService customerService;
	private RoomService roomService;

	public CustomerBillService(CustomerBillRepository customerBillRepository, CustomerService customerService,RoomService roomService) {
		this.customerBillRepository = customerBillRepository;
		this.customerService = customerService;
		this.roomService = roomService;
	}

	public List<BillResponseDTO> getAllBills() {
		List<CustomerBill> billList;
		billList= customerBillRepository.findAll();
		 return billList.stream().map(bill -> new BillResponseDTO(bill)).collect(Collectors.toList());
	}
	
	public BillResponseDTO getBill(Long billId) {
		CustomerBill bill =  customerBillRepository.findById(billId)
	             .orElseThrow(() -> new EntityNotFoundException("Bill Not Found with id : " + billId));
	      return new BillResponseDTO(bill);
	}
	
	public CustomerBill createBill(@Valid BillCreateDTO newBill) {
		CustomerBill newB=new CustomerBill();
		DateUtil.compareTwoDate(newBill.getEntryDate(), newBill.getExitDate(), "Exit Date cant be earlier than Entry Date");
		Rooms rooms=roomService.getRoom(newBill.getRoomId()) ;
		Customers customer=customerService.getCustomer(newBill.getCustomerId()) ;

		if(rooms==null)
			throw new EntityNotFoundException("Room Not Found with id : " + newBill.getRoomId());
		else if (customer==null)
			throw new EntityNotFoundException("Customer Not Found with id : " + newBill.getCustomerId());

		else {
			newB.setCount(newBill.getCount());
			newB.setId(newBill.getId());
			newB.setCustomers(customer);
			newB.setRooms(rooms);
			newB.setEntryDate(newBill.getEntryDate());
			newB.setExitDate(newBill.getExitDate());
			
			
		}
		return customerBillRepository.save(newB);
	}
	
	public CustomerBill updateBill(Long billId, @Valid BillCreateDTO newBill) {
		DateUtil.compareTwoDate(newBill.getEntryDate(), newBill.getExitDate(), "Exit Date cant be earlier than Entry Date");
		Rooms rooms=roomService.getRoom(newBill.getRoomId()) ;
		Customers customer=customerService.getCustomer(newBill.getCustomerId()) ;
		return customerBillRepository.findById(billId).map(newbill -> {
			if(rooms==null)
				throw new EntityNotFoundException("Room Not Found with id : " + newBill.getRoomId());
			else if (customer==null)
				throw new EntityNotFoundException("Customer Not Found with id : " + newBill.getCustomerId());
				else {
					newbill.setCount(newBill.getCount());
					newbill.setId(newBill.getId());
					newbill.setCustomers(customer);
					newbill.setRooms(rooms);
					newbill.setEntryDate(newBill.getEntryDate());
					newbill.setExitDate(newBill.getExitDate());
					
	         return customerBillRepository.save(newbill);
				}
	     }).orElseThrow(() -> new EntityNotFoundException("BillId " + billId + " not found"));
	}
	
	public ResponseEntity<?> deleteBill(Long billId) {
		return customerBillRepository.findById(billId).map(bill -> {
			customerBillRepository.delete(bill);
	        return ResponseEntity.ok().build();
	    }).orElseThrow(() -> new EntityNotFoundException("BillId " + billId + " not found"));
		
	}

	

	

	

	

}

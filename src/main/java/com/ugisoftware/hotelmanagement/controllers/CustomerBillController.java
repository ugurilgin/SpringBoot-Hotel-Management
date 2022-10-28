package com.ugisoftware.hotelmanagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugisoftware.hotelmanagement.dto.request.BillCreateDTO;
import com.ugisoftware.hotelmanagement.dto.response.BillResponseDTO;
import com.ugisoftware.hotelmanagement.entities.CustomerBill;
import com.ugisoftware.hotelmanagement.services.CustomerBillService;

@RequestMapping("/api/bill")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerBillController {
	private CustomerBillService customerBillService;

public CustomerBillController(CustomerBillService customerBillService)
{
	this.customerBillService=customerBillService;
}

@GetMapping
public List<BillResponseDTO> getAllBills() {
	return customerBillService.getAllBills();
}

@GetMapping("/{billId}")
public BillResponseDTO getBill(@PathVariable Long billId) {
	return customerBillService.getBill(billId);
}

@PostMapping
public CustomerBill  createBill(@Valid @RequestBody BillCreateDTO newBill) {
	return customerBillService.createBill(newBill);
}

@PutMapping("/{billId}")
public CustomerBill updateBill(@PathVariable Long billId,@Valid @RequestBody BillCreateDTO newBill)
{
	return customerBillService.updateBill(billId,newBill);
} 

@DeleteMapping("/{billId}")
public void deleteBill(@PathVariable Long billId)
{
	customerBillService.deleteBill(billId);
}
}


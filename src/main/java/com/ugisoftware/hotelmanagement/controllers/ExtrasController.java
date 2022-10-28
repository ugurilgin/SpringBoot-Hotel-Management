package com.ugisoftware.hotelmanagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ugisoftware.hotelmanagement.dto.response.ExtrasResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Extras;
import com.ugisoftware.hotelmanagement.services.ExtrasService;


@RequestMapping("/api/extras")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExtrasController {
	private ExtrasService extrasService;

public ExtrasController(ExtrasService extrasService)
{
	this.extrasService=extrasService;
}

@GetMapping
public List<ExtrasResponseDTO> getAllExtras() {
	return extrasService.getAllExtras();
}

@GetMapping("/{extrasId}")
public Extras getExtras(@PathVariable Long extrasId) {
	return extrasService.getExtras(extrasId);
}


@GetMapping("/{extrasId}/customers")
public ResponseEntity<?> getAllCustomersByExtrasId(@PathVariable Long extrasId) {

	return extrasService.getAllCustomersByExtrasId(extrasId);
}
@PostMapping
public Extras  createExtras(@Valid @RequestBody Extras newExtras) {
	return extrasService.createExtras(newExtras);
}

@PutMapping("/{extrasId}")
public Extras updateExtras(@PathVariable Long extrasId,@Valid @RequestBody Extras newExtras)
{
	return extrasService.updateExtras(extrasId,newExtras);
} 

@DeleteMapping("/{extrasId}")
public void deleteExtras(Long extrasId)
{
	extrasService.deleteExtras(extrasId);
}
}

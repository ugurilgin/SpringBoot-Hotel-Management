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

import com.ugisoftware.hotelmanagement.dto.request.RoomCreateDTO;
import com.ugisoftware.hotelmanagement.dto.response.RoomsResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Rooms;
import com.ugisoftware.hotelmanagement.services.RoomService;



@RequestMapping("/api/rooms")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RoomController {
	private RoomService roomService;

public RoomController(RoomService roomService)
{
	this.roomService=roomService;
}

@GetMapping
public List<RoomsResponseDTO> getAllRooms() {
	return roomService.getAllRooms();
}

@GetMapping("/{roomId}")
public Rooms getRoom(@PathVariable Long roomId) {
	return roomService.getRoom(roomId);
}

@PostMapping
public Rooms  createRoom(@Valid @RequestBody RoomCreateDTO newRoom) {
	return roomService.createRoom(newRoom);
}

@PutMapping("/{roomId}")
public Rooms updateRoom(@PathVariable Long roomId,@Valid @RequestBody RoomCreateDTO newRoom)
{
	return roomService.updateRoom(roomId,newRoom);
} 

@DeleteMapping("/{roomId}")
public void deleteRoom(@PathVariable Long roomId)
{
	roomService.deleteRoom(roomId);
}
}

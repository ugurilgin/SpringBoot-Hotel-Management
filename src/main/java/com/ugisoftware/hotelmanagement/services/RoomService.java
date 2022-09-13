package com.ugisoftware.hotelmanagement.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.ugisoftware.hotelmanagement.dto.request.RoomCreateDTO;
import com.ugisoftware.hotelmanagement.dto.response.RoomsResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Employee;
import com.ugisoftware.hotelmanagement.entities.Rooms;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.RoomsRepository;

@Service
public class RoomService {
RoomsRepository roomsRepository;
EmployeeService employeeService;
public RoomService(RoomsRepository roomsRepository,EmployeeService employeeService) {
	this.roomsRepository = roomsRepository;
	this.employeeService=employeeService;
	
}

public List<RoomsResponseDTO> getAllRooms()
{    List<Rooms> roomList;
	 roomList= roomsRepository.findAll();
	 return roomList.stream().map(room -> new RoomsResponseDTO(room)).collect(Collectors.toList());

}

public Rooms getRoom(Long roomId) {
	
	 Rooms room =  roomsRepository.findById(roomId)
             .orElseThrow(() -> new EntityNotFoundException("Room Not Found with id : " + roomId));
      return room;
				

}

public Rooms createRoom(@Valid RoomCreateDTO room)
{
	Rooms newRooms=new Rooms();
	Employee employee=employeeService.getEmployee(room.getEmployeeId()) ;
	if(employee==null)
		throw new EntityNotFoundException("Employee Not Found with id : " + room.getEmployeeId());
	else {
		newRooms.setBeds(room.getBeds());
		newRooms.setClean(room.getClean());
		newRooms.setId(room.getId());
		newRooms.setPrice(room.getPrice());
		newRooms.setStatue(room.getStatue());
		newRooms.setType(room.getType());
		newRooms.setEmployee(employee);
		newRooms.setRoomNumber(room.getRoomNumber());
		
	}
	return roomsRepository.save(newRooms);
}



public Rooms updateRoom(@Valid Long roomId,RoomCreateDTO room ) {
	Employee employee=employeeService.getEmployee(room.getEmployeeId()) ;
	
	 return roomsRepository.findById(roomId).map(newroom -> {
		 if(employee==null)
				throw new EntityNotFoundException("Employee Not Found with id : " + room.getEmployeeId());
			else {
		 newroom.setBeds(room.getBeds());
		 newroom.setClean(room.getClean());
		 newroom.setId(room.getId());
		 newroom.setPrice(room.getPrice());
		 newroom.setStatue(room.getStatue());
		 newroom.setType(room.getType());
		 newroom.setEmployee(employee);
		 newroom.setRoomNumber(room.getRoomNumber());
         return roomsRepository.save(newroom);
			}
     }).orElseThrow(() -> new EntityNotFoundException("RoomId " + roomId + " not found"));

	
}
public ResponseEntity<?> deleteRoom(Long roomId) {
	return roomsRepository.findById(roomId).map(room -> {
        roomsRepository.delete(room);
        return ResponseEntity.ok().build();
    }).orElseThrow(() -> new EntityNotFoundException("RoomId " + roomId + " not found"));
	//commentRepository.deleteById(commentId);
	
}

}

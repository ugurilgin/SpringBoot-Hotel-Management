package com.ugisoftware.hotelmanagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import com.mysql.cj.xdevapi.Schema.CreateCollectionOptions;
import com.ugisoftware.hotelmanagement.dto.request.RoomCreateDTO;
import com.ugisoftware.hotelmanagement.dto.response.RoomsResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Employee;
import com.ugisoftware.hotelmanagement.entities.Rooms;
import com.ugisoftware.hotelmanagement.repositories.EmployeeRepository;
import com.ugisoftware.hotelmanagement.repositories.RoomsRepository;
import com.ugisoftware.hotelmanagement.services.EmployeeService;
import com.ugisoftware.hotelmanagement.services.RoomService;
import com.ugisoftware.hotelmanagement.utils.Blood;
import com.ugisoftware.hotelmanagement.utils.Gender;
import com.ugisoftware.hotelmanagement.utils.RoomClean;
import com.ugisoftware.hotelmanagement.utils.RoomStatues;
import com.ugisoftware.hotelmanagement.utils.RoomTypes;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

	@InjectMocks
	private RoomService roomService;
	
	@Mock
	private RoomsRepository roomsRepository;
	
	@Mock
	private EmployeeService employeeService;
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@Test
	public void getAllRoom_Success_TryToGetAllRooms()
	{
		List<RoomsResponseDTO> roomResponseList;
		
		 roomResponseList= roomService.getAllRooms();
		    assertThat(roomResponseList.size()).isGreaterThanOrEqualTo(0);

	}
	@Test
	public void getAllRoomwithValues_Success_TryToGetAllRooms()
	{
		List<Rooms> list=exampleRoomsWithEmployee();

		when(roomsRepository.findAll()).thenReturn(list);
		List<RoomsResponseDTO> roomList=roomService.getAllRooms();
		assertEquals(3, roomList.size());
		verify(roomsRepository, times(1)).findAll();

		
	}
	
	@Test
	public void getRoomById_Success_TryToRoomById()
	{
		Rooms oneRooms=exampleOneRoom();
		
		when(roomsRepository.findById(1L)).thenReturn(Optional.of(oneRooms) );
		Rooms room=roomService.getRoom(1L);
		assertEquals(1, room.getId());
		assertEquals(true, room.equals(oneRooms));

	}
	@Test
	public void saveRoom_Success_TryToSave()
	{
		Employee newEmployee =exampleEmployee("Test-Adress","01-01-1999",Blood.ABRhN,"test@test.com","01-01-2021",Gender.Erkek,1L,"Test-Meslek","Test-Name","Test-Surname","183-456-7744",1000,"01-01-2020");
		RoomCreateDTO roomOne=new RoomCreateDTO() ;
		roomOne.setId(1L);roomOne.setBeds(1);roomOne.setClean( RoomClean.Clean );
		roomOne.setEmployeeId(1L);roomOne.setPrice(100);roomOne.setRoomNumber(100);
		roomOne.setStatue(RoomStatues.Available);roomOne.setType(RoomTypes.Dubleks);
		when(employeeService.getEmployee(1L)).thenReturn(newEmployee );
		when(roomsRepository.save(exampleOneRoom())).thenReturn(exampleOneRoom() );
		when(roomService.createRoom(roomOne)).thenReturn(exampleOneRoom() );
		Rooms newR =roomService.createRoom(roomOne);

		


	}
	
	private List<Rooms> exampleRoomsWithEmployee() {
		
		List<Rooms> list=new ArrayList<Rooms>()  ;
		Rooms roomOne = exampleOneRoom();
		Rooms roomTwo = new Rooms();
		Rooms roomThree = new Rooms();
		Employee newEmployee =exampleEmployee("Test-Adress","01-01-1999",Blood.ABRhN,"test@test.com","01-01-2021",Gender.Erkek,1L,"Test-Meslek","Test-Name","Test-Surname","183-456-7744",1000,"01-01-2020");
		
	
		
		roomTwo.setId(2L);roomTwo.setBeds(2);roomTwo.setClean( RoomClean.Clean );
		roomTwo.setEmployee(newEmployee);roomTwo.setPrice(200);roomTwo.setRoomNumber(200);
		roomTwo.setStatue(RoomStatues.Available);roomTwo.setType(RoomTypes.Dubleks);
		roomThree.setId(3L);roomThree.setBeds(3);roomThree.setClean( RoomClean.Clean );
		roomThree.setEmployee(newEmployee);roomThree.setPrice(300);roomThree.setRoomNumber(300);
		roomThree.setStatue(RoomStatues.Available);roomThree.setType(RoomTypes.Dubleks);
		
		list.add(roomOne);
		list.add(roomTwo);
		list.add(roomThree);
		return list;
		
	}
	
	public static Employee exampleEmployee(String adress,String birthDate,Blood blood,String email,String finishDate,Gender gender,Long id,String job,String name,String surname,String phone,int salary,String startDate)
	{ 
		Employee newEmployee =new Employee();
		newEmployee.setAdress(adress);
		newEmployee.setBirthDate(birthDate);
		newEmployee.setBlood(blood);
		newEmployee.setEmail(email);
		newEmployee.setFinishDate(finishDate);
		newEmployee.setGender(gender);
		newEmployee.setId(id);
		newEmployee.setJob(job);
		newEmployee.setName(name);
		newEmployee.setSurname(surname);
		newEmployee.setPhone(phone);
		newEmployee.setSalary(salary);
		newEmployee.setStartDate(startDate);
		return newEmployee;
	}
	
	private Rooms exampleOneRoom()
	{
		
		Rooms roomOne = new Rooms();
		
		Employee newEmployee =exampleEmployee("Test-Adress","01-01-1999",Blood.ABRhN,"test@test.com","01-01-2021",Gender.Erkek,1L,"Test-Meslek","Test-Name","Test-Surname","183-456-7744",1000,"01-01-2020");
		
		roomOne.setId(1L);roomOne.setBeds(1);roomOne.setClean( RoomClean.Clean );
		roomOne.setEmployee(newEmployee);roomOne.setPrice(100);roomOne.setRoomNumber(100);
		roomOne.setStatue(RoomStatues.Available);roomOne.setType(RoomTypes.Dubleks);
		return roomOne;
		
		
	}
}

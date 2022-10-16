package com.ugisoftware.hotelmanagement;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ugisoftware.hotelmanagement.controllers.RoomController;
import com.ugisoftware.hotelmanagement.dto.request.RoomCreateDTO;
import com.ugisoftware.hotelmanagement.dto.response.RoomsResponseDTO;
import com.ugisoftware.hotelmanagement.entities.Employee;
import com.ugisoftware.hotelmanagement.entities.Rooms;
import com.ugisoftware.hotelmanagement.exceptions.EntityNotFoundException;
import com.ugisoftware.hotelmanagement.repositories.RoomsRepository;
import com.ugisoftware.hotelmanagement.services.RoomService;
import com.ugisoftware.hotelmanagement.utils.Blood;
import com.ugisoftware.hotelmanagement.utils.Gender;
import com.ugisoftware.hotelmanagement.utils.RoomClean;
import com.ugisoftware.hotelmanagement.utils.RoomStatues;
import com.ugisoftware.hotelmanagement.utils.RoomTypes;

@WebMvcTest(RoomController.class)
@Import(RoomController.class)
public class RoomControllerTest {
	
   
		
	    @Autowired
	    private MockMvc mockMvc;
	
	    @MockBean
	    private RoomService roomService;
	    @MockBean
	    private RoomsRepository roomRepository;
	
	    @Autowired
	    private ObjectMapper objectMapper;
	    
	
	
		@Test
	    public void givenRoomObject_whenCreateRoom_thenReturnSavedRoom() throws Exception{
	
	        // given - precondition or setup
			RoomCreateDTO newRooms=new RoomCreateDTO();
			newRooms.setId(1L);newRooms.setBeds(1);newRooms.setClean( RoomClean.Clean );
			newRooms.setEmployeeId(1L);newRooms.setPrice(100);newRooms.setRoomNumber(100);
			newRooms.setStatue(RoomStatues.Available);newRooms.setType(RoomTypes.Dubleks);
					given(roomService.createRoom(any(RoomCreateDTO.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));
	
	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/rooms")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(newRooms)));
	
	        // then - verify the result or output using assert statements
	        response.andDo(print()).
	                andExpect(status().isOk())
	                .andExpect(jsonPath("$.beds",
	                        is(newRooms.getBeds())))
	                .andExpect(jsonPath("$.roomNumber",
	                        is(newRooms.getRoomNumber())));
	       
	    }
		
		
		   // JUnit test for Get All rooms REST API
	    @Test
	    public void givenListOfRooms_whenGetAllRooms_thenReturnRoomsList() throws Exception{
	        // given - precondition or setup
	        List<RoomsResponseDTO > listOfRooms = new ArrayList<>();
	        listOfRooms= exampleRoomsWithEmployee().stream().map(room -> new RoomsResponseDTO(room)).collect(Collectors.toList());

	        given(roomService.getAllRooms()).willReturn(listOfRooms);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/rooms"));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.size()",
	                        is(listOfRooms.size())));
	
	    }
	    @Test
	    public void givenroomId_whenGetRoomById_thenReturnRoomObject() throws Exception{
	        // given - precondition or setup
	        long roomId = 1L;
	    	Rooms room =exampleOneRoom();
	
	        given(roomService.getRoom(roomId)).willReturn(room);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/rooms/{roomId}", roomId));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.beds",
	                        is(room.getBeds())))
	                .andExpect(jsonPath("$.roomNumber",
	                        is(room.getRoomNumber())));
	
	    }
	    @Test
	    public void givenInvalidroomId_whenGetRoomById_thenReturnException() throws Exception{
	        // given - precondition or setup
	        long roomId = 1L;
	     
	       
	     	when(roomService.getRoom(roomId)).thenThrow(new EntityNotFoundException("roomId " + roomId + " not found"));
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/rooms/{roomId}", roomId));
	
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
	
	    }
	    @Test
	    public void givenUpdatedRoom_whenUpdateRoom_thenReturnUpdateRoomObject() throws Exception{
	        // given - precondition or setup
	        long roomId = 1L;
	        Rooms savedRooms=new Rooms();
	        savedRooms.setId(1L);savedRooms.setBeds(1);savedRooms.setClean( RoomClean.Clean );
	        savedRooms.setPrice(100);savedRooms.setRoomNumber(100);
	        savedRooms.setStatue(RoomStatues.Available);savedRooms.setType(RoomTypes.Dubleks);	
	
	        RoomCreateDTO uptatedRooms=new RoomCreateDTO();
	        uptatedRooms.setId(1L);uptatedRooms.setBeds(2);uptatedRooms.setClean( RoomClean.Clean );
	        uptatedRooms.setEmployeeId(1L);uptatedRooms.setPrice(200);uptatedRooms.setRoomNumber(100);
	        uptatedRooms.setStatue(RoomStatues.Available);uptatedRooms.setType(RoomTypes.Dubleks);	
	        
	        Rooms uptatedRoomsResponse=new Rooms();
	        uptatedRoomsResponse.setId(1L);uptatedRoomsResponse.setBeds(2);uptatedRoomsResponse.setClean( RoomClean.Clean );
	       uptatedRooms.setPrice(200);uptatedRoomsResponse.setRoomNumber(100);
	        uptatedRoomsResponse.setStatue(RoomStatues.Available);uptatedRoomsResponse.setType(RoomTypes.Dubleks);	
	
	        given(roomRepository.findById(roomId)).willReturn(Optional.of(savedRooms) );
	        given(roomService.getRoom(roomId)).willReturn(savedRooms);
	
	        given(roomService.updateRoom(anyLong(),any(RoomCreateDTO.class)))
	        .willReturn(uptatedRoomsResponse);
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(put("/rooms/{roomId}", roomId)
	                                    .contentType(MediaType.APPLICATION_JSON)
	                                    .content(objectMapper.writeValueAsString(uptatedRoomsResponse)));
	
	
	        // then - verify the output
	        response.andDo(print()).
	        andExpect(status().isOk());
	    }
	    @Test
	    public void givenUpdatedRoom_whenUpdateRoom_thenReturnException() throws Exception{
	        // given - precondition or setup
	    	 long roomId = 1L;
	    	 RoomCreateDTO newRooms=new RoomCreateDTO();
				newRooms.setId(1L);newRooms.setBeds(1);newRooms.setClean( RoomClean.Clean );
				newRooms.setEmployeeId(1L);newRooms.setPrice(100);newRooms.setRoomNumber(100);
				newRooms.setStatue(RoomStatues.Available);newRooms.setType(RoomTypes.Dubleks);	
	     	  given(roomRepository.findById(roomId)).willReturn(null );
	     	
	         
	     	when(roomService.updateRoom(anyLong(),any(RoomCreateDTO.class))).thenThrow(new EntityNotFoundException("roomId " + roomId + " not found"));
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(put("/rooms/{roomId}", roomId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(newRooms)));
	
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
	    }
	    @Test
	    public void givenroomId_whenDeleteRoom_thenReturn200() throws Exception{
	        // given - precondition or setup
	        long roomId = 1L;
	   
	        
	        given(roomRepository.findById(roomId)).willReturn(Optional.of(exampleOneRoom()) );
	        given(roomService.deleteRoom(roomId))
	        .willAnswer((invocation)-> invocation.getArgument(0));
	     
	
	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(delete("/rooms/{roomId}", roomId));
	
	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print());
	    }
	    @Test
	    public void givenroomId_whenDeleteRoom_thenReturnException() throws Exception{
	        // given - precondition or setup
	        long roomId = 1L;
	        
	        given(roomRepository.findById(roomId)).willReturn(null );
	     	when(roomService.deleteRoom(roomId)).thenThrow(new EntityNotFoundException("roomId " + roomId + " not found"));
	
	     
	
	        // when -  action or the behaviour that we are going test
	     	
	        ResultActions response = mockMvc.perform(delete("/rooms/{roomId}", roomId));
	      
	        // then - verify the output
	        response.andExpect(status().isNotFound())
	        .andDo(print());
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
	}



	
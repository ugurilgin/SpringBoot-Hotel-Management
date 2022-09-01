package com.ugisoftware.hotelmanagement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



import lombok.Data;

@Data
@Entity
@Table(name="room_status")
public class RoomStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "room_status")
	private Rooms rooms;
	
	@NotNull(message = "Statue can not be empty")
	@Size(min=3, max=25,message = "Statue size must be between 3 and 25")
	private String statue;
	
	@NotNull(message = "Is Clean can not be empty")
	@Size(min=3, max=25,message = "Is Clean  size must be between 3 and 25")
	private String clean;
}

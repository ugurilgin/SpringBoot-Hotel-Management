package com.ugisoftware.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.hotelmanagement.entities.Rooms;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {

}

package com.ugisoftware.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.hotelmanagement.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

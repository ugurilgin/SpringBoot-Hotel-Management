package com.ugisoftware.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.hotelmanagement.entities.RefreshToken;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

	RefreshToken findByUserId(Long userId);
}
	

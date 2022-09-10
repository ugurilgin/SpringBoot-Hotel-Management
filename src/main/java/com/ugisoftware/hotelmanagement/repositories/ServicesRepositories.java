package com.ugisoftware.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.hotelmanagement.entities.Services;

public interface ServicesRepositories extends JpaRepository<Services, Long> {

}

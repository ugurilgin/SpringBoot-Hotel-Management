package com.ugisoftware.hotelmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.hotelmanagement.entities.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}

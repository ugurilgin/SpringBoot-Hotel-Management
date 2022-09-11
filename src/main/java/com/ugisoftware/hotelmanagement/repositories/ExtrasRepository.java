package com.ugisoftware.hotelmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ugisoftware.hotelmanagement.entities.Extras;

public interface ExtrasRepository extends JpaRepository<Extras, Long> {
	List<Extras> findExtrasByCustomersId(Long customersId);
}

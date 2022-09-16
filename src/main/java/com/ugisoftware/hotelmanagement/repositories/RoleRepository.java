package com.ugisoftware.hotelmanagement.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ugisoftware.hotelmanagement.entities.ERole;
import com.ugisoftware.hotelmanagement.entities.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}

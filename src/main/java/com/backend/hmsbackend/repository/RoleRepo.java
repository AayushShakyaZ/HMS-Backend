package com.backend.hmsbackend.repository;

import com.backend.hmsbackend.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo  extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);


}

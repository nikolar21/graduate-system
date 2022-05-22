package com.tusofia.scienceblog.repository;

import com.tusofia.scienceblog.model.entity.ERole;
import com.tusofia.scienceblog.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(ERole name);
}

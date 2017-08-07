package com.bozturk.idle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bozturk.idle.model.Role;

@Transactional
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByRole(String role);

}

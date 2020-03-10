package com.app.entry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entry.model.Role;
/**
 * @author lk00564920
 * RoleRepository
 * Helps to manage all crud operation related to role entity
 * 
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}
